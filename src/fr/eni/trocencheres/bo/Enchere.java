package fr.eni.trocencheres.bo;

import java.io.Serializable;
import java.util.Date;

public class Enchere implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateEnchere;
	private Utilisateur acheteur;
	private Vente vente;
	private Integer mise;
	
	/**
	 * 
	 */
	public Enchere() {
	}
	
	/**
	 * La date est initialisé à l'instanciation (Dixit Jean)
	 * @param acheteur
	 * @param vente
	 * @param mise
	 */
	public Enchere(Utilisateur acheteur, Vente vente, int mise) {
		this.dateEnchere = new Date();
		this.acheteur = acheteur;
		this.vente = vente;
		this.mise = mise;
	}
	
	/**
	 * @param dateEnchere
	 * @param acheteur
	 * @param vente
	 * @param mise
	 */
	public Enchere(Date dateEnchere, Utilisateur acheteur, Vente vente, int mise) {
		this.dateEnchere = dateEnchere;
		this.acheteur = acheteur;
		this.vente = vente;
		this.mise = mise;
	}
	
	
	
	

	/**
	 * @return the dateEnchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(Date dateEnchere) {
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
}
