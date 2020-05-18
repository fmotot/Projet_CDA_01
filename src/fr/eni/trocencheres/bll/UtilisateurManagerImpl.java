/**
 * 
 */
package fr.eni.trocencheres.bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.DAOFactory;
import fr.eni.trocencheres.dal.UtilisateurDAO;
import fr.eni.trocencheres.settings.Settings;

/**
 * @author fmoto
 *
 */
class UtilisateurManagerImpl implements UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	UtilisateurManagerImpl() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	@Override
	public Utilisateur login(String login, String motDePasse) throws BusinessException {

		Utilisateur utilisateur = utilisateurDAO.selectByLogin(login);

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.LOGIN_INCORRECT);
			throw businessException;
		} else if (!utilisateur.getMotDePasse().equals(encryptMDP(motDePasse))) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_INCORRECT);
			throw businessException;
		}

		return utilisateur;
	}

	@Override
	public Utilisateur supprimerMonCompte(Utilisateur utilisateurConnecte) throws BusinessException {
		return this.utilisateurDAO.deleteOne(utilisateurConnecte);
	}

	@Override
	public Utilisateur afficherUtilisateur(String pseudo) throws BusinessException {
		return this.utilisateurDAO.selectByLogin(pseudo);
	}

	@Override
	public Utilisateur modifierMonCompte(Utilisateur utilisateurSession, Utilisateur utilisateurData)
			throws BusinessException {
		BusinessException businessException = new BusinessException();

		// Validation des éléments
		utilisateurSession.setCodePostal(this.validerCodePostal(utilisateurData.getCodePostal(), businessException));
		utilisateurSession.setPseudo(this.validerPseudo(utilisateurData.getPseudo(), businessException));
		utilisateurSession.setNom(this.validerNom(utilisateurData.getNom(), businessException));
		utilisateurSession.setPrenom(this.validerPrenom(utilisateurData.getPrenom(), businessException));
		utilisateurSession.setEmail(this.validerEmail(utilisateurData.getEmail(), businessException));
		utilisateurSession.setRue(this.validerRue(utilisateurData.getRue(), businessException));
		utilisateurSession.setVille(this.validerVille(utilisateurData.getVille(), businessException));

		// test du téléphone si changer uniquement
		if (!utilisateurData.getTelephone().equals(utilisateurSession.getTelephone())) {
			utilisateurSession.setTelephone(this.validerTelephone(utilisateurData.getTelephone(), businessException));
		}

		// Validation du mot de passe si nouveau
		boolean isPasswordToBeChanged = false;
		if (utilisateurData.getMotDePasse() != null && !utilisateurData.getMotDePasse().equals("")
				&& !utilisateurData.getMotDePasse().equals(utilisateurSession.getMotDePasse())) {
			utilisateurSession
					.setMotDePasse(this.validerMotDePasse(utilisateurData.getMotDePasse(), businessException));
			isPasswordToBeChanged = true;
		}

		if (!businessException.hasErreurs()) {
			// Cryptage du mot de passe si nouveau ou si changement du Pseudo (pour salage)
			if (isPasswordToBeChanged) {
				utilisateurSession.setMotDePasse(this.encryptMDP(utilisateurSession.getMotDePasse()));
			}

			utilisateurSession = this.utilisateurDAO.updateOne(utilisateurSession);
		} else {
			throw businessException;
		}

		return utilisateurSession;
	}

	@Override
	public Utilisateur creerCompteUtilisateur(String telephone, String codePostal, String pseudo, String nom,
			String prenom, String email, String rue, String ville, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;

		// Validation des éléments
		telephone = this.validerTelephone(telephone, businessException);
		codePostal = this.validerCodePostal(codePostal, businessException);
		pseudo = this.validerPseudo(pseudo, businessException);
		nom = this.validerNom(nom, businessException);
		prenom = this.validerPrenom(prenom, businessException);
		email = this.validerEmail(email, businessException);
		rue = this.validerRue(rue, businessException);
		ville = this.validerVille(ville, businessException);
		motDePasse = this.validerMotDePasse(motDePasse, businessException);

		if (!businessException.hasErreurs()) {
			String hashMotDePasse = this.encryptMDP(motDePasse);
			int credit = Integer.parseInt(Settings.getProperty("defaut_credit"));
			boolean administrateur = Boolean.parseBoolean(Settings.getProperty("defaut_compte_administrateur"));
			boolean actif = Boolean.parseBoolean(Settings.getProperty("defaut_compte_actif"));

			utilisateur = new Utilisateur(telephone, codePostal, credit, pseudo, nom, prenom, email, rue, ville,
					hashMotDePasse, administrateur, actif);

			utilisateur = this.utilisateurDAO.insertOne(utilisateur);
		} else {
			throw businessException;
		}

		return utilisateur;
	}

	private String encryptMDP(String motDePasse) throws BusinessException {
		byte[] hash = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_SELECTION_ENCODAGE);
			throw businessException;
		}

		// on salt avec le pseudo de l'utilisateur
		md.update(Settings.getProperty("constant_chiffrage").getBytes());
		hash = md.digest(motDePasse.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	private String validerMotDePasse(String motDePasse, BusinessException businessException) {
		if (motDePasse == null || motDePasse.length() < 8) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_MOT_DE_PASSE_TROP_COURT);
		} else {

		}

		return motDePasse;
	}

	private String validerTelephone(String telephone, BusinessException businessException) throws BusinessException {

		if (telephone != null) {
			telephone = telephone.trim();
			if (!telephone.equals("")) {
				if (utilisateurDAO.isTelephoneExist(telephone)) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_TELEPHONE_DOUBLON);
				} else if (telephone.length() > 15) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_TELEPHONE_TROP_LONG);
				}
			}
			else {
				telephone = null;
			}
		}

		return telephone;
	}

	private String validerCodePostal(String codePostal, BusinessException businessException) {

		if (codePostal == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_CODE_POSTAL_VIDE);
		} else {
			codePostal = codePostal.trim();
			if (codePostal.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_CODE_POSTAL_VIDE);
			} else {
				if (codePostal.length() > 10) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_CODE_POSTAL_TROP_LONG);
				}
			}
		}

		return codePostal;
	}

	private String validerPseudo(String pseudo, BusinessException businessException) {

		if (pseudo == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO_VIDE);
		} else {
			pseudo = pseudo.trim();
			if (pseudo.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO_VIDE);
			} else {
				if (pseudo.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PSEUDO_TROP_LONG);
				}
			}
		}

		return pseudo;
	}

	private String validerNom(String nom, BusinessException businessException) {

		if (nom == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_NOM_VIDE);
		} else {
			nom = nom.trim();
			if (nom.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_NOM_VIDE);
			} else {
				if (nom.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_NOM_TROP_LONG);
				}
			}
		}

		return nom;
	}

	private String validerPrenom(String prenom, BusinessException businessException) {

		if (prenom == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PRENOM_VIDE);
		} else {
			prenom = prenom.trim();
			if (prenom.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PRENOM_VIDE);
			} else {
				if (prenom.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_PRENOM_TROP_LONG);
				}
			}
		}

		return prenom;
	}

	private String validerEmail(String email, BusinessException businessException) {

		if (email == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_EMAIL_VIDE);
		} else {
			email = email.trim();
			if (email.length() > 50) {

				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_EMAIL_TROP_LONG);
			} else {
				String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);

				if (email == null || !matcher.matches()) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_EMAIL_NON_VALIDE);
				}
			}

		}

		return email;
	}

	private String validerRue(String rue, BusinessException businessException) {

		if (rue == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_RUE_VIDE);
		} else {
			rue = rue.trim();
			if (rue.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_RUE_VIDE);
			} else {
				if (rue.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_RUE_TROP_LONG);
				}
			}
		}

		return rue;
	}

	private String validerVille(String ville, BusinessException businessException) {

		if (ville == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_VILLE_VIDE);
		} else {
			ville = ville.trim();
			if (ville.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_VILLE_VIDE);
			} else {
				if (ville.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_UTILISATEUR_VILLE_TROP_LONG);
				}
			}
		}

		return ville;
	}
}
