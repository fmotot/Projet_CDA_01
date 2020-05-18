package fr.eni.trocencheres.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

public interface VenteDAO  extends DAO<Vente>{

	
	/**
	 * 
	 * ID 2003	Lister les ventes
	 * ID 2009	Pagination
	 * Effectue une recherche avec les critères suivants
	 * @param isMesVentes		true si la recherche comprends mes ventes
	 * @param isMesEncheres		true si la recherche comprends mes enchères
	 * @param isMesAcquisitions true si la recherche comprends mes acquisitions 
	 * @param isAutresEncheres	true si la recherche comprends les autres enchères
	 * @param recherche			le mot à rechercher
	 * @param categorie			la catégorie à filtrer ou null pour toutes les catégories
	 * @return					la liste des Vente
	 * @throws BusinessException
	 */
	public List<Vente> getVentesFiltered(Utilisateur utilisateur,boolean isMesVentes, boolean isMesEncheres, boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)throws BusinessException;
	
	/**
	 * Effectue une recherche pour récupérer les Ventes à une date donnée
	 * @param dateFinEnchere
	 * @return
	 * @throws BusinessException
	 */
	public List<Vente> getVentesByDateFinEnchere(LocalDateTime dateFinEnchere) throws BusinessException;
	
	
}
