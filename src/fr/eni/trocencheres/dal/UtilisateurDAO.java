package fr.eni.trocencheres.dal;

import fr.eni.trocencheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {

	/**
	 * Permet de savoir si un numero de téléphone existe déjà. 
	 * @return boolean
	 *  
	**/
	public boolean isTelephoneExist(String telephone);
	public Utilisateur selectByLogin(String login);
}
