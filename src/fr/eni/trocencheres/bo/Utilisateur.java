package fr.eni.trocencheres.bo;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int noUtilisateur, telephone, codePostal, credit;
	private String pseudo, nom, prenom, email, rue, ville, motDePasse;
	private boolean administrateur;

	public Utilisateur() {
	}

	public Utilisateur(int noUtilisateur, int telephone, int codePostal, int credit, String pseudo, String nom,
			String prenom, String email, String rue, String ville, String motDePasse, boolean administrateur) {
		this.noUtilisateur = noUtilisateur;
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
	}

	public Utilisateur(int telephone, int codePostal, int credit, String pseudo, String nom, String prenom,
			String email, String rue, String ville, String motDePasse, boolean administrateur) {
		super();
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
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", telephone=" + telephone + ", codePostal=" + codePostal
				+ ", credit=" + credit + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", rue=" + rue + ", ville=" + ville + ", motDePasse=" + motDePasse + ", administrateur="
				+ administrateur + "]";
	}

	
}
