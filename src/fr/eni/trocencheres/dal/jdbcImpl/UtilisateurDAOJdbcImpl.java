package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static String SELECT_ALL_UTILISATEURS ="SELECT * FROM UTILISATEURS";
	private static String INSERT_UN_UTILISATEUR = "INSERT INTO utilisateurs (code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static String UPDATE_UN_UTILISATEUR= "UPDATE utilisateurs SET (administrateur = ?,code_postal=?,credit=?,email=?,mot_de_passe=?,nom=?,prenom=?,pseudo=?,rue=?,telephone=?,ville=? WHERE no_utilisateur= ?";
	private static String SELECT_UN_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur= ?";
	private static String DELETE_UN_UTILISATEUR="DELETE FROM UTILISATEURS WHERE no_utilisateur= ?";
	private static String IS_TELEPHONE_EXIST="SELECT * FROM UTILISATEURS WHERE telephone=?";
	private static String SELECT_BY_LOGIN="SELECT * FROM UTILISATEURS WHERE pseudo= ? OR email=? ";
	
	
	@Override
	public List<Utilisateur> getAll() {
		
		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		
		try {
			Connection cnx= ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_UTILISATEURS);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Utilisateur user= new Utilisateur();
				
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
			
			
			
		} catch (SQLException e) {
			System.out.println("erreur lors de la selection des utilisateurs");
			e.printStackTrace();
		}
		
		
		
		return listUtilisateurs;
	}

	@Override
	public Utilisateur insertOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur updateOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur deleteOne(Utilisateur entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTelephoneExist(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur selectByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
