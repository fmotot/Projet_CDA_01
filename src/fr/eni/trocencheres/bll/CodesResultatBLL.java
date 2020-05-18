package fr.eni.trocencheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	
	
	// Codes résultat pour les Utilisateurs
	
	/**
	 * Echec mot de passe incorrect
	 */
	public static final int MOT_DE_PASSE_INCORRECT = 20001;
	
	/**
	 * Echec mot de passe incorrect
	 */
	public static final int LOGIN_INCORRECT = 20002;
	
	/**
	 * Echec lors de la sélection du type d'encodage
	 */
	public static final int ERREUR_SELECTION_ENCODAGE = 22002;
	
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

	/**
	 * Echec mot de passe trop court -8
	 */
	public static final int REGLE_UTILISATEUR_MOT_DE_PASSE_TROP_COURT = 21018;

	
	
	
	// Codes résultat pour les Ventes 

	/**
	 * Echec du numéro de vente
	 */
	public static final int VENTE_INCONNUE = 23001;

	/**
	 * Echec suppresion de l'enchère refusée
	 */
	public static final int ENCHERE_SUPPRESSION_REFUSEE = 23002;

	/**
	 * Echec utilisateur inconnu
	 */
	public static final int UTILISATEUR_INCONNU = 23003;

	/**
	 * Echec enchère inconnu
	 */
	public static final int ENCHERE_INCONNUE = 23004;

	/**
	 * Echec nom d'article obligatoire
	 */
	public static final int REGLE_VENTE_NOM_ARTICLE_VIDE = 23005;

	/**
	 * Echec nom d'article trop long +30
	 */
	public static final int REGLE_VENTE_NOM_ARTICLE_TROP_LONG = 23006;

	/**
	 * Echec description obligatoire
	 */
	public static final int REGLE_VENTE_DESCRIPTION_VIDE = 23007;

	/**
	 * Echec description trop long +300
	 */
	public static final int REGLE_VENTE_DESCRIPTION_TROP_LONG = 23008;

	/**
	 * Echec date de fin d'enchère obligatoire
	 */
	public static final int REGLE_VENTE_DATE_FIN_ENCHERE_VIDE = 23009;

	/**
	 * Echec date de fin d'enchère forcément ultérieur à la date du jour
	 */
	public static final int REGLE_VENTE_DATE_FIN_ENCHERE_IMPOSSIBLE = 23010;

	/**
	 * Echec prix initial obligatoire
	 */
	public static final int REGLE_VENTE_PRIX_INITIAL_VIDE = 23011;

	/**
	 * Echec le prix ne peux pas être négatif
	 */
	public static final int REGLE_VENTE_PRIX_INITIAL_NEGATIF = 23012;

	/**
	 * Echec tous les champs du retrait doivent être rempli si le retrait ce fait ailleurs qu'au domicile
	 */
	public static final int REGLE_RETRAIT_INCOMPLET = 23014;

	/**
	 * Echec rue trop long +30
	 */
	public static final int REGLE_RETRAIT_RUE_TROP_LONG = 23015;

	/**
	 * Echec ville trop long +30
	 */
	public static final int REGLE_RETRAIT_VILLE_TROP_LONG = 23016;

	/**
	 * Echec code postal trop long +30
	 */
	public static final int REGLE_RETRAIT_CODE_POSTAL_TROP_LONG = 23017;

	/**
	 * Echec mise trop basse
	 */
	public static final int ENCHERE_MISE_REFUSEE = 23018;

	/**
	 * Echec Annulation refusée, le vendeur ne correspond pas
	 */
	public static final int VENTE_ANNULATION_REFUSEE = 23019;

	/**
	 * Echec categorie inconnue
	 */
	public static final int CATEGORIE_INCONNUE = 23020;
}
