package fr.eni.trocencheres.bll;

/**
 * 
 * @author fmoto
 *
 */
public abstract class BLLFactory {

	public static AdministrationManager getAdministrationManager() {
		return null;
	}
	
	public static VenteManager getVenteManager() {
		return new VenteManagerImpl();
	}
	
	public static UtilisateurManager getUtilisateurManager() {
		return new UtilisateurManagerImpl();
	}
	
	public static CategorieManager getCategorieManager() {
		return new CategorieManagerImpl();
	}
}
