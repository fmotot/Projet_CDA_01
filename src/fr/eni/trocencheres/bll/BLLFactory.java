package fr.eni.trocencheres.bll;

public abstract class BLLFactory {

	public static AdministrationManager getAdministrationManager() {
		return null;
	}
	
	public static EnchereManager getEnchereManager() {
		return null;
	}
	
	public static UtilisateurManager getUtilisateurManager() {
		return null;
	}
}
