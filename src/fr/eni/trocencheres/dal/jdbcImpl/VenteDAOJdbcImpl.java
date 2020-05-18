package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Retrait;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.VenteDAO;

public class VenteDAOJdbcImpl implements VenteDAO {

	private static String SELECT_ALL_VENTES = "SELECT ventes.no_vente,ventes.nom_article,ventes.description,ventes.date_fin_encheres,prix_initial,ventes.no_utilisateur AS vendeur, ventes.no_categorie,c.libelle, ventes.retrait_article,u.pseudo AS pseudo_vendeur,u.nom AS nom_vendeur,u.prenom AS prenom_vendeur, u.email AS email_vendeur,u.telephone AS tel_vendeur, u.rue AS rue_vendeur, u.code_postal AS cp_vendeur, u.ville AS ville_vendeur,u.mot_de_passe AS mdp_vendeur, u.credit AS credit_vendeur,u.isActif AS isActif_vendeur, u.administrateur AS admin_vendeur, encheres.date_enchere, encheres.no_utilisateur AS acheteur,encheres.mise,t.pseudo AS pseudo_acheteur, t.nom AS nom_acheteur, t.prenom AS prenom_acheteur, t.email AS email_acheteur, t.telephone AS tel_acheteur,t.rue AS rue_acheteur, t.code_postal AS cp_acheteur,t.ville AS ville_acheteur,t.mot_de_passe AS mdp_acheteur,t.credit AS credit_acheteur, t.isActif AS isActif_acheteur, t.administrateur AS admin_acheteur,retraits.no_vente AS no_vente_rtr, retraits.rue AS rue_rtr, retraits.code_postal AS cp_rtr,retraits.ville AS ville_rtr FROM VENTES " + 
			"	INNER JOIN utilisateurs as u" + 
			"    ON u.no_utilisateur = ventes.no_utilisateur " + 
			"	 LEFT JOIN encheres " + 
			"    ON ventes.no_vente = encheres.no_vente " + 
			"    LEFT JOIN utilisateurs as t " + 
			"    ON t.no_utilisateur = encheres.no_utilisateur " + 
			"    LEFT JOIN retraits" + 
			"    ON retraits.no_vente = ventes.no_vente " + 
			"    LEFT JOIN categories as c " + 
			"    ON c.no_categorie= ventes.no_categorie " + 
			"    ORDER BY ventes.no_vente,  encheres.mise DESC";
	
	private static String INSERT_UNE_VENTE = "";
	private static String UPDATE_UNE_VENTE ="";
	private static String SELECT_UNE_VENTE="";
	private static String DELETE_UNE_VENTE="";
	
	
   
	
	@Override
	public List<Vente> getAll() throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_VENTES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				
				if(listeVentes.isEmpty() || listeVentes.get(listeVentes.size()-1).getNoVente()!= rs.getInt("no_vente") ) {
					
					// Creation nouvelle objet vente
					Vente vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nom_article"));
					vente.setDescription(rs.getString("description"));
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setRetraitArticle(rs.getBoolean("retrait_article"));
					vente.setDateFinEncheres(new java.sql.Timestamp(rs.getDate("date_fin_encheres").getTime()).toLocalDateTime());
					
					// creation et set du vendeur
					Utilisateur vendeur = new Utilisateur();
					vendeur.setAdministrateur(rs.getBoolean("admin_vendeur"));
					vendeur.setCodePostal(rs.getString("cp_vendeur"));
					vendeur.setPseudo(rs.getString("pseudo_vendeur"));
					vendeur.setNom(rs.getString("nom_vendeur"));
					vendeur.setPrenom(rs.getString("prenom_vendeur"));
					vendeur.setEmail(rs.getString("email_vendeur"));
					vendeur.setTelephone(rs.getString("tel_vendeur"));
					vendeur.setCredit(rs.getInt("credit_vendeur"));
					vendeur.setMotDePasse(rs.getString("mdp_vendeur"));
					vendeur.setRue(rs.getString("rue_vendeur"));
					vendeur.setVille(rs.getString("ville_vendeur"));
					vendeur.setNoUtilisateur(rs.getInt("vendeur"));
					vendeur.setActif(rs.getBoolean("isActif_vendeur"));

					vente.setVendeur(vendeur);
					
					Categorie categorie = new Categorie();
					categorie.setLibelle(rs.getString("libelle"));
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					
					vente.setCategorie(categorie);
					
					if (rs.getString("rue_rtr")!= null && rs.getInt("no_vente_rtr")== vente.getNoVente()) {
						Retrait retrait = new Retrait();
						retrait.setRue(rs.getString("rue_rtr"));
						retrait.setCodePostal(rs.getString("cp_rtr"));
						retrait.setVille(rs.getString("ville_rtr"));
						
						vente.setRetrait(retrait);
					}
										
					
					
					
					//Creation enchère + addEnchere() s'il y en a : 
					if(rs.getDate("date_enchere")!=null) {
						
					Enchere enchere = new Enchere();
					Utilisateur acheteur = new Utilisateur();
					acheteur.setAdministrateur(rs.getBoolean("admin_acheteur"));
					acheteur.setCodePostal(rs.getString("cp_acheteur"));
					acheteur.setPseudo(rs.getString("pseudo_acheteur"));
					acheteur.setNom(rs.getString("nom_acheteur"));
					acheteur.setPrenom(rs.getString("prenom_acheteur"));
					acheteur.setEmail(rs.getString("email_acheteur"));
					acheteur.setTelephone(rs.getString("tel_acheteur"));
					acheteur.setCredit(rs.getInt("credit_acheteur"));
					acheteur.setMotDePasse(rs.getString("mdp_acheteur"));
					acheteur.setRue(rs.getString("rue_acheteur"));
					acheteur.setVille(rs.getString("ville_acheteur"));
					acheteur.setNoUtilisateur(rs.getInt("vendeur"));
					acheteur.setActif(rs.getBoolean("isActif_acheteur"));
					
					enchere.setAcheteur(acheteur);
					enchere.setDateEnchere(new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
					enchere.setMise(rs.getInt("mise"));
					enchere.setVente(vente);			
					}
					
				
					listeVentes.add(vente);
					
				}else {
					//creation d'unne nouvelle enchère et ajout à la liste d'enchère du dernier objet vente
					Enchere enchere = new Enchere();
					Utilisateur acheteur = new Utilisateur();
					acheteur.setAdministrateur(rs.getBoolean("admin_acheteur"));
					acheteur.setCodePostal(rs.getString("cp_acheteur"));
					acheteur.setPseudo(rs.getString("pseudo_acheteur"));
					acheteur.setNom(rs.getString("nom_acheteur"));
					acheteur.setPrenom(rs.getString("prenom_acheteur"));
					acheteur.setEmail(rs.getString("email_acheteur"));
					acheteur.setTelephone(rs.getString("tel_acheteur"));
					acheteur.setCredit(rs.getInt("credit_acheteur"));
					acheteur.setMotDePasse(rs.getString("mdp_acheteur"));
					acheteur.setRue(rs.getString("rue_acheteur"));
					acheteur.setVille(rs.getString("ville_acheteur"));
					acheteur.setNoUtilisateur(rs.getInt("vendeur"));
					acheteur.setActif(rs.getBoolean("isActif_acheteur"));
					
					enchere.setAcheteur(acheteur);
					enchere.setDateEnchere(new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
					enchere.setMise(rs.getInt("mise"));
					enchere.setVente(listeVentes.get(listeVentes.size()-1));
					
					
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIES_ECHEC);
			throw businessException;
		}

		
		
		return listeVentes;
	}

	@Override
	public Vente insertOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente updateOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente getOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente deleteOne(Vente entity) throws BusinessException {
		return null;
	}

	

//Si tout coché : getALL
//Si mes enchère en cours   : GET ALL ENCHERE WHERE no_utilisateur = acheteur  > liste des ventes GETALL where  no_vente = .. AND no_vente=... && date enchère non terminée.
//Si mes acquisition : get ALL ventes where date_fin inférieur à aujourd'hui  + ajouter à la liste si 1er acheteur dans la liste = no_utilisateur.
//Autre enchère : get all enchere  where  acheteur != no_utilisateur > GET ALL where vendeur not no_utilisateur  ou 
	
	@Override
	public List<Vente> getVentesFiltered(Utilisateur utilisateur, boolean isMesVentes, boolean isMesEncheres,boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)
			throws BusinessException {
		
		StringBuffer sb = new StringBuffer();
		
		
		return null;
	}

}
