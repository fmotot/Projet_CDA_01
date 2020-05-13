package fr.eni.trocencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.trocencheres.BusinessException;
import fr.eni.trocencheres.bll.BLLFactory;
import fr.eni.trocencheres.bll.UtilisateurManager; 
 
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
 
		String login = request.getParameter("inputId"); 
		String motDePasse = request.getParameter("inputPassword"); 
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurManager(); 
		try { 
			utilisateurManager.login(login, motDePasse); 
			request.setAttribute("utilisateur", utilisateurManager); 
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/MonProfil.jsp"); 
			requestDispatcher.forward(request, response); 
		} catch (BusinessException e) { 
			System.out.println("utilisateur inexistant"); 
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/SeConnecter.jsp"); 
			requestDispatcher.forward(request, response); 
		} 
	} 
 
} 