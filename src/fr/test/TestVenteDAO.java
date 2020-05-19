package fr.test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
import fr.eni.trocencheres.dal.DAOFactory;
import fr.eni.trocencheres.dal.EnchereDAO;
import fr.eni.trocencheres.dal.UtilisateurDAO;
import fr.eni.trocencheres.dal.VenteDAO;

/**
 * Servlet implementation class TestVenteDAO
 */
@WebServlet("/TestVenteDAO")
public class TestVenteDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestVenteDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Vente> listVente = null;
		VenteDAO venteDAO = DAOFactory.getVenteDAO();
		VenteManager venteManager = BLLFactory.getVenteManager();
		
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		
		LocalDate date = LocalDate.of(2020, 10, 25);
		LocalDateTime dateTime = LocalDateTime.of(date,LocalTime.MIDNIGHT);
//		LocalDateTime hier = LocalDateTime.of(LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(1),LocalTime.MIDNIGHT);
		
		try {
			listVente = venteDAO.getVentesByDateFinEnchere(dateTime);
			
			for (Vente vente : listVente) {
				response.getWriter().append(vente.toString()).append("<BR/>");
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Enchere e : listVente.get(0).getListeEncheres()) {
			response.getWriter().append(e.toString()).append("<BR/>");
		}
		
		response.getWriter().append("enchère max : " + listVente.get(0).getMaxEnchere()).append("<BR/>");
		
		
		
		Utilisateur user = new Utilisateur();
		user.setNoUtilisateur(2);
		try {
			user = utilisateurDAO.getOne(user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			venteManager.encherir(user, listVente.get(0), 50);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append("Liste code erreur : ").append(e.getListeCodesErreur().toString()).append("<BR/>");
		}
		
		
		try {
			venteManager.encherir(user, listVente.get(0), 100);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append("Liste code erreur").append(e.getListeCodesErreur().toString()).append("<BR/>");
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
