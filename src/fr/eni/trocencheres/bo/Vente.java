/**
 * 
 */
package fr.eni.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fmoto
 * @author Macorigh Rudy // getMaxEnchere // set et getClassement
 */
public class Vente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer noVente;
	private String nomArticle;
	private String description;
	private LocalDateTime dateFinEncheres;
	private Integer miseAPrix;
	private boolean retraitArticle;

	private Categorie categorie;
	private Utilisateur vendeur;
	private Retrait retrait;

	private Integer classement;

	private List<Enchere> listeEncheres;

	public Vente() {
		listeEncheres = new ArrayList<Enchere>();
	}

	public Vente(String nomArticle, String description, LocalDateTime dateFinEncheres, Integer miseAPrix,
			Utilisateur vendeur, Retrait retrait, boolean retraitArticle, Categorie categorie,
			List<Enchere> listeEncheres) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.vendeur = vendeur;
		this.retrait = retrait;
		this.listeEncheres = listeEncheres;
		this.categorie = categorie;
	}

	public Vente(Integer noVente, String nomArticle, String description, LocalDateTime dateFinEncheres,
			Integer miseAPrix, Utilisateur vendeur, Retrait retrait, boolean retraitArticle, Categorie categorie,
			List<Enchere> listeEncheres) {
		this(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, retrait, retraitArticle, categorie,
				listeEncheres);
		this.noVente = noVente;
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
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
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

	/**
	 * @return the retraitArticle
	 */
	public boolean isRetraitArticle() {
		return retraitArticle;
	}

	/**
	 * @param retraitArticle the retraitArticle to set
	 */
	public void setRetraitArticle(boolean retraitArticle) {
		this.retraitArticle = retraitArticle;
	}

	/**
	 * @return the listeEncheres
	 */
	public List<Enchere> getListeEncheres() {
		List<Enchere> result = null;
		if (this.listeEncheres != null) {
			result = new ArrayList<Enchere>();
			for (Enchere enchere : this.listeEncheres) {
				result.add(enchere);
			}
		}

		return result;
	}

	public void addEnchere(Enchere enchere) {
		if (this.equals(enchere.getVente())) {
			this.listeEncheres.add(enchere);
		}
	}

	public Enchere getMaxEnchere() {

		// si requete ORDER BY DESC
		if (!listeEncheres.isEmpty()) {
			Enchere derniereEnchere = this.listeEncheres.get(0);
			return derniereEnchere;
		} else {
			return null;
		}

	}

	public Integer getClassement() {
		return classement;
	}

	public void setClassement(Utilisateur utilisateurConnecte) {
		Integer classement = 0;
		// avec une requete SQL en DESC
		for (Enchere enchere : this.listeEncheres) {
			if (enchere.getAcheteur().equals(utilisateurConnecte)) {
				classement = listeEncheres.indexOf(enchere);
				break;
			}
		}
		this.classement = classement;
	}

	@Override
	public String toString() {
		return "Vente [noVente=" + noVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + "]";
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;

		if (object instanceof Vente && ((Vente) object).getNoVente() == this.getNoVente()) {
			equal = true;
		}

		return equal;
	}
}
