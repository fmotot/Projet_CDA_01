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

		List<Categorie> listeCategorie = null;
		try {
			listeCategorie = categorieManager.getListeCategorie();
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
		request.setAttribute("listeCategorie", listeCategorie);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/NouvelleVente.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		VenteManager venteManager = BLLFactory.getVenteManager();
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
		try {
			Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
			String nomArticle = request.getParameter("inputArticle");
			String description = request.getParameter("inputDescription");
			// Transformation de la String date html en LocalDate puis LocalDateTime
			LocalDate ld = LocalDate.parse(request.getParameter("inputFinEnchere"), DATEFORMATTER);
			LocalDateTime dateFinEncheres = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());

			Integer miseAPrix = Integer.parseInt(request.getParameter("inputPrixDeBase"));
			
			// Verification si retrait est complété, sinon le remplace par l'adresse de l'utilisateur
			String rue = request.getParameter("inputRue");
			if (rue.trim().equals("")) {
				rue = vendeur.getRue();
			}
			String ville = request.getParameter("inputVille");
			if (ville.trim().equals("")) {
				ville = vendeur.getVille();
			}
			String codePostal = request.getParameter("inputCodePostal");
			if (codePostal.trim().equals("")) {
				codePostal = vendeur.getCodePostal();
			}
			
			Integer noCategorie = Integer.parseInt(request.getParameter("selectCategories"));

			Categorie categorie = new Categorie(noCategorie);
			
			System.out.println(nomArticle + " " + description + " " + dateFinEncheres + " " + miseAPrix + " " + vendeur
				+ " " + rue + " " + ville + " " + codePostal + " " + categorie);
			

			venteManager.creerVente(nomArticle, description, dateFinEncheres, miseAPrix, vendeur, rue, ville, codePostal, categorie);

		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchere.jsp");
		requestDispatcher.forward(request, response);
	}

}
