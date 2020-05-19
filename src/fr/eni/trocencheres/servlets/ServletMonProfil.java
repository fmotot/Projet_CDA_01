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
 * @author fmoto modification pour gestion erreur
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
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager();

		// Recuperation de l'utilisateur en session
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		
		// si clique sur le bouton "supprimer"
		if (request.getParameter("submit").equals("supprimer")) {
			try {
				// Suppression de l'utilisateur en BDD
				utilisateurManager.supprimerMonCompte(utilisateurConnecte);
				
				// Renvoi sur la page de connexion
				// TODO renvoyer sur liste des enchères non connecté
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp");
				requestDispatcher.forward(request, response);
				System.out.println("suppression effectuée");
			} catch (BusinessException e) {
				
				// Erreur a gérer en cas de probleme lors de la suppression
				System.out.println("erreur de suppession de compte");
				e.printStackTrace();
				
				List<Integer> listeCodesErreur = e.getListeCodesErreur();
				if (listeCodesErreur.size() > 0) {
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
				
				// erreur a gérer si probleme lors de l'update
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		// si clique sur le bouton "enregistrer"
		else if (request.getParameter("submit").equals("enregistrer")){
			Utilisateur utilisateurData = new Utilisateur();
			
			// Reaffectation des données dans l'utilisateur temporaire
			System.out.println("remise des valeurs dans le new user");
			utilisateurData.setPseudo(request.getParameter("inputPseudo"));
			utilisateurData.setNom(request.getParameter("inputNom"));
			utilisateurData.setPrenom(request.getParameter("inputPrenom"));
			utilisateurData.setEmail(request.getParameter("inputEmail"));
			utilisateurData.setTelephone(request.getParameter("inputTelephone"));
			utilisateurData.setRue(request.getParameter("inputRue"));
			utilisateurData.setCodePostal(request.getParameter("inputCodePostal"));
			utilisateurData.setVille(request.getParameter("inputVille"));
			utilisateurData.setMotDePasse(request.getParameter("inputMotDePasse"));
			
			try {
				// Mise a jour de l'utilisateur en BDD et recuperation de l'utilisateur mis à jour
				Utilisateur utilisateurUpdate = utilisateurManager.modifierMonCompte(utilisateurConnecte, utilisateurData, request.getParameter("inputConfirmation"));
				
				// Affectation de l'utilisateur mis a jour en session
				session.setAttribute("utilisateur", utilisateurUpdate);
				
				// renvoi sur la JSP
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			} catch (BusinessException e) {
				System.out.println("Passage dans le catch");
				System.err.println(e.getListeCodesErreur());
				List<Integer> listeCodesErreur = e.getListeCodesErreur();
				if (listeCodesErreur.size() > 0) {
					request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
				
				request.setAttribute("utilisateur", utilisateurData);
				// erreur a gérer si probleme lors de l'update
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}
}
