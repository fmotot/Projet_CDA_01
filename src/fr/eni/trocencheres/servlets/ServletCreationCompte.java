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
 * @author fmoto modifications 
 *
 */
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

		try {
			// Creation de l'utilisateur en BDD
			Utilisateur utilisateur = utilisateurManager.creerCompteUtilisateur(telephone, codePostal, pseudo, nom,
					prenom, email, rue, ville, motDePasse, confirmationMDP);
			// Mise de l'utilisateur en session
			session.setAttribute("utilisateur", utilisateur);
			// Renvoi sur JSP
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			// erreur a gérer si probleme lors de la creation
			System.out.println("erreur creation compte");
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();

			// Affectation des variables à renvoyer
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("codePostal", codePostal);
			request.setAttribute("ville", ville);
			request.setAttribute("motDePasse", motDePasse);
			request.setAttribute("confirmationMDP", confirmationMDP);

			if(listeCodesErreur.size()>0)
			{
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/CreationCompte.jsp");
			requestDispatcher.forward(request, response);

		}
	}
}