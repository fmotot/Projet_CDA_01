package fr.eni.trocencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Vente;

/**
 * 
 * @author Macorigh Rudy
 *
 */
@WebServlet("/ProfilRetraitServlet")
public class ProfilRetraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VenteManager venteManager = BLLFactory.getVenteManager();
		
		try {
			//recuperation du noVente pour avoir l'adresse de retrait
			Integer noVente = Integer.parseInt(request.getParameter("noVente"));
			Vente vente = venteManager.afficherVente(noVente);
			request.setAttribute("vente", vente);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ProfilRetrait.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
