package fr.eni.trocencheres.dal.jdbcImpl;

import java.util.List;

import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static String SELECT_ALL_UTILISATEURS ="SELECT * FROM UTILISATEURS";
	private static String INSERT_UN_UTILISATEUR = "INSERT INTO utilisateurs (code_postal,credit,email,mot_de_passe,nom,prenom,pseudo,rue,telephone,ville) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	
	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return null;
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
	
}
