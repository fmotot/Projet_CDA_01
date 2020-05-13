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
import fr.eni.trocencheres.bll.UtilisateurManager;
import fr.eni.trocencheres.bo.Utilisateur;

@WebServlet("/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/CreationCompte.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String pseudo = request.getParameter("inputPseudo");
		String nom = request.getParameter("inputNom");
		String prenom = request.getParameter("inputPrenom");
		String email = request.getParameter("inputEmail");
		String telephone = request.getParameter("inputTelephone");
		String rue = request.getParameter("inputRue");
		String codePostal = request.getParameter("inputCodePostal");
		String ville = request.getParameter("inputVille");
		String motDePasse = request.getParameter("inputMotDePasse");
		String confirmationMDP = request.getParameter("inputConfirmation");

		//UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();
		//try {
			// utilisateurManager.creerCompteUtilisateur(telephone, codePostal, pseudo, nom, prenom, email, rue, ville,	motDePasse);
			Utilisateur utilisateur = new Utilisateur(telephone, codePostal, 100, pseudo, nom, prenom, email, rue, ville,motDePasse, true, true);
			
			// a remettre utilisateurManager
			session.setAttribute("utilisateur", utilisateur);
		//} catch (BusinessException e) {
		//	System.out.println("erreur creation compte");
		//	e.printStackTrace();
		//}

		if (motDePasse.equals(confirmationMDP)) {

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
			requestDispatcher.forward(request, response);
		} else {

			System.out.println("erreur de mot de passe");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/CreationCompte.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	

}