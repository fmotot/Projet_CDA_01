package fr.eni.trocencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.UtilisateurManager;
import fr.eni.trocencheres.bo.Utilisateur;

@WebServlet("/ServletMonProfil")
public class ServletMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		System.out.println(utilisateurConnecte.toString());
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();

		if (request.getParameter("submit").equals("supprimer")) {
			try {
				utilisateurManager.supprimerMonCompte(utilisateurConnecte);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp");
				requestDispatcher.forward(request, response);
				System.out.println("suppression effectuée");
			} catch (BusinessException e) {
				System.out.println("erreur de suppession de compte");
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("submit").equals("enregistrer")) {
			
			// a ajouter la condition de verification que son mdp soit le bon
			if (request.getParameter("inputMotDePasse").equals(request.getParameter("inputConfirmation"))) {
			utilisateurConnecte.setPseudo(request.getParameter("inputPseudo"));
			utilisateurConnecte.setPseudo(request.getParameter("inputNom"));
			utilisateurConnecte.setPrenom(request.getParameter("inputPrenom"));
			utilisateurConnecte.setEmail(request.getParameter("inputEmail"));
			utilisateurConnecte.setTelephone(request.getParameter("inputTelephone"));
			utilisateurConnecte.setRue(request.getParameter("inputRue"));
			utilisateurConnecte.setCodePostal(request.getParameter("inputCodePostal"));
			utilisateurConnecte.setVille(request.getParameter("inputVille"));
			utilisateurConnecte.setMotDePasse(request.getParameter("inputMotDePasse"));
			System.out.println("modification autorisée");
		//	try {
				//utilisateurManager.modifierMonCompte(utilisateurConnecte);
				session.setAttribute("utilisateur", utilisateurConnecte);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			//} catch (BusinessException e) {
				//e.printStackTrace();
			//}
		} else {
			System.out.println("mot de passe ne correspondent pas");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
			requestDispatcher.forward(request, response);
		}
		}
		
	}

}
