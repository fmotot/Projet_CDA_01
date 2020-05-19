/**
 * 
 */
package fr.eni.trocencheres.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
			Utilisateur vendeur, String rue, String ville, String codePostal, Categorie categorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Vente vente = null;
		
		nomArticle = this.validerNomArticle(nomArticle, businessException);
		description = this.validerDescription(description, businessException);
		dateFinEncheres = this.validerDateFinEnchere(dateFinEncheres, businessException);
		miseAPrix = this.validerPrixInitial(miseAPrix, businessException);
		vendeur = this.validerUtilisateur(vendeur, businessException);
		Retrait retrait = this.validerRetrait(rue, ville, codePostal, businessException);
		categorie = this.validerCategorie(categorie, businessException);
		
		if (!businessException.hasErreurs()) {
			vente = new Vente(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, retrait, false, categorie, null);
			
			vente = this.venteDAO.insertOne(vente);
		}
		else {
			throw businessException;
		}
		
		return vente;
	}

	@Override
	public Vente annulerVente(Vente vente, Utilisateur utilisateurSession) throws BusinessException {
		
		
		BusinessException businessException = new BusinessException();
		
		utilisateurSession = this.validerUtilisateur(utilisateurSession, businessException);
		vente = this.validerVente(vente, businessException);
		
		if (!businessException.hasErreurs()) {
			
			if (utilisateurSession.equals(vente.getVendeur())) {
				// créditer les comptes utilisateurs des mises
				for (Enchere enchere : vente.getListeEncheres()) {
					enchere.getAcheteur().setCredit(enchere.getAcheteur().getCredit() + enchere.getMise());
				}			
				
				// pour annuler une vente il faut supprimer la vente de la BDD ainsi que les enchères qui la concernent et mettre à jour les utilisateurs liés
				vente = venteDAO.deleteOne(vente);
			}
			else {
				businessException.ajouterErreur(CodesResultatBLL.VENTE_ANNULATION_REFUSEE);
				throw businessException;
			}
		}
		else {
			throw businessException;
		}
		
		return vente;
	}

	@Override
	public List<Vente> listerVentes(Utilisateur utilisateur, boolean isMesVentes, boolean isMesEncheres,
			boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)
			throws BusinessException {
		
		return venteDAO.getVentesFiltered(utilisateur, isMesVentes, isMesEncheres, isMesAcquisitions, isAutresEncheres, recherche, categorie);
	}

	@Override
	public Vente encherir(Utilisateur acheteur, Vente vente, Integer mise) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Enchere enchere = null;
		
		acheteur = this.validerUtilisateur(acheteur, businessException);
		vente = this.validerVente(vente, businessException);
		
		if (!businessException.hasErreurs()) {
			// refuser une enchère plus basse que l'enchère la plus haute en cours
			if (mise > vente.getMaxEnchere().getMise()) {
				enchere = new Enchere(acheteur, vente, mise);
				
				// récupération ancienne enchère pour en connaître le montant ou vérif si existante
				Enchere oldEnchere = enchereDAO.getOne(enchere);
				
				// update si Enchere existante dans la liste
				if (oldEnchere != null) {
					
					// MAJ des points utilisateur
					acheteur.setCredit(acheteur.getCredit() - mise + oldEnchere.getMise());
					
					enchere = enchereDAO.updateOne(enchere);
				}
				// sinon insert
				else {
					
					// MAJ des points utilisateur
					acheteur.setCredit(acheteur.getCredit() - mise);
					
					enchere = enchereDAO.insertOne(enchere);
				}
				vente = venteDAO.getOne(vente);
			}
			else {
				businessException.ajouterErreur(CodesResultatBLL.ENCHERE_MISE_REFUSEE);
				throw businessException;
			}
		}
		else {
			throw businessException;
		}
		
		return vente;
	}

	public List<Vente> terminerVentes() throws BusinessException {

		// TODO NOUVEAU SELECT lister toutes les ventes dont la date dépasse la date du jour
		// SELECT * FROM VENTE WHERE dateFinEnchere = hier
		
		LocalDateTime hier = LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(1),LocalTime.MIDNIGHT);
		List<Vente> venteTerminees = venteDAO.getAll();
		
		// pour toutes les ventes terminées, supprimer les enchères dont qui ne sont pas la dernière et recréditer l'utilisateur lié
		for (Vente vente : venteTerminees) {
			for (Enchere enchere : vente.getListeEncheres()) {
				if (!enchere.equals(vente.getMaxEnchere())) {
					enchere.getAcheteur().setCredit(enchere.getAcheteur().getCredit() + enchere.getMise());

					enchere = enchereDAO.deleteOne(enchere);
				}
			}
		}
		
		return venteTerminees;
	}

	@Override
	public Vente annulerEnchere(Vente vente, Utilisateur utilisateurSession) throws BusinessException {
		BusinessException businessException = new BusinessException();

		utilisateurSession = this.validerUtilisateur(utilisateurSession, businessException);
		vente = this.validerVente(vente, businessException);
		
		
		if (!businessException.hasErreurs()) {
			
			Enchere enchere = null; 
			for (Enchere e : vente.getListeEncheres()) {
				if (e.getAcheteur().equals(utilisateurSession)) {
					enchere = e;
					break;
				}
			}
			
			if (enchere != null) {
				
				// créditer l'utilisateur
				enchere.getAcheteur().setCredit(enchere.getAcheteur().getCredit() + enchere.getMise());
				
				enchere = enchereDAO.deleteOne(enchere);
			} else {
				businessException.ajouterErreur(CodesResultatBLL.ENCHERE_SUPPRESSION_REFUSEE);
				throw businessException;
			}
		} else {
			throw businessException;
		}

		return vente;
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

	private LocalDateTime validerDateFinEnchere(LocalDateTime dateFinEncheres, BusinessException businessException) {
		if (dateFinEncheres == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DATE_FIN_ENCHERE_VIDE);
		} else {
			// si date inférieur à aujourd'hui erreur
			if (dateFinEncheres.isBefore(LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")),LocalTime.MIDNIGHT))) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_VENTE_DATE_FIN_ENCHERE_IMPOSSIBLE);
			}
		}

		return dateFinEncheres;
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

	private Vente validerVente(Vente vente, BusinessException businessException) {
		if (vente == null) {
			businessException.ajouterErreur(CodesResultatBLL.VENTE_INCONNUE);
		}
		
		return vente;
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
	
	private Categorie validerCategorie(Categorie categorie, BusinessException businessException) {
		if (categorie == null) {
			businessException.ajouterErreur(CodesResultatBLL.CATEGORIE_INCONNUE);
		}
		
		return categorie;
	}

}
