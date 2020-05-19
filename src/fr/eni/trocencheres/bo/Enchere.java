package fr.eni.trocencheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 
 * @author fmoto
 *
 */
public class Enchere implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime dateEnchere;
	private Utilisateur acheteur;
	private Vente vente;
	private Integer mise;
	
	/**
	 * 
	 */
	public Enchere() {
	}
	
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", acheteur=" + acheteur.getNoUtilisateur() + ", vente=" + vente.getNoVente() + ", mise=" + mise
				+ "]";
	}

	/**
	 * La date est initialisé à l'instanciation (Dixit Jean)
	 * @param acheteur
	 * @param vente
	 * @param mise
	 */
	public Enchere(Utilisateur acheteur, Vente vente, int mise) {
		this.dateEnchere = LocalDateTime.now(ZoneId.of("Europe/Paris"));;
		this.acheteur = acheteur;
		this.setVente(vente);
		this.mise = mise;
	}
	
	/**
	 * @param dateEnchere
	 * @param acheteur
	 * @param vente
	 * @param mise
	 */
	public Enchere(LocalDateTime dateEnchere, Utilisateur acheteur, Vente vente, int mise) {
		this.dateEnchere = dateEnchere;
		this.acheteur = acheteur;
		this.setVente(vente);
		this.mise = mise;
	}
	
	
	
	

	/**
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	
	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	
	/**
	 * @return the acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	
	/**
	 * @param acheteur the acheteur to set
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	
	/**
	 * @return the vente
	 */
	public Vente getVente() {
		return vente;
	}
	
	/**
	 * @param vente the vente to set
	 */
	public void setVente(Vente vente) {
		this.vente = vente;
		vente.addEnchere(this);
	}
	
	/**
	 * @return the mise
	 */
	public int getMise() {
		return mise;
	}
	
	/**
	 * @param mise the mise to set
	 */
	public void setMise(int mise) {
		this.mise = mise;
	}
	
	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		
		if (object instanceof Enchere 
				&& this.getAcheteur().getNoUtilisateur() == ((Enchere)object).getAcheteur().getNoUtilisateur()
				&& this.getVente().getNoVente() == ((Enchere)object).getVente().getNoVente()) {
			equal = true;
		}
		
		return equal;
	}
	
}
