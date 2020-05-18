package fr.eni.trocencheres.bll;

import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.dal.DAOFactory;
import fr.eni.trocencheres.dal.CategorieDAO;

public class CategorieManagerImpl implements CategorieManager {

	private CategorieDAO categorieDAO;

	CategorieManagerImpl() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	@Override
	public List<Categorie> getListeCategorie() throws BusinessException {
		return categorieDAO.getAll();
	}

}
