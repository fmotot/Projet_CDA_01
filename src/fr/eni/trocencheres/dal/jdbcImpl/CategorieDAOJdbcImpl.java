package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.dal.CategorieDAO;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
/**
 * 
 * @author Jean
 *
 */
public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static String SELECT_ALL_CATEGORIES="SELECT * FROM categories";
	private static String INSERT_UNE_CATEGORIE="INSERT INTO categories (libelle) VALUES (?) ";
	private static String UPDATE_UNE_CATEGORIE="UPDATE categories SET libelle=? WHERE no_categorie=?";
	private static String SELECT_UNE_CATEGORIE="SELECT * FROM categories WHERE no_categorie=?";
	private static String DELETE_UNE_CATEGORIE="DELETE FROM categories WHERE no_categorie=?";
	

	@Override
	public List<Categorie> getAll() throws BusinessException {
		List<Categorie>  listeCategories = new ArrayList<Categorie>();
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Categorie categorie = new Categorie();
				
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
				listeCategories.add(categorie);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIES_ECHEC);
			throw businessException;
		}
		
		
		return listeCategories;
	}

	@Override
	public Categorie insertOne(Categorie entity) throws BusinessException {
		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try {
			
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UNE_CATEGORIE,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, entity.getLibelle());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				entity.setNoCategorie(rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		return entity;
	}

	@Override
	public Categorie updateOne(Categorie entity) throws BusinessException {
		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_UNE_CATEGORIE);
			
			pstmt.setString(1, entity.getLibelle());
			pstmt.setInt(2, entity.getNoCategorie());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}
		
		
		return entity;
	}

	@Override
	public Categorie getOne(Categorie entity) throws BusinessException {
		Categorie categorie = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_UNE_CATEGORIE);
			pstmt.setInt(1, entity.getNoCategorie());

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				categorie= new Categorie();
				categorie.setLibelle(rs.getString("libelle"));
				categorie.setNoCategorie(rs.getInt("no_categorie"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIE_ECHEC);
			throw businessException;
		}
		return categorie;
	}

	@Override
	public Categorie deleteOne(Categorie entity) throws BusinessException {
		if (entity == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_UNE_CATEGORIE);
			pstmt.setInt(1, entity.getNoCategorie());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_CATEGORIE_ECHEC);
			throw businessException;
		}
			
		return entity;
	}

}
