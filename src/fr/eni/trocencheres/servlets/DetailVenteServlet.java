package fr.eni.trocencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.trocencheres.bo.Vente;


@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Vente vente = (Vente) request.getAttribute("vente");
		
		
		request.setAttribute("vente", vente);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp") ;
	    requestDispatcher.forward(request, response) ;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer proposition = (Integer) request.getAttribute("inputMaProposition");
		
		
	}

}
