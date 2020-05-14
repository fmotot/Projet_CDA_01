package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO{
	private static String SELECT_ALL_ENCHERES="SELECT * FROM encheres";
	private static String INSERT_UNE_ENCHERE="INSERT INTO encheres (date_enchere,mise,no_utilisateur,no_vente) VALUES (?,?,?,?) ";
	private static String UPDATE_UNE_ENCHERE="UPDATE encheres SET date_enchere=?,mise=? WHERE no_utilisateur=? AND no_vente=?";
	private static String SELECT_UNE_ENCHERE="SELECT * FROM encheres WHERE no_utilisateur=? AND no_vente=?";
	private static String DELETE_UNE_ENCHERE="DELETE FROM encheres WHERE no_utilisateur=? AND no_vente=?";
	


	@Override
	public List<Enchere> getAll() throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		
		
		return listeEnchere;
	}

	@Override
	public Enchere insertOne(Enchere entity) throws BusinessException {
		
		return null;
	}

	@Override
	public Enchere updateOne(Enchere entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere getOne(Enchere entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere deleteOne(Enchere entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
