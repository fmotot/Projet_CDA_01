package fr.eni.trocencheres.dal;

import java.util.List;

import fr.eni.trocencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	
	public List<Utilisateur> getAllUtilisateurs();
	public void insertUtilisateur(Utilisateur utilisateur);
	public void updateUtilisateur(Utilisateur utilisateur);
	public Utilisateur getUtilisateur(Integer idUtilisateur);
	public Utilisateur deleteUtilisateur(Integer idUtilisateur);
	
	

}
