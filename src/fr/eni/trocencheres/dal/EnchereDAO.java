package fr.eni.trocencheres.dal;

import java.util.List;

import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Utilisateur;


public interface EnchereDAO extends DAO<Enchere> {
	
	public List<Enchere> getAllEncheresbyUtilisateur(Utilisateur utilisateur);
	
}
