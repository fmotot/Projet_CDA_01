package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Retrait;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.VenteDAO;

/**
 * 
 * @author jean getAll, getVentesFiltered
 * @author fmoto xxxOne
 *
 */
public class VenteDAOJdbcImpl implements VenteDAO {

	private static String SELECT_ALL_VENTES = "SELECT ventes.no_vente,ventes.nom_article,ventes.description,ventes.date_fin_encheres,prix_initial,ventes.no_utilisateur AS vendeur, ventes.no_categorie,c.libelle, ventes.retrait_article,u.pseudo AS pseudo_vendeur,u.nom AS nom_vendeur,u.prenom AS prenom_vendeur, u.email AS email_vendeur,u.telephone AS tel_vendeur, u.rue AS rue_vendeur, u.code_postal AS cp_vendeur, u.ville AS ville_vendeur,u.mot_de_passe AS mdp_vendeur, u.credit AS credit_vendeur,u.isActif AS isActif_vendeur, u.administrateur AS admin_vendeur, encheres.date_enchere, encheres.no_utilisateur AS acheteur,encheres.mise,t.pseudo AS pseudo_acheteur, t.nom AS nom_acheteur, t.prenom AS prenom_acheteur, t.email AS email_acheteur, t.telephone AS tel_acheteur,t.rue AS rue_acheteur, t.code_postal AS cp_acheteur,t.ville AS ville_acheteur,t.mot_de_passe AS mdp_acheteur,t.credit AS credit_acheteur, t.isActif AS isActif_acheteur, t.administrateur AS admin_acheteur,retraits.no_vente AS no_vente_rtr, retraits.rue AS rue_rtr, retraits.code_postal AS cp_rtr,retraits.ville AS ville_rtr FROM VENTES "
			+ "	INNER JOIN utilisateurs as u" + "    ON u.no_utilisateur = ventes.no_utilisateur "
			+ "	 LEFT JOIN encheres " + "    ON ventes.no_vente = encheres.no_vente "
			+ "    LEFT JOIN utilisateurs as t " + "    ON t.no_utilisateur = encheres.no_utilisateur "
			+ "    LEFT JOIN retraits" + "    ON retraits.no_vente = ventes.no_vente "
			+ "    LEFT JOIN categories as c " + "    ON c.no_categorie= ventes.no_categorie ";
	private static String ORDER_BY_VENTE_ENCHERE_DESC = "    ORDER BY ventes.no_vente,  encheres.mise DESC";

	private static String INSERT_UNE_VENTE = "INSERT INTO ventes (nom_article, description, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?);";
	private static String UPDATE_UNE_VENTE = "UPDATE ventes SET nom_article=?, description=?, date_fin_encheres=?, prix_initial=?, no_categories=?, retrait_article=? WHERE no_vente=?;";
	private static String SELECT_UNE_VENTE = "SELECT ventes.no_vente,ventes.nom_article,ventes.description,ventes.date_fin_encheres,prix_initial,ventes.no_utilisateur AS vendeur, ventes.no_categorie,c.libelle, ventes.retrait_article,u.pseudo AS pseudo_vendeur,u.nom AS nom_vendeur,u.prenom AS prenom_vendeur, u.email AS email_vendeur,u.telephone AS tel_vendeur, u.rue AS rue_vendeur, u.code_postal AS cp_vendeur, u.ville AS ville_vendeur,u.mot_de_passe AS mdp_vendeur, u.credit AS credit_vendeur,u.isActif AS isActif_vendeur, u.administrateur AS admin_vendeur, encheres.date_enchere, encheres.no_utilisateur AS acheteur,encheres.mise,t.pseudo AS pseudo_acheteur, t.nom AS nom_acheteur, t.prenom AS prenom_acheteur, t.email AS email_acheteur, t.telephone AS tel_acheteur,t.rue AS rue_acheteur, t.code_postal AS cp_acheteur,t.ville AS ville_acheteur,t.mot_de_passe AS mdp_acheteur,t.credit AS credit_acheteur, t.isActif AS isActif_acheteur, t.administrateur AS admin_acheteur,retraits.no_vente AS no_vente_rtr, retraits.rue AS rue_rtr, retraits.code_postal AS cp_rtr,retraits.ville AS ville_rtr FROM VENTES "
			+ "	INNER JOIN utilisateurs as u" + "    ON u.no_utilisateur = ventes.no_utilisateur "
			+ "	 LEFT JOIN encheres " + "    ON ventes.no_vente = encheres.no_vente "
			+ "    LEFT JOIN utilisateurs as t " + "    ON t.no_utilisateur = encheres.no_utilisateur "
			+ "    LEFT JOIN retraits" + "    ON retraits.no_vente = ventes.no_vente "
			+ "    LEFT JOIN categories as c " + "    ON c.no_categorie= ventes.no_categorie "
			+ " WHERE ventes.no_vente=?";
	private static String SELECT_UNE_VENTE_BY_DATE_FIN_ENCHERE = "SELECT ventes.no_vente,ventes.nom_article,ventes.description,ventes.date_fin_encheres,prix_initial,ventes.no_utilisateur AS vendeur, ventes.no_categorie,c.libelle, ventes.retrait_article,u.pseudo AS pseudo_vendeur,u.nom AS nom_vendeur,u.prenom AS prenom_vendeur, u.email AS email_vendeur,u.telephone AS tel_vendeur, u.rue AS rue_vendeur, u.code_postal AS cp_vendeur, u.ville AS ville_vendeur,u.mot_de_passe AS mdp_vendeur, u.credit AS credit_vendeur,u.isActif AS isActif_vendeur, u.administrateur AS admin_vendeur, encheres.date_enchere, encheres.no_utilisateur AS acheteur,encheres.mise,t.pseudo AS pseudo_acheteur, t.nom AS nom_acheteur, t.prenom AS prenom_acheteur, t.email AS email_acheteur, t.telephone AS tel_acheteur,t.rue AS rue_acheteur, t.code_postal AS cp_acheteur,t.ville AS ville_acheteur,t.mot_de_passe AS mdp_acheteur,t.credit AS credit_acheteur, t.isActif AS isActif_acheteur, t.administrateur AS admin_acheteur,retraits.no_vente AS no_vente_rtr, retraits.rue AS rue_rtr, retraits.code_postal AS cp_rtr,retraits.ville AS ville_rtr FROM VENTES "
			+ "	INNER JOIN utilisateurs as u" + "    ON u.no_utilisateur = ventes.no_utilisateur "
			+ "	 LEFT JOIN encheres " + "    ON ventes.no_vente = encheres.no_vente "
			+ "    LEFT JOIN utilisateurs as t " + "    ON t.no_utilisateur = encheres.no_utilisateur "
			+ "    LEFT JOIN retraits" + "    ON retraits.no_vente = ventes.no_vente "
			+ "    LEFT JOIN categories as c " + "    ON c.no_categorie= ventes.no_categorie "
			+ " WHERE ventes.date_fin_encheres=?";
	private static String DELETE_UNE_VENTE = "DELETE FROM ventes WHERE no_vente=?";

	private static String SELECT_UN_RETRAIT = "SELECT no_vente, rue, code_postal, ville FROM retraits WHERE no_vente=?";
	private static String INSERT_UN_RETRAIT = "INSERT INTO retraits (no_vente, rue, code_postal, ville) VALUES (?,?,?,?)";
	private static String UPDATE_UN_RETRAIT = "UPDATE retrairs SET rue=?, code_postal=?, ville=? WHERE no_vente=?";
	private static String DELETE_UN_RETRAIT = "DELETE FROM retraits WHERE no_vente=?";

	private static String DELETE_UNE_ENCHERE = "DELETE FROM encheres WHERE no_vente=?";

	private static String UPDATE_CREDIT_UTILISATEUR = "UPDATE utilisateurs SET credit=? WHERE no_utilisateur= ?";

	@Override
	public List<Vente> getAll() throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_VENTES + ORDER_BY_VENTE_ENCHERE_DESC);
			ResultSet rs = pstmt.executeQuery();

			listeVentes = listerVentes(rs);

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_VENTES_ECHEC);
			throw businessException;

		}

		return listeVentes;
	}

	@Override
	public List<Vente> getVentesFiltered(Utilisateur utilisateur, boolean isMesVentes, boolean isMesEncheres,
			boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)
			throws BusinessException {

		System.out.println("recherche : " + recherche + " \n categorie : " + categorie);
		System.out.println(utilisateur);
		System.out.println("IsMesVentes " + isMesVentes);
		System.out.println("IsMesEncheres " + isMesEncheres);
		System.out.println("IsMesAcquiisitions " + isMesAcquisitions);
		System.out.println("IsAutresEncheres " + isAutresEncheres);
		List<Vente> listeVentesFiltre = new ArrayList<Vente>();
		StringBuffer sb = new StringBuffer();
		Connection cnx;
		Set<String> NoVentesFiltered = new HashSet<String>();
		try {
			cnx = ConnectionProvider.getConnection();
			ResultSet rs = null;

			if (isMesVentes && isMesEncheres && isMesAcquisitions && isAutresEncheres) {

			} else {
				PreparedStatement ps = null;
				if (isMesVentes) {
					ps = cnx.prepareStatement("SELECT no_vente FROM ventes WHERE no_utilisateur = ?");
					ps.setInt(1, utilisateur.getNoUtilisateur());
					rs = ps.executeQuery();
					while (rs.next()) {
						NoVentesFiltered.add(rs.getInt(1) + "");

					}
				}

				if (isMesEncheres) {
					LocalDateTime aujourdhui = LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")),
							LocalTime.MIDNIGHT);
					Date date = java.sql.Date.valueOf(aujourdhui.toLocalDate());
					ps = cnx.prepareStatement(
							"SELECT ventes.no_vente, GROUP_CONCAT(encheres.no_utilisateur ORDER BY encheres.mise DESC) AS acheteurs FROM ventes"
									+ "	LEFT JOIN encheres " + "	ON ventes.no_vente = encheres.no_vente"
									+ " WHERE date_fin_encheres > ? " + "	GROUP BY ventes.no_vente");
					ps.setDate(1, date);
					rs = ps.executeQuery();

					while (rs.next()) {
						String str = rs.getString("acheteurs");
						if (str == null) {

						} else {
							String[] no_acheteurs = str.split(",");
							boolean isAutreEnchere = false;
							for (String string : no_acheteurs) {

								if (string.equals(utilisateur.getNoUtilisateur() + "")) {
									isAutreEnchere = true;
								}

							}

							if (isAutreEnchere) {
								NoVentesFiltered.add(rs.getInt("no_vente") + "");
							}

						}
					}
				}

				if (isMesAcquisitions) {
					LocalDateTime aujourdhui = LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")),
							LocalTime.MIDNIGHT);
					Date date = java.sql.Date.valueOf(aujourdhui.toLocalDate());
					ps = cnx.prepareStatement(
							"SELECT ventes.no_vente, GROUP_CONCAT(encheres.no_utilisateur ORDER BY encheres.mise DESC)AS acheteurs FROM ventes"
									+ "	LEFT JOIN encheres " + "	ON ventes.no_vente = encheres.no_vente"
									+ " WHERE date_fin_encheres <= ? AND ventes.no_utilisateur != ?" + "	GROUP BY ventes.no_vente");

					ps.setDate(1, date);
					ps.setInt(2, utilisateur.getNoUtilisateur());
					rs = ps.executeQuery();
					while (rs.next()) {
						String str = rs.getString("acheteurs");
						if (str == null) {

						} else {
							String[] no_acheteurs = str.split(",");
							boolean isUneAcquisition = false;

							if (no_acheteurs[0].equals(utilisateur.getNoUtilisateur() + "")) {
								isUneAcquisition = true;
							}

							if (isUneAcquisition) {
								NoVentesFiltered.add(rs.getInt("no_vente") + "");
							}

						}
					}
				}
				if (isAutresEncheres) {
					LocalDateTime aujourdhui = LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")),
							LocalTime.MIDNIGHT);
					Date date = java.sql.Date.valueOf(aujourdhui.toLocalDate());
					ps = cnx.prepareStatement(
							"SELECT ventes.no_vente, GROUP_CONCAT(encheres.no_utilisateur ORDER BY encheres.mise DESC) AS acheteurs FROM ventes"
									+ "	LEFT JOIN encheres " + "	ON ventes.no_vente = encheres.no_vente"
									+ " WHERE date_fin_encheres > ? " + "	GROUP BY ventes.no_vente");
					ps.setDate(1, date);
					rs = ps.executeQuery();
					while (rs.next()) {

						String str = rs.getString("acheteurs");
						if (str == null) {
							NoVentesFiltered.add(rs.getInt("no_vente") + "");
						} else {
							String[] no_acheteurs = str.split(",");
							boolean isAutreEnchere = true;
							if (utilisateur != null) {
								for (String string : no_acheteurs) {
									if (string.equals(utilisateur.getNoUtilisateur() + "")) {
										isAutreEnchere = false;
									}
								}
							}
							if (isAutreEnchere) {
								NoVentesFiltered.add(rs.getInt("no_vente" + "") + "");
							}

						}
					}
				}

			}

			String strVentesAAfficher = String.join(",", NoVentesFiltered);
			sb.append(SELECT_ALL_VENTES);

			if (isMesVentes && isMesEncheres && isMesAcquisitions && isAutresEncheres) {

				if (recherche != null || categorie != null) {
					sb.append(" WHERE ");
					if (recherche != null) {
						sb.append(" ventes.description LIKE '%" + recherche + "%'");
					}

					if (recherche != null && categorie != null) {
						sb.append(" AND ");
					}

					if (categorie != null) {
						sb.append(" ventes.no_categorie = " + categorie.getNoCategorie());
					}
				}
				sb.append(ORDER_BY_VENTE_ENCHERE_DESC);
				PreparedStatement pstmt = cnx.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
			} else {

				sb.append(" WHERE ventes.no_vente IN (?) ");

				if (recherche != null) {
					sb.append(" AND ventes.description LIKE '%" + recherche + "%'");
				}

				if (categorie != null) {
					sb.append(" AND ventes.no_categorie = " + categorie.getNoCategorie());
				}

				sb.append(ORDER_BY_VENTE_ENCHERE_DESC);
				PreparedStatement pstmt = cnx.prepareStatement(sb.toString());
				pstmt.setString(1, strVentesAAfficher);
				rs = pstmt.executeQuery();
			}

			listeVentesFiltre = listerVentes(rs);

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_VENTES_ECHEC);
			throw businessException;
		}

		return listeVentesFiltre;
	}

	private List<Vente> listerVentes(ResultSet rs) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		try {
			while (rs.next()) {

				if (listeVentes.isEmpty()
						|| listeVentes.get(listeVentes.size() - 1).getNoVente() != rs.getInt("no_vente")) {

					// Creation nouvelle objet vente
					Vente vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nom_article"));
					vente.setDescription(rs.getString("description"));
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setRetraitArticle(rs.getBoolean("retrait_article"));
					vente.setDateFinEncheres(
							new java.sql.Timestamp(rs.getDate("date_fin_encheres").getTime()).toLocalDateTime());

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

					if (rs.getString("rue_rtr") != null && rs.getInt("no_vente_rtr") == vente.getNoVente()) {
						Retrait retrait = new Retrait();
						retrait.setRue(rs.getString("rue_rtr"));
						retrait.setCodePostal(rs.getString("cp_rtr"));
						retrait.setVille(rs.getString("ville_rtr"));

						vente.setRetrait(retrait);
					}

					// Creation enchère + addEnchere() s'il y en a :
					if (rs.getDate("date_enchere") != null) {

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
						acheteur.setNoUtilisateur(rs.getInt("acheteur"));
						acheteur.setActif(rs.getBoolean("isActif_acheteur"));

						enchere.setAcheteur(acheteur);
						enchere.setDateEnchere(
								new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
						enchere.setMise(rs.getInt("mise"));
						enchere.setVente(vente);
					}

					listeVentes.add(vente);

				} else {
					// creation d'unne nouvelle enchère et ajout à la liste d'enchère du dernier
					// objet vente
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
					acheteur.setNoUtilisateur(rs.getInt("acheteur"));
					acheteur.setActif(rs.getBoolean("isActif_acheteur"));

					enchere.setAcheteur(acheteur);
					enchere.setDateEnchere(
							new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
					enchere.setMise(rs.getInt("mise"));
					enchere.setVente(listeVentes.get(listeVentes.size() - 1));

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_VENTES_ECHEC);
			throw businessException;
		}

		return listeVentes;
	}

	@Override
	public Vente insertOne(Vente entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;

		if (entity == null) {
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			// insertion de la vente
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UNE_VENTE, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, entity.getNomArticle());
			pstmt.setString(2, entity.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(entity.getDateFinEncheres().toLocalDate()));
			pstmt.setInt(4, entity.getMiseAPrix());
			pstmt.setInt(5, entity.getVendeur().getNoUtilisateur());
			pstmt.setInt(6, entity.getCategorie().getNoCategorie());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				entity.setNoVente(rs.getInt(1));
			}

			// insertion du retrait si nécessaire
			if (entity.getRetrait() != null) {
				pstmt = cnx.prepareStatement(INSERT_UN_RETRAIT);

				pstmt.setInt(1, entity.getNoVente());
				pstmt.setString(2, entity.getRetrait().getRue());
				pstmt.setString(3, entity.getRetrait().getCodePostal());
				pstmt.setString(4, entity.getRetrait().getVille());

				pstmt.executeUpdate();
			}

			cnx.commit();
		} catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}

			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public Vente updateOne(Vente entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;

		if (entity == null) {
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);

			// Update de la vente
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UNE_VENTE);

			pstmt.setString(1, entity.getNomArticle());
			pstmt.setString(2, entity.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(entity.getDateFinEncheres().toLocalDate()));
			pstmt.setInt(4, entity.getMiseAPrix());
			pstmt.setInt(5, entity.getCategorie().getNoCategorie());
			pstmt.setBoolean(6, entity.isRetraitArticle());
			pstmt.setInt(7, entity.getNoVente());

			pstmt.executeUpdate();

			// On vérifie si un retrait existe déjà pour savoir si on doit insert/update ou
			// delete
			pstmt = cnx.prepareStatement(SELECT_UN_RETRAIT);

			pstmt.setInt(1, entity.getNoVente());

			ResultSet rs = pstmt.executeQuery();

			Retrait retrait = null;
			while (rs.next()) {
				retrait = new Retrait();

				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setRue(rs.getString("rue"));
				retrait.setVille(rs.getString("ville"));
			}

			// si un retrait a été indiqué
			if (entity.getRetrait() != null) {
				// s'il n'y avait pas de retrait avant on l'ajoute
				if (retrait == null) {
					pstmt = cnx.prepareStatement(INSERT_UN_RETRAIT);

					pstmt.setInt(1, entity.getNoVente());
					pstmt.setString(2, entity.getRetrait().getRue());
					pstmt.setString(3, entity.getRetrait().getCodePostal());
					pstmt.setString(4, entity.getRetrait().getVille());

					pstmt.executeUpdate();
				}
				// s'il y avait déjà un retrait on le met à jour
				else {
					pstmt = cnx.prepareStatement(UPDATE_UN_RETRAIT);

					pstmt.setString(1, entity.getRetrait().getRue());
					pstmt.setString(2, entity.getRetrait().getCodePostal());
					pstmt.setString(3, entity.getRetrait().getVille());
					pstmt.setInt(4, entity.getNoVente());

					pstmt.executeUpdate();
				}
			}
			// si aucun retrait n'est indiqué
			else {
				// s'il y avait un retrait avant, on le supprime
				if (retrait != null) {
					pstmt = cnx.prepareStatement(DELETE_UN_RETRAIT);

					pstmt.setInt(1, entity.getNoVente());

					pstmt.executeUpdate();
				}
			}

			cnx.commit();
		} catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
				businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
				throw businessException;
			}

			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public Vente getOne(Vente entity) throws BusinessException {
		Vente vente = null;

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UNE_VENTE + ORDER_BY_VENTE_ENCHERE_DESC);
			pstmt.setInt(1, entity.getNoVente());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				if (vente == null) {
					// Creation nouvelle objet vente
					vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nom_article"));
					vente.setDescription(rs.getString("description"));
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setRetraitArticle(rs.getBoolean("retrait_article"));
					vente.setDateFinEncheres(
							new java.sql.Timestamp(rs.getDate("date_fin_encheres").getTime()).toLocalDateTime());

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

					if (rs.getString("rue_rtr") != null && rs.getInt("no_vente_rtr") == vente.getNoVente()) {
						Retrait retrait = new Retrait();
						retrait.setRue(rs.getString("rue_rtr"));
						retrait.setCodePostal(rs.getString("cp_rtr"));
						retrait.setVille(rs.getString("ville_rtr"));

						vente.setRetrait(retrait);
					}

					// Creation enchère + addEnchere() s'il y en a :
					if (rs.getDate("date_enchere") != null) {

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
						acheteur.setNoUtilisateur(rs.getInt("acheteur"));
						acheteur.setActif(rs.getBoolean("isActif_acheteur"));

						enchere.setAcheteur(acheteur);
						enchere.setDateEnchere(
								new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
						enchere.setMise(rs.getInt("mise"));

						enchere.setVente(vente);
					}
				} else {
					if (rs.getDate("date_enchere") != null) {

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
						acheteur.setNoUtilisateur(rs.getInt("acheteur"));
						acheteur.setActif(rs.getBoolean("isActif_acheteur"));

						enchere.setAcheteur(acheteur);
						enchere.setDateEnchere(
								new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
						enchere.setMise(rs.getInt("mise"));

						enchere.setVente(vente);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_VENTE_ECHEC);
			throw businessException;
		}

		return vente;
	}

	@Override
	public Vente deleteOne(Vente entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;

		// Doit supprimer la vente de la BDD ainsi que les enchères qui la concernent et
		// mettre à jour les utilisateurs liés

		if (entity == null) {
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);

			// suppression de la vente
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UNE_VENTE);

			pstmt.setInt(1, entity.getNoVente());

			pstmt.executeUpdate();

			// suppression des enchères liés à la vente
			pstmt = cnx.prepareStatement(DELETE_UNE_ENCHERE);

			pstmt.setInt(1, entity.getNoVente());

			pstmt.executeUpdate();

			// update du crédit des utilisateurs liés aux enchères sur la vente
			for (Enchere e : entity.getListeEncheres()) {
				pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR);

				pstmt.setInt(1, e.getAcheteur().getCredit());
				pstmt.setInt(2, e.getAcheteur().getNoUtilisateur());

				pstmt.executeUpdate();
			}

			cnx.commit();

		} catch (Exception e) {
			try {
				cnx.rollback();
			} catch (Exception e1) {

				e1.printStackTrace();
				businessException.ajouterErreur(CodesResultatDAL.ROLLBACK_ENCHERE_ECHEC);
				throw businessException;
			}

			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ENCHERE_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public List<Vente> getVentesByDateFinEnchere(LocalDateTime dateFinEnchere) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx
					.prepareStatement(SELECT_UNE_VENTE_BY_DATE_FIN_ENCHERE + ORDER_BY_VENTE_ENCHERE_DESC);

			pstmt.setDate(1, (java.sql.Date.valueOf(dateFinEnchere.toLocalDate())));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				if (listeVentes.isEmpty()
						|| listeVentes.get(listeVentes.size() - 1).getNoVente() != rs.getInt("no_vente")) {

					// Creation nouvelle objet vente
					Vente vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nom_article"));
					vente.setDescription(rs.getString("description"));
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setRetraitArticle(rs.getBoolean("retrait_article"));
					vente.setDateFinEncheres(
							new java.sql.Timestamp(rs.getDate("date_fin_encheres").getTime()).toLocalDateTime());

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

					if (rs.getString("rue_rtr") != null && rs.getInt("no_vente_rtr") == vente.getNoVente()) {
						Retrait retrait = new Retrait();
						retrait.setRue(rs.getString("rue_rtr"));
						retrait.setCodePostal(rs.getString("cp_rtr"));
						retrait.setVille(rs.getString("ville_rtr"));

						vente.setRetrait(retrait);
					}

					// Creation enchère + addEnchere() s'il y en a :
					if (rs.getDate("date_enchere") != null) {

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
						acheteur.setNoUtilisateur(rs.getInt("acheteur"));
						acheteur.setActif(rs.getBoolean("isActif_acheteur"));

						enchere.setAcheteur(acheteur);
						enchere.setDateEnchere(
								new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
						enchere.setMise(rs.getInt("mise"));
						enchere.setVente(vente);
					}

					listeVentes.add(vente);

				} else {
					// creation d'unne nouvelle enchère et ajout à la liste d'enchère du dernier
					// objet vente
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
					acheteur.setNoUtilisateur(rs.getInt("acheteur"));
					acheteur.setActif(rs.getBoolean("isActif_acheteur"));

					enchere.setAcheteur(acheteur);
					enchere.setDateEnchere(
							new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
					enchere.setMise(rs.getInt("mise"));
					enchere.setVente(listeVentes.get(listeVentes.size() - 1));

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_VENTES_ECHEC);
			throw businessException;
		}

		return listeVentes;
	}

}
