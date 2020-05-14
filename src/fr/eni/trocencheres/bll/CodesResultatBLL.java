package fr.eni.trocencheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec mot de passe incorrect
	 */
	public static final int LOGIN_MOT_DE_PASSE_INCORRECT = 20001;
	
	/**
	 * Echec mot de passe incorrect
	 */
	public static final int LOGIN_INCORRECT = 20001;
	
	/**
	 * Echec lors de la sélection du type d'encodage
	 */
	public static final int ERREUR_SELECTION_ENCODAGE = 20002;
	
	/**
	 * Echec téléphone déjà utilisé
	 */
	public static final int REGLE_UTILISATEUR_TELEPHONE_DOUBLON=21001;
	
	/**
	 * Echec téléphone ne doit pas dépassé 15 caractères
	 */
	public static final int REGLE_UTILISATEUR_TELEPHONE_TROP_LONG=21002;

	/**
	 * Echec code postal est obligatoire
	 */
	public static final int REGLE_UTILISATEUR_CODE_POSTAL_VIDE = 21003;

	/**
	 * Echec code postal trop long
	 */
	public static final int REGLE_UTILISATEUR_CODE_POSTAL_TROP_LONG = 21004;

	/**
	 * Echec pseudo obligatoire
	 */
	public static final int REGLE_UTILISATEUR_PSEUDO_VIDE = 21005;

	/**
	 * Echec pseudo trop long +30
	 */
	public static final int REGLE_UTILISATEUR_PSEUDO_TROP_LONG = 21006;
	
	/**
	 * Echec nom obligatoire
	 */
	public static final int REGLE_UTILISATEUR_NOM_VIDE = 21007;
	
	/**
	 * Echec nom trop long +30
	 */
	public static final int REGLE_UTILISATEUR_NOM_TROP_LONG = 21008;
	
	
	/**
	 * Echec prenom obligatoire
	 */
	public static final int REGLE_UTILISATEUR_PRENOM_VIDE = 21009;
	
	/**
	 * Echec prenom trop long +30
	 */
	public static final int REGLE_UTILISATEUR_PRENOM_TROP_LONG = 21010;
	
	/**
	 * Echec email obligatoire
	 */
	public static final int REGLE_UTILISATEUR_EMAIL_VIDE = 21011;
	
	/**
	 * Echec email trop long +50
	 */
	public static final int REGLE_UTILISATEUR_EMAIL_TROP_LONG = 21012;

	/**
	 * Echec pas un email
	 */
	public static final int REGLE_UTILISATEUR_EMAIL_NON_VALIDE = 21013;
	
	/**
	 * Echec rue obligatoire
	 */
	public static final int REGLE_UTILISATEUR_RUE_VIDE = 21014;
	
	/**
	 * Echec rue trop long +30
	 */
	public static final int REGLE_UTILISATEUR_RUE_TROP_LONG = 21015;
	
	/**
	 * Echec ville obligatoire
	 */
	public static final int REGLE_UTILISATEUR_VILLE_VIDE = 21016;
	
	/**
	 * Echec ville trop long +30
	 */
	public static final int REGLE_UTILISATEUR_VILLE_TROP_LONG = 21017;
}
