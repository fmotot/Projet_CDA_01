package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.DAOFactory;
import fr.eni.trocencheres.dal.EnchereDAO;
import fr.eni.trocencheres.dal.UtilisateurDAO;
import fr.eni.trocencheres.dal.VenteDAO;
/**
 * 
 * @author Jean
 *
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static String SELECT_ALL_ENCHERES = "SELECT * FROM encheres";
	private static String INSERT_UNE_ENCHERE = "INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente) VALUES (?,?,?,?) ";
	private static String UPDATE_UNE_ENCHERE = "UPDATE encheres SET date_enchere=?,mise=? WHERE no_utilisateur=? AND no_vente=?";
	private static String SELECT_UNE_ENCHERE = "SELECT * FROM encheres WHERE no_utilisateur=? AND no_vente=?";
	private static String DELETE_UNE_ENCHERE = "DELETE FROM encheres WHERE no_utilisateur=? AND no_vente=?";

	private static String UPDATE_CREDIT_UTILISATEUR = "UPDATE utilisateurs SET credit=? WHERE no_utilisateur= ?";

	@Override
	public List<Enchere> getAll() throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		VenteDAO venteDAO = DAOFactory.getVenteDAO();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ENCHERES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Enchere enchere = new Enchere();

				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				acheteur = utilisateurDAO.getOne(acheteur);
				enchere.setAcheteur(acheteur);

				Vente vente = new Vente();
				vente.setNoVente(rs.getInt("no_vente"));
				vente = venteDAO.getOne(vente);
				enchere.setVente(vente);

				enchere.setDateEnchere(new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
				enchere.setMise(rs.getInt("mise"));

				listeEnchere.add(enchere);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIES_ECHEC);
			throw businessException;
		}

		return listeEnchere;
	}
//date_enchere,mise,no_utilisateur,no_vente

	@Override
	public Enchere insertOne(Enchere entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;

		// Doit insérer l'enchère et mettre à jour l'utilisateur lié

		if (entity == null) {
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try {

			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);

			// insertion de l'enchère
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UNE_ENCHERE);

			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(entity.getDateEnchere()));
			pstmt.setInt(2, entity.getMise());
			pstmt.setInt(3, entity.getAcheteur().getNoUtilisateur());
			pstmt.setInt(4, entity.getVente().getNoVente());

			pstmt.executeUpdate();

			// update du crédit de l'utilisateur
			pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR);

			pstmt.setInt(1, entity.getAcheteur().getCredit());
			pstmt.setInt(2, entity.getAcheteur().getNoUtilisateur());

			pstmt.executeUpdate();

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
	public Enchere updateOne(Enchere entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		
		if (entity == null) {
			
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			
			// update de l'enchère
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UNE_ENCHERE);

			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(entity.getDateEnchere()));
			pstmt.setInt(2, entity.getMise());
			pstmt.setInt(3, entity.getAcheteur().getNoUtilisateur());
			pstmt.setInt(4, entity.getVente().getNoVente());

			pstmt.executeUpdate();

			// update du crédit de l'utilisateur
			pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR);

			pstmt.setInt(1, entity.getAcheteur().getCredit());
			pstmt.setInt(2, entity.getAcheteur().getNoUtilisateur());

			pstmt.executeUpdate();
			
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
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public Enchere getOne(Enchere entity) throws BusinessException {
		Enchere enchere = null;
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		VenteDAO venteDAO = DAOFactory.getVenteDAO();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UNE_ENCHERE);
			pstmt.setInt(1, entity.getAcheteur().getNoUtilisateur());
			pstmt.setInt(2, entity.getVente().getNoVente());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				enchere = new Enchere();

				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				acheteur = utilisateurDAO.getOne(acheteur);
				enchere.setAcheteur(acheteur);

				Vente vente = new Vente();
				vente.setNoVente(rs.getInt("no_vente"));
				vente = venteDAO.getOne(vente);
				enchere.setVente(vente);

				enchere.setDateEnchere(new java.sql.Timestamp(rs.getDate("date_enchere").getTime()).toLocalDateTime());
				enchere.setMise(rs.getInt("mise"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ENCHERE_ECHEC);
			throw businessException;
		}

		return enchere;
	}

	@Override
	public Enchere deleteOne(Enchere entity) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Connection cnx = null;
		
		// Doit supprimer l'enchère et mettre à jour l'utilisateur lié
		
		if (entity == null) {
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}
		
		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			
			// suppression de l'enchère
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UNE_ENCHERE);
			
			pstmt.setInt(1, entity.getAcheteur().getNoUtilisateur());
			pstmt.setInt(2, entity.getVente().getNoVente());

			pstmt.executeUpdate();
			
			// update du crédit de l'utilisateur
			pstmt = cnx.prepareStatement(UPDATE_CREDIT_UTILISATEUR);

			pstmt.setInt(1, entity.getAcheteur().getCredit());
			pstmt.setInt(2, entity.getAcheteur().getNoUtilisateur());

			pstmt.executeUpdate();

			cnx.commit();
		} catch (SQLException e) {
			
			try {
				cnx.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}
			
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ENCHERE_ECHEC);
			throw businessException;
		}

		return entity;
	}

}

























