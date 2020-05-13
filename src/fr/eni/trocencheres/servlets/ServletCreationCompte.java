package fr.eni.trocencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String pseudo = request.getParameter("inputPseudo");
		String nom = request.getParameter("inputNom");
		String prenom = request.getParameter("inputPrenom");
		String email = request.getParameter("inputEmail");
		Integer telephone = Integer.parseInt(request.getParameter("inputTelephone"));
		String rue = request.getParameter("inputRue");
		String codePostal = request.getParameter("inputCodePostal");
		String ville = request.getParameter("inputVille");
		String motDePasse = request.getParameter("inputMotDePasse");
		String confirmationMDP = request.getParameter("inputConfirmation");
		Integer credit = 100;
		boolean administrateur = false;
		boolean actif = true;

		
		if (motDePasse.equals(confirmationMDP)) {
			Utilisateur utilisateur = new Utilisateur(telephone, codePostal, credit, pseudo, nom, prenom, email, rue,
					ville, motDePasse, administrateur, actif);
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("erreur");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/CreationCompte.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
