/**
 * 
 */
package fr.eni.trocencheres.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fmoto
 *
 */
public class Vente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer noVente;
	private String nomArticle;
	private String description;
	private Date dateFinEncheres;
	private Integer miseAPrix;
	
	
	private Categorie categorie;
	private Utilisateur vendeur;
	private Retrait retrait;
	
	
	public Vente(Integer noVente, String nomArticle, String description, Date dateFinEncheres, Integer miseAPrix, Utilisateur vendeur, Retrait retrait) {
		this(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, retrait);
		this.noVente = noVente;
	}

	public Vente(String nomArticle, String description, Date dateFinEncheres, Integer miseAPrix, Utilisateur vendeur, Retrait retrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.vendeur = vendeur;
		this.retrait = retrait;
	}
	
	public Vente() {
	}

	
	
	/**
	 * @return the noVente
	 */
	public int getNoVente() {
		return noVente;
	}

	/**
	 * @param noVente the noVente to set
	 */
	public void setNoVente(Integer noVente) {
		this.noVente = noVente;
	}

	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateFinEncheres
	 */
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}

	/**
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	/**
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}

	/**
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "Vente [noVente=" + noVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ "]";
	}
	
}
