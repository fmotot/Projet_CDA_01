package fr.eni.trocencheres.bll;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Utilisateur;

public interface UtilisateurManager {

	/**
	 * ID 1001 : Se connecter 
	 * Recupère l'utilisateur qui a le login fourni et verifie la correspondance du mot de passe
	 * @param login
	 * @param motDePasse
	 * @return l'utilisateur
	 * @throws BusinessException
	 */
	Utilisateur login(String login, String motDePasse) throws BusinessException;

	
	/**
	 * ID 1004 : Créer un compte 
	 * Creation du compte utilisateur avec les informations fournies 
	 * @throws BusinessException
	 * @return l'utilisateur
	 * @param utilisateur
	 */
	Utilisateur creerCompteUtilisateur(String telephone, String codePostal, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse) throws BusinessException;

	/**
	 * ID 1005 : Supprimer mon compte
	 * Suppression du compte de l'utilisateur connecté
	 * @param utilisateur
	 * @return l'utilisateur qui a été supprimer
	 * @throws BusinessException
	 */
	Utilisateur supprimerMonCompte(Utilisateur utilisateurConnecte) throws BusinessException;
	
	/**
	 * ID 1007 : Afficher un profil
	 *  Affichage des information de l'utilisateur en fonction de son pseudo
	 * @param pseudo
	 * @return l'utilisateur ayant le pseudo fourni
	 * @throws BusinessException
	 */
	Utilisateur afficherUtilisateur(String pseudo) throws BusinessException;
	
	/**
	 * ID 1009 et 1003 : Modifier/Gérer mon profil
	 * Modifie les informations d'un utilisateur déjà existant
	 * @param utilisateur
	 * @param isNouveauMotDePasse	true si l'utilisateur a changé son mot de passe
	 * @return l'utilisateur modifié
	 * @throws BusinessException
	 */
	Utilisateur modifierMonCompte(Utilisateur utilisateur, boolean isNouveauMotDePasse) throws BusinessException;
		
	
}
