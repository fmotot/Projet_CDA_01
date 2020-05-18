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
@WebServlet("/ServletSeConnecter") 
public class ServletSeConnecter extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp"); 
		requestDispatcher.forward(request, response); 
	} 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		HttpSession session = request.getSession();
		// Recuperation du login et mot de passe
		String login = request.getParameter("inputId"); 
		String motDePasse = request.getParameter("inputPassword"); 
		System.out.println(login + motDePasse);
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager(); 
		
		try { 
			// Verification en BDD que les 2 concordent
			Utilisateur utilisateur = utilisateurManager.login(login, motDePasse); 
			// Mise de l'utilisateur en session si OK
			session.setAttribute("utilisateur", utilisateur);		
			// Renvoi sur la page de profil
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp"); 
			requestDispatcher.forward(request, response); 
			

		} catch (BusinessException e) { 
			System.out.println("utilisateur inexistant"); 
			System.err.println(e.getListeCodesErreur());
			// Renvoi sur la page de connexion si pas de concordance
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp"); 
			requestDispatcher.forward(request, response); 
		} 
	} 
 
} 