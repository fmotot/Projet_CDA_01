package fr.eni.trocencheres.bo;

import java.io.Serializable;

public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	int noCategorie;
	String libelle;
	
	public Categorie() {
	}
	
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}

	
	

}
