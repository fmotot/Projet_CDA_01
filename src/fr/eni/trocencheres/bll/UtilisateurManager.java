package fr.eni.trocencheres.bll;

import fr.eni.trocencheres.bo.Utilisateur;

public interface UtilisateurManager {

	/**
	 * @param utilisateur
	 *  ajout d'un utilisateur a la BDD
	 */
	public void addUtilisateur(Utilisateur utilisateur);
	
	/**
	 * @param noUtilisateur
	 *  Suppression d'un utilisateur via son id
	 */
	public void deleteUtilisateur(Integer noUtilisateur);
	
	/**
	 * @param pseudo
	 * Selection d'un utilisateur via son pseudo
	 */
	public void selectUtilisateur(String pseudo);
	
	/**
	 * @param utilisateur
	 * Mise a jour des information de l'utilisateur
	 */
	public void updateUtilisateur(Utilisateur utilisateur);
	
}
