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
import fr.eni.trocencheres.bll.UtilisateurManager;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Enchere;
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
		HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Integer noVente = Integer.parseInt(request.getParameter("noVente"));
		VenteManager venteManager = BLLFactory.getVenteManager();
		Vente vente = null;
		
		try {
			vente = venteManager.afficherVente(noVente);
			if (utilisateur != null) {
				vente.setClassement(utilisateur);
				System.out.println(vente.getClassement());
			}
			
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}
		}
		List<Enchere> listeEnchere = vente.getListeEncheres();
		if (listeEnchere.size() > 0) {
			Enchere derniereEnchere = listeEnchere.get(0);
			request.setAttribute("enchere", derniereEnchere);
		}
				
		if(request.getServletPath().equals("/DetailVenteServlet")) {

		request.setAttribute("vente", vente);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
		requestDispatcher.forward(request, response);
		}
		
		if(request.getServletPath().equals("/AnnulerEnchere")) {
			
			//annulation de l'enchere
			try {
				venteManager.annulerEnchere(vente, utilisateur);
				session.setAttribute("utilisateur", utilisateurManager.afficherUtilisateur(utilisateur.getPseudo()));
			} catch (BusinessException e) {
				System.err.println(e.getListeCodesErreur());
				e.printStackTrace();
				List<Integer> listeCodesErreur = e.getListeCodesErreur();
				if(listeCodesErreur.size()>0)
				{
					request.setAttribute("listeCodesErreur",listeCodesErreur);
				}
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchere.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Integer noVente = Integer.parseInt(request.getParameter("noVente"));
		VenteManager venteManager = BLLFactory.getVenteManager();
		Vente vente = null;
		
		
		try {
			Integer mise = Integer.parseInt(request.getParameter("inputMaProposition"));
			vente = venteManager.afficherVente(noVente);
			venteManager.encherir(utilisateur, vente, mise);
			
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}
		
		}
		try {
			
			vente = venteManager.afficherVente(noVente);
			if (utilisateur != null) {
				vente.setClassement(utilisateur);
				System.out.println(vente.getClassement());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vente", vente);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
		requestDispatcher.forward(request, response);
		}
	}


