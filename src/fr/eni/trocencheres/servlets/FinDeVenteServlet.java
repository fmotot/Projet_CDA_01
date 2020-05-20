package fr.eni.trocencheres.servlets;

import java.io.IOException;
import java.util.List;

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
						"/FinDeVenteServlet",
						"/RetraitEffectue"
})

public class FinDeVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession session = request.getSession();
		VenteManager venteManager = BLLFactory.getVenteManager();
			
		try {
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
			Integer noVente = Integer.parseInt(request.getParameter("noVente"));
			
			Vente vente = venteManager.afficherVente(noVente);
			
			request.setAttribute("vente", vente);
			
			if(request.getServletPath().equals("/RetraitEffectue")) {
				vente.setRetraitArticle(true);
				venteManager.modifierVente(vente,utilisateurConnecte);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/FinDeVente.jsp");
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
