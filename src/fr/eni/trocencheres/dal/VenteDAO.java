package fr.eni.trocencheres.dal;

import java.util.List;

import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

public interface VenteDAO  extends DAO<Vente>{

	public List<Vente> getAllVentesbyUtilisateur(Utilisateur utilisateur);
	public List<Vente> getAllVentesByUtilisateurByMotCleByCategorie(Utilisateur utilisateur,String motCle,Categorie categorie);
	
	
	
	
}
