package fr.eni.trocencheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Utilisateur;

/**
 * 
 * @author Macorigh Rudy
 *
 */

@WebServlet("/NouvelleVenteServlet")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategorieManager categorieManager = BLLFactory.getCategorieManager();
		
		List<Categorie> listeCategorie = categorieManager.getListeCategorie;
		request.setAttribute("listeCategorie", listeCategorie);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/NouvelleVente.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		VenteManager venteManager = BLLFactory.getVenteManager();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		try {
			Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
			String nomArticle = request.getParameter("inputArticle");
			String description = request.getParameter("inputDescription");
			LocalDateTime dateFinEncheres = LocalDateTime.parse(request.getParameter("inputFinEnchere"), formatDate);
			Integer miseAPrix = Integer.parseInt(request.getParameter("inputPrixDeBase"));
			String rue = request.getParameter("inputRue");
			String ville = request.getParameter("inputCodePostal");
			String codePostal = request.getParameter("inputVille");
			// une fois categorie rajoutée a la fonction en BLL
			Integer noCategorie = Integer.parseInt(request.getParameter("selectCategories"));
			Categorie categorie = new Categorie(noCategorie);
			
			
			venteManager.creerVente(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, rue, ville, codePostal, categorie);
			
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchere.jsp");
		requestDispatcher.forward(request, response);
	}

}
