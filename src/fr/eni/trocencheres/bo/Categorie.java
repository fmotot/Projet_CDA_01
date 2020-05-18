package fr.eni.trocencheres.bo;

import java.io.Serializable;
/**
 * 
 * @author Macorigh Rudy
 *
 */
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer noCategorie;
	String libelle;
	
	public Categorie() {
	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	public Categorie(Integer noCategorie, String libelle) {
		this(libelle);
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}

	
	

}
