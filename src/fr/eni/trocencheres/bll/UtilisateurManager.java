package fr.eni.trocencheres.bll;

import fr.eni.trocencheres.bo.Utilisateur;

public interface UtilisateurManager {

	
	Utilisateur  login(String login, String motDePasse);
}
