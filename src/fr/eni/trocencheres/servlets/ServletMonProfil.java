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
/**
 * 
 * @author Macorigh Rudy
 *
 */
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

		// Recuperation de l'utilisateur en session
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();
		Utilisateur utilisateurData = new Utilisateur();
		// si clique sur le bouton supprimer
		if (request.getParameter("submit").equals("supprimer")) {
			try {
				// Suppression de l'utilisateur en BDD
				utilisateurManager.supprimerMonCompte(utilisateurConnecte);
				// Renvoi sur la page de connexion
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp");
				requestDispatcher.forward(request, response);
				System.out.println("suppression effectuée");
			} catch (BusinessException e) {
				// Erreur a gérer en cas de probleme lors de la suppression
				System.out.println("erreur de suppession de compte");
				e.printStackTrace();
			}
		}
		// si clique sur le bouton enregistrer
		if (request.getParameter("submit").equals("enregistrer")) {
			// Verification au prealable que les champs MDP et Confirmation soit identiques
			if ((request.getParameter("inputMotDePasse")).equals(request.getParameter("inputConfirmation"))) {
				// Reaffectation des données dans l'utilisateur
				System.out.println("remise des valeurs dans le new user");
				utilisateurData.setPseudo(request.getParameter("inputPseudo"));
				System.out.println(utilisateurData.getPseudo());
				utilisateurData.setNom(request.getParameter("inputNom"));
				utilisateurData.setPrenom(request.getParameter("inputPrenom"));
				utilisateurData.setEmail(request.getParameter("inputEmail"));
				utilisateurData.setTelephone(request.getParameter("inputTelephone"));
				utilisateurData.setRue(request.getParameter("inputRue"));
				utilisateurData.setCodePostal(request.getParameter("inputCodePostal"));
				utilisateurData.setVille(request.getParameter("inputVille"));
				utilisateurData.setMotDePasse(request.getParameter("inputMotDePasse"));

				try {
					// Mise a jour de l'utilisateur en BDD et recuperation de l'utilisateur mis a
					// jour
					Utilisateur utilisateurUpdate = utilisateurManager.modifierMonCompte(utilisateurConnecte,
							utilisateurData, request.getParameter("inputConfirmation"));

					// Affectation de l'utilisateur mis a jour en session
					session.setAttribute("utilisateur", utilisateurUpdate);
					// renvoi sur la JSP
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
					requestDispatcher.forward(request, response);
				} catch (BusinessException e) {
					System.err.println(e.getListeCodesErreur());
					// erreur a gérer si probleme lors de l'update
					e.printStackTrace();
				}
			} else {
				// Si mot de passe et confirmation differents, retour sur la page de profil
				System.out.println("erreur de mot de passe");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			}

		}

	}

}
