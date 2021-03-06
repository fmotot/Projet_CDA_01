package fr.eni.trocencheres.bo;

import java.io.Serializable;
/**
 * 
 * @author Macorigh Rudy
 * @author fmoto	sur equals
 *
 */
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer noUtilisateur;
	private String telephone;
	private Integer credit;
	
	private String codePostal;  
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String rue;
	private String ville;
	private String motDePasse;
	
	private boolean actif;
	private boolean administrateur;

	public Utilisateur() {
	}

	public Utilisateur(String telephone, String codePostal, Integer credit, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, boolean administrateur, boolean actif) {
		this.telephone = telephone;
		this.codePostal = codePostal;
		this.credit = credit;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.administrateur = administrateur;
		this.actif = actif;
	}

	public Utilisateur(Integer noUtilisateur, String telephone, String codePostal, Integer credit, String pseudo, String nom,
			String prenom, String email, String rue, String ville, String motDePasse, boolean administrateur, boolean actif) {
		this(telephone, codePostal, credit, pseudo, nom, prenom, email, rue, ville, motDePasse, administrateur, actif);
		this.noUtilisateur = noUtilisateur;
	}
	
	/**
	 * @return the noUtilisateur
	 */
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the credit
	 */
	public Integer getCredit() {
		return credit;
	}

	/**
	 * @param credit the credit to set
	 */
	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}

	/**
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", telephone=" + telephone + ", codePostal=" + codePostal
				+ ", credit=" + credit + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", rue=" + rue + ", ville=" + ville + ", motDePasse=" + motDePasse + ", administrateur="
				+ administrateur + "]";
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		
		if (object instanceof Utilisateur && ((Utilisateur)object).getNoUtilisateur().equals(this.getNoUtilisateur())) {
			equal = true;
		}
		
		return equal;
	}
	
}
