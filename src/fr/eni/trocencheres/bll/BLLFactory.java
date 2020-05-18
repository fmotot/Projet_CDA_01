package fr.eni.trocencheres.bll;

public abstract class BLLFactory {

	public static AdministrationManager getAdministrationManager() {
		return null;
	}
	
	public static VenteManager getVenteManager() {
		return null;
	}
	
	public static UtilisateurManager getUtilisateurManager() {
		return new UtilisateurManagerImpl();
	}
	
	public static CategorieManager getCategorieManager() {
		return new CategorieManagerImpl();
	}
}
