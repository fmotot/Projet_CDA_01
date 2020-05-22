package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.UtilisateurDAO;
/**
 * 
 * @author Jean
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static String SELECT_ALL_UTILISATEURS = "SELECT * FROM UTILISATEURS";
	private static String INSERT_UN_UTILISATEUR = "INSERT INTO utilisateurs (code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static String UPDATE_UN_UTILISATEUR = "UPDATE utilisateurs SET code_postal=?,credit=?,email=?,mot_de_passe=?,nom=?,prenom=?,pseudo=?,rue=?,telephone=?,ville=?,administrateur=?,isActif=? WHERE no_utilisateur= ?";
	private static String SELECT_UN_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur= ?";
	private static String DELETE_UN_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur= ?";
	private static String IS_TELEPHONE_EXIST = "SELECT * FROM UTILISATEURS WHERE telephone=?";
	private static String SELECT_BY_LOGIN = "SELECT * FROM UTILISATEURS WHERE pseudo= ? OR email= ? ";
	private static String IS_PSEUDO_EXIST = "SELECT * FROM UTILISATEURS WHERE pseudo= ? ";
	private static String IS_EMAIL_EXIST = "SELECT * FROM UTILISATEURS WHERE email= ? ";
	@Override
	public List<Utilisateur> getAll() throws BusinessException {

		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_UTILISATEURS);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Utilisateur user = new Utilisateur();

				user.setAdministrateur(rs.getBoolean("administrateur"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setCredit(rs.getInt("credit"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setRue(rs.getString("rue"));
				user.setVille(rs.getString("ville"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));

				listUtilisateurs.add(user);
			}
			
			pstmt.close();
			cnx.close();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;

		}

		return listUtilisateurs;
	}

	@Override
	public Utilisateur insertOne(Utilisateur entity) throws BusinessException {
		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UN_UTILISATEUR,
			PreparedStatement.RETURN_GENERATED_KEYS);

			// code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville
			pstmt.setString(1, entity.getCodePostal());
			pstmt.setInt(2, entity.getCredit());
			pstmt.setString(3, entity.getEmail());
			pstmt.setString(4, entity.getMotDePasse());
			pstmt.setString(5, entity.getNom());
			pstmt.setString(6, entity.getPrenom());
			pstmt.setString(7, entity.getPseudo());
			pstmt.setString(8, entity.getRue());
			pstmt.setString(9, entity.getTelephone());
			pstmt.setString(10, entity.getVille());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				entity.setNoUtilisateur(rs.getInt(1));
			}
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public Utilisateur updateOne(Utilisateur entity) throws BusinessException {

		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UN_UTILISATEUR);

			pstmt.setString(1, entity.getCodePostal());
			pstmt.setInt(2, entity.getCredit());
			pstmt.setString(3, entity.getEmail());
			pstmt.setString(4, entity.getMotDePasse());
			pstmt.setString(5, entity.getNom());
			pstmt.setString(6, entity.getPrenom());
			pstmt.setString(7, entity.getPseudo());
			pstmt.setString(8, entity.getRue());
			pstmt.setString(9, entity.getTelephone());
			pstmt.setString(10, entity.getVille());
			pstmt.setBoolean(11, entity.isAdministrateur());
			pstmt.setBoolean(12, entity.isActif());
			pstmt.setInt(13, entity.getNoUtilisateur());

			pstmt.executeUpdate();
			
			pstmt.close();
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}

		return entity;
	}

	@Override
	public Utilisateur getOne(Utilisateur entity) throws BusinessException {
		Utilisateur user = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UN_UTILISATEUR);
			pstmt.setInt(1, entity.getNoUtilisateur());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				user = new Utilisateur();
				user.setAdministrateur(rs.getBoolean("administrateur"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setCredit(rs.getInt("credit"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setRue(rs.getString("rue"));
				user.setVille(rs.getString("ville"));
				user.setActif(rs.getBoolean("isActif"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}

		return user;
	}

	@Override
	public Utilisateur deleteOne(Utilisateur entity) throws BusinessException {
		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		} else {

			try {
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_UN_UTILISATEUR);
				pstmt.setInt(1, entity.getNoUtilisateur());

				pstmt.executeUpdate();
				pstmt.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_UTILISATEUR_ERREUR);
				throw businessException;
			}
		}
		
		return entity;
	}

	@Override
	public boolean isTelephoneExist(String telephone) throws BusinessException {
		Connection cnx;
		boolean isTelephoneExist = false;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(IS_TELEPHONE_EXIST);
			pstmt.setString(1, telephone);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				isTelephoneExist = true;
			}
			
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_LECTURE_TELEPHONE);
			throw businessException;
		}

		return isTelephoneExist;
	}
	
	
	public boolean isPseudoExist(String pseudo) throws BusinessException {
		Connection cnx;
		boolean isPseudoExist = false;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(IS_PSEUDO_EXIST);
			pstmt.setString(1, pseudo);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				isPseudoExist = true;
			}
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_LECTURE_PSEUDO);
			throw businessException;
		}

		return isPseudoExist;
	}
	
	 
	 
	 public boolean isEmailExist(String email) throws BusinessException {
			Connection cnx;
			boolean isEmailExist = false;
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(IS_EMAIL_EXIST);
				pstmt.setString(1, email);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					isEmailExist = true;
				}
				pstmt.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.ECHEC_LECTURE_EMAIL);
				throw businessException;
			}

			return isEmailExist;
		}

	@Override
	public Utilisateur selectByLogin(String login) throws BusinessException {
		Utilisateur user = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_LOGIN);
			pstmt.setString(1, login);
			pstmt.setString(2, login);

			ResultSet rs = pstmt.executeQuery();

			
			
				
			while (rs.next()) {

				user = new Utilisateur();
				user.setAdministrateur(rs.getBoolean("administrateur"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setCredit(rs.getInt("credit"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setRue(rs.getString("rue"));
				user.setVille(rs.getString("ville"));
				user.setActif(rs.getBoolean("isActif"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));

			}
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_LECTURE_BY_LOGIN);
			throw businessException;
		}
		
			

		return user;
	}

}
