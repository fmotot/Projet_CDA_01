package fr.eni.trocencheres.dal;

import java.util.List;

import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

public interface VenteDAO  extends DAO<Vente>{

	public List<Vente> getAllVentesbyUtilisateur(Utilisateur utilisateur);
	
}
