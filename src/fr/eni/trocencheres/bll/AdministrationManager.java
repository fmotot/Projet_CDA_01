package fr.eni.trocencheres.bll;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Utilisateur;
/**
 * 
 * @author fmoto
 *
 */
public interface AdministrationManager {

	/**
	 * ID 3001 : Supprimer des comptes utilisateurs 
	 * Suppression du compte d'un utilisateur par un compte administrateur
	 * @param administrateur
	 * @param utilisateurASupprimer
	 * @return l'utilisateur qui a été supprimer
	 * @throws BusinessException
	 */
	Utilisateur supprimerCompteUtilisateur(Utilisateur administrateur, Utilisateur utilisateurASupprimer) throws BusinessException;

	/**
	 * ID 3002 : Désactiver un compte utilisateur
	 *  Désactivation du compte d'un utilisateur par un compte administrateur
	 * @param administrateur
	 * @param utilisateurADesactiver
	 * @return l'utilisateur qui a été désactivé
	 * @throws BusinessException
	 */
	Utilisateur desactiverCompteUtilisateur(Utilisateur administrateur, Utilisateur utilisateurADesactiver) throws BusinessException;
}
