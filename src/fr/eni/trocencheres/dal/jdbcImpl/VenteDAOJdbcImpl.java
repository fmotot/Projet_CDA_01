package fr.eni.trocencheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.CodesResultatDAL;
import fr.eni.trocencheres.dal.ConnectionProvider;
import fr.eni.trocencheres.dal.VenteDAO;

public class VenteDAOJdbcImpl implements VenteDAO {

	private static String SELECT_ALL_VENTES = "SELECT * FROM VENTES INNER JOIN encheres ON ventes.no_vente = encheres.no_vente INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = encheres.no_utilisateur LEFT JOIN retraits ON retraits.no_vente = ventes.no_vente";
	private static String INSERT_UNE_VENTE = "";
	private static String UPDATE_UNE_VENTE ="";
	private static String SELECT_UNE_VENTE="";
	private static String DELETE_UNE_VENTE="";
	
	
//	SELECT * FROM VENTES 
//	INNER JOIN utilisateurs as u
//    ON u.no_utilisateur = ventes.no_utilisateur 
//	INNER JOIN encheres 
//    ON ventes.no_vente = encheres.no_vente
//    INNER JOIN utilisateurs
//    ON utilisateurs.no_utilisateur = encheres.no_utilisateur 
//    LEFT JOIN retraits
//    ON retraits.no_vente = ventes.no_vente;
	
	@Override
	public List<Vente> getAll() throws BusinessException {
		List<Vente> listesVentes = new ArrayList<Vente>();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_VENTES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Vente vente = new Vente();

				

				listesVentes.add(vente);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_CATEGORIES_ECHEC);
			throw businessException;
		}

		
		
		return listesVentes;
	}

	@Override
	public Vente insertOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente updateOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente getOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public Vente deleteOne(Vente entity) throws BusinessException {
		return null;
	}

	@Override
	public List<Vente> getVentesFiltered(Utilisateur utilisateur, boolean isMesVentes, boolean isMesEncheres,boolean isMesAcquisitions, boolean isAutresEncheres, String recherche, Categorie categorie)
			throws BusinessException {
		
		return null;
	}

}
