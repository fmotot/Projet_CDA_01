package fr.eni.trocencheres.dal;

import fr.eni.trocencheres.dal.jdbcImpl.CategorieDAOJdbcImpl;
import fr.eni.trocencheres.dal.jdbcImpl.EnchereDAOJdbcImpl;
import fr.eni.trocencheres.dal.jdbcImpl.UtilisateurDAOJdbcImpl;
import fr.eni.trocencheres.dal.jdbcImpl.VenteDAOJdbcImpl;
/**
 * 
 * @author jeanr
 *
 */
public abstract class DAOFactory {

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static VenteDAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
	}
}
	
