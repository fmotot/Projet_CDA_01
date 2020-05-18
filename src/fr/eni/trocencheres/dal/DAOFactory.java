package fr.eni.trocencheres.dal;

import fr.eni.trocencheres.dal.jdbcImpl.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static CategorieDAO getCategorieDAO() {
		return null;
	}
	
	public static EnchereDAO getEnchereDAO() {
		return null;
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static VenteDAO getVenteDAO() {
		return null;
	}
}
	
