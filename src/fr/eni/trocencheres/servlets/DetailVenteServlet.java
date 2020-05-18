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
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Enchere;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Vente vente = (Vente) request.getAttribute("vente");

		request.setAttribute("vente", vente);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Integer mise = (Integer) request.getAttribute("inputMaProposition");
		Vente vente = (Vente) request.getAttribute("vente");
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		VenteManager venteManager = BLLFactory.getVenteManager();
		
		try {
			// si on fait en sort que echerir retourne la vente qui a ete encheri
			 Vente venteMiseAJour = venteManager.encherir(utilisateur, vente, mise);
			 request.setAttribute("vente", venteMiseAJour);
			
// 			si encherir me retoune la liste des encheres de la vente
			List<Enchere> listeEncheres = venteManager.encherir(utilisateur, vente, mise);
			vente.setListeEncheres(listeEncheres);
			request.setAttribute("vente", vente);
			
			
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
	}

}
