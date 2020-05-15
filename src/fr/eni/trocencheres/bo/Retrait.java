package fr.eni.trocencheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable {
	private static final long serialVersionUID = 1L;

	String rue;
	String ville;
	String codePostal;
	

	public Retrait() {
	}
	
	public Retrait(String rue, String ville, String codePostal) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
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
	
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", ville=" + ville + ", codePostal=" + codePostal + "]";
	}
	
	
}
