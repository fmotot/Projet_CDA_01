/**
 * 
 */
package fr.eni.trocencheres.bll;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Retrait;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.DAOFactory;
import fr.eni.trocencheres.dal.EnchereDAO;
import fr.eni.trocencheres.dal.VenteDAO;

/**
 * @author fmoto
 *
 */
public class VenteManagerImpl implements VenteManager {
	private VenteDAO venteDAO;
	private EnchereDAO enchereDAO;

	VenteManagerImpl() {
		venteDAO = DAOFactory.getVenteDAO();
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	@Override
	public Vente creerVente(String nomArticle, String description, LocalDateTime dateFinEncheres, Integer miseAPrix,
			Utilisateur vendeur, String rue, String ville, Integer codePostal) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Vente vente = null;
		
		nomArticle = this.validerNomArticle(nomArticle, businessException);
		description = this.validerDescription(description, businessException);
		
		
		
		
		return vente;
	}

	@Override
	public Vente annulerVente(Vente vente, Utilisateur utilisateurSession) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> listerVentes(Utilisateur utilisateur, boolean isMesVentes, boolean isMesEncheres,
			boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere encherir(Utilisateur acheteur, Vente vente, Integer mise) throws BusinessException {
		// TODO Auto-generated method stub
		// refuser une enchère plus basse que l'enchère la plus haute en cours
		return null;
	}

	@Override
	public List<Vente> terminerVentes() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere annulerEnchere(Enchere enchere, Utilisateur utilisateurSession) throws BusinessException {
		BusinessException businessException = new BusinessException();

		utilisateurSession = this.validerUtilisateur(utilisateurSession, businessException);
		enchere = this.validerEnchere(enchere, businessException);

		if (!businessException.hasErreurs()) {
			if (enchere.getAcheteur() == utilisateurSession) {
				enchere = enchereDAO.deleteOne(enchere);
				// créditer l'utilisateur
			} else {
				businessException.ajouterErreur(CodesResultatBLL.ENCHERE_SUPPRESSION_REFUSEE);
				throw businessException;
			}
		} else {
			throw businessException;
		}

		return enchere;
	}

	@Override
	public Vente afficherVente(Integer noVente) throws BusinessException {
		Vente vente = null;
		if (noVente > 0) {
			vente = new Vente();
			vente.setNoVente(noVente);
			vente = venteDAO.getOne(vente);
		}

		if (vente == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.VENTE_INCONNUE);
			throw businessException;
		}

		return vente;
	}

	private String validerNomArticle(String nomArticle, BusinessException businessException) {
		if (nomArticle == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_NOM_ARTICLE_VIDE);
		} else {
			nomArticle = nomArticle.trim();
			if (nomArticle.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_NOM_ARTICLE_VIDE);
			} else {
				if (nomArticle.length() > 300) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_NOM_ARTICLE_TROP_LONG);
				}
			}
		}

		return nomArticle;
	}

	private String validerDescription(String description, BusinessException businessException) {
		if (description == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DESCRIPTION_VIDE);
		} else {
			description = description.trim();
			if (description.length() < 1) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DESCRIPTION_VIDE);
			} else {
				if (description.length() > 300) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DESCRIPTION_TROP_LONG);
				}
			}
		}

		return description;
	}

	private Date validerDateFinEnchere(Date dateFinEnchere, BusinessException businessException) {
		if (dateFinEnchere == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DATE_FIN_ENCHERE_VIDE);
		} else {
			// TODO si date inférieur à aujourd'hui erreur
			if (dateFinEnchere.before(new Date())) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DATE_FIN_ENCHERE_IMPOSSIBLE);
			}
		}

		return dateFinEnchere;
	}

	private Integer validerPrixInitial(Integer prixInitial, BusinessException businessException) {
		if (prixInitial == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_PRIX_INITIAL_VIDE);
		} else {
			if (prixInitial < 0) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_PRIX_INITIAL_NEGATIF);
			}
		}

		return prixInitial;
	}

	private Utilisateur validerUtilisateur(Utilisateur utilisateurSession, BusinessException businessException) {
		if (utilisateurSession == null) {
			businessException.ajouterErreur(CodesResultatBLL.UTILISATEUR_INCONNU);
		}

		return utilisateurSession;
	}

	private Enchere validerEnchere(Enchere enchere, BusinessException businessException) {
		if (enchere == null) {
			businessException.ajouterErreur(CodesResultatBLL.ENCHERE_INCONNUE);
		}

		return enchere;
	}

	private Retrait validerRetrait(String rue, String ville, String codePostal, BusinessException businessException) {
		Retrait retrait = null;
		
		if (rue != null) {
			rue = rue.trim();
		}
		if (ville != null) {
			ville = ville.trim();
		}
		if (codePostal != null) {
			codePostal = codePostal.trim();
		}
		
		// si tous les champs sont null ou vide 
		if (((rue == null || rue.length() < 1) && (ville == null || ville.length() < 1) && (codePostal == null || codePostal.length() < 1))) {
			retrait = null;
		} else {
			// si un des champs reste null ou vide
			if ((rue == null || rue.length() < 1) || (ville == null || ville.length() < 1) || (codePostal == null || codePostal.length() < 1)) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_INCOMPLET);
			}
			else {
				if (rue.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_RUE_TROP_LONG);
				}
				if (ville.length() > 30) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_VILLE_TROP_LONG);
				}
				if (codePostal.length() > 15) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAIT_CODE_POSTAL_TROP_LONG);
				}
				
				if (!businessException.hasErreurs()) {
					retrait = new Retrait(rue, ville, codePostal);
				}
			}
		}

		return retrait;
	}

}