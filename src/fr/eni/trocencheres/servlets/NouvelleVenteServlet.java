package fr.eni.trocencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.trocencheres.bll.CategorieManager;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Retrait;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

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

		setCategorieAttribute(request);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/NouvelleVente.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		setCategorieAttribute(request);

		VenteManager venteManager = BLLFactory.getVenteManager();
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		String nomArticle = request.getParameter("inputArticle");
		String description = request.getParameter("inputDescription");
		// Transformation de la String date html en LocalDate puis LocalDateTime
		LocalDate ld = LocalDate.parse(request.getParameter("inputFinEnchere"), DATEFORMATTER);
		LocalDateTime dateFinEncheres = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());

		Integer miseAPrix = Integer.parseInt(request.getParameter("inputPrixDeBase"));
		String rue = request.getParameter("inputRue");
		String ville = request.getParameter("inputVille");
		String codePostal = request.getParameter("inputCodePostal");
		Integer noCategorie = Integer.parseInt(request.getParameter("selectCategories"));

		Categorie categorie = new Categorie(noCategorie);

//		System.out.println(nomArticle + " " + description + " " + dateFinEncheres + " " + miseAPrix + " " + vendeur
//				+ " " + rue + " " + ville + " " + codePostal + " " + categorie);
		try {
			Vente venteCree = venteManager.creerVente(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, rue, ville,
					codePostal, categorie);
			
			request.setAttribute("vente", venteCree);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/DetailVente.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();

			// TODO gérer les erreurs et renvoyer sur la page de création de vente
			List<Integer> listeCodesErreur = e.getListeCodesErreur();
			if (listeCodesErreur.size() > 0) {
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
			
			Vente vente = new Vente();
			
			vente.setCategorie(categorie);
			vente.setDateFinEncheres(dateFinEncheres);
			vente.setDescription(description);
			vente.setMiseAPrix(miseAPrix);
			vente.setNomArticle(nomArticle);
			
			Retrait retrait = new Retrait(rue, ville, codePostal);
			vente.setRetrait(retrait);
			
			request.setAttribute("vente", vente);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/NouvelleVente.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	private static void setCategorieAttribute(HttpServletRequest request) {
		CategorieManager categorieManager = BLLFactory.getCategorieManager();

		List<Categorie> listeCategorie = null;
		try {
			listeCategorie = categorieManager.getListeCategorie();
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
		request.setAttribute("listeCategorie", listeCategorie);
	}
}
