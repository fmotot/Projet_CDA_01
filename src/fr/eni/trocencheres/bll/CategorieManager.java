package fr.eni.trocencheres.bll;

import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;

public interface CategorieManager {

	List<Categorie> getListeCategorie() throws BusinessException;
}
