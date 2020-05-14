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

		// Recuperation des données de la JSP
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

		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();

		// Verification du mot de passe et de sa confirmation
		if (motDePasse.equals(confirmationMDP)) {
			try {
				// Creation de l'utilisateur en BDD
				Utilisateur utilisateur = utilisateurManager.creerCompteUtilisateur(telephone, codePostal, pseudo, nom,
						prenom, email, rue, ville, motDePasse);
				// Mise de l'utilisateur en session
				session.setAttribute("utilisateur", utilisateur);
				// Renvoi sur JSP 
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			} catch (BusinessException e) {
				// erreur a gérer si probleme lors de la creation
				System.out.println("erreur creation compte");
				e.printStackTrace();
			}

		} else {
			// Si mot de passe et confirmation differents, retour sur la page de creation
			// A determiner si doit etre pre-rempli avec les infos entrées precedemment
			System.out.println("erreur de mot de passe");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/CreationCompte.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}