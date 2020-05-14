package fr.eni.trocencheres.dal.jdbcImpl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.dal.CategorieDAO;

public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static String SELECT_ALL_CATEGORIES="SELECT * FROM categories";
	private static String INSERT_UNE_CATEGORIE="INSERT INTO categories (libelle) VALUES (?) ";
	private static String UPDATE_UNE_CATEGORIE="UPDATE categories SET libelle=? WHERE no_categorie=?";
	private static String DELETE_UNE_CATEGORIE="DELETE FROM categories WHERE no_categorie=?";
	

	@Override
	public List<Categorie> getAll() throws BusinessException {
		List<Categorie>  listeCategories = new ArrayList<Categorie>();
		
		
		
		
		return listeCategories;
	}

	@Override
	public Categorie insertOne(Categorie entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie updateOne(Categorie entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie getOne(Categorie entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie deleteOne(Categorie entity) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
