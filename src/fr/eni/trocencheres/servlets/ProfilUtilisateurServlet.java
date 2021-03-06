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
import fr.eni.trocencheres.bo.Utilisateur;
/**
 * 
 * @author Macorigh Rudy
 *
 */

@WebServlet("/ProfilUtilisateurServlet")
public class ProfilUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();
		
		try {
			Utilisateur utilisateurChoisi = utilisateurManager.afficherUtilisateur(request.getParameter("pseudo"));
			request.setAttribute("utilisateurChoisi", utilisateurChoisi);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ProfilUtilisateur.jsp") ;
	    requestDispatcher.forward(request, response) ;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
