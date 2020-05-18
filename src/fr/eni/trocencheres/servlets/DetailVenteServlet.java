package fr.eni.trocencheres.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;
/**
 * 
 * @author Macorigh Rudy
 *
 */

@WebServlet(
		urlPatterns= {
						"/DetailVenteServlet",
						"/AnnulerEnchere"
})

public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/DetailVenteServlet")) {

		Vente vente = (Vente) request.getAttribute("vente");

		request.setAttribute("vente", vente);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
		requestDispatcher.forward(request, response);
	}
		
		if(request.getServletPath().equals("/AnnulerEnchere")) {
			HttpSession session = request.getSession();
			
			Vente vente = (Vente) request.getAttribute("vente");
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			VenteManager venteManager = BLLFactory.getVenteManager();
			
			//annulation de l'enchere
			try {
				venteManager.annulerEnchere(vente, utilisateur);
			} catch (BusinessException e) {
				System.err.println(e.getListeCodesErreur());
				e.printStackTrace();
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchereServlet.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Integer mise = (Integer) request.getAttribute("inputMaProposition");
		Vente vente = (Vente) request.getAttribute("vente");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		VenteManager venteManager = BLLFactory.getVenteManager();

		try {
			// encherissement et recuperation de la vente mise a jour
			Vente venteMiseAJour = venteManager.encherir(utilisateur, vente, mise);
			request.setAttribute("vente", venteMiseAJour);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
			requestDispatcher.forward(request, response);

		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
	}

}
