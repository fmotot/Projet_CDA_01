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
import fr.eni.trocencheres.bll.CategorieManager;
import fr.eni.trocencheres.bll.VenteManager;
import fr.eni.trocencheres.bo.Categorie;
import fr.eni.trocencheres.bo.Utilisateur;
import fr.eni.trocencheres.bo.Vente;

/**
 * 
 * @author Macorigh Rudy
 *
 */

@WebServlet("/ListeEnchereServlet")
public class ListeEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		HttpSession session = request.getSession();
		VenteManager venteManager = BLLFactory.getVenteManager();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		String recherche = null;
		Categorie categorie = null;
		List<Categorie> listeCategorie = getListeCategorie();
		try {
			List<Vente>listeDeVentes = venteManager.listerVentes(utilisateurConnecte, false, false,
					false, true, recherche, categorie);
			request.setAttribute("listeVentes", listeDeVentes);
			request.setAttribute("listeCategorie", listeCategorie);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchere.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		VenteManager venteManager = BLLFactory.getVenteManager();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		boolean isMesVentes = false;
		boolean isMesEncheres = false;
		boolean isMesAcquisitions = false;
		boolean isAutresEncheres = false;
		String recherche = null;
		Categorie categorie = null;
		List<Vente> listeDeVentes;

		// requete les checkbox , si coché, retourne on si vide retourne null et donne
		// la valeur booléenne en fonction
		if (request.getParameter("mesVentes") == null) {
			isMesVentes = false;
		} else {
			isMesVentes = true;
		}

		if (request.getParameter("mesEncheres") == null) {
			isMesEncheres = false;
		} else {
			isMesEncheres = true;
		}

		if (request.getParameter("mesAcquisitions") == null) {
			isMesAcquisitions = false;
		} else {
			isMesAcquisitions = true;
		}

		if (request.getParameter("autresEncheres") == null) {
			isAutresEncheres = false;
		} else {
			isAutresEncheres = true;
		}

		// requete de la recherche dans la textbox
		recherche = request.getParameter("recherche");
		if (recherche.equals("")) {
			recherche = null;
		}

		// requete la categorie dans la droplist et crée une categorie pour l'ajoutée au
		// constructeur de la fonction
		if (request.getParameter("categorie").equals("toutes")) {
			categorie = null;
		} else {
			categorie = new Categorie(Integer.parseInt(request.getParameter("categorie")));
		}
		try {
			
			if(utilisateurConnecte==null) {
				listeDeVentes = venteManager.listerVentes(utilisateurConnecte, false, false,
						false, true, recherche, categorie);
			
			}
				
			else {
				// recupere la liste des ventes associée a la recherche utilisateur
				listeDeVentes = venteManager.listerVentes(utilisateurConnecte, isMesVentes, isMesEncheres,
						isMesAcquisitions, isAutresEncheres, recherche, categorie);
			}	
			
			
			for (Vente vente : listeDeVentes) {
				vente.setClassement(utilisateurConnecte);
			}
			List<Categorie> listeCategorie = getListeCategorie();
			request.setAttribute("listeCategorie", listeCategorie);
			request.setAttribute("listeVentes", listeDeVentes);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/ListeEnchere.jsp");
			requestDispatcher.forward(request, response);
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}

	}

	private static List<Categorie> getListeCategorie() {
		CategorieManager categorieManager = BLLFactory.getCategorieManager();

		List<Categorie> listeCategorie = null;
		try {
			listeCategorie = categorieManager.getListeCategorie();
		} catch (BusinessException e) {
			System.err.println(e.getListeCodesErreur());
			e.printStackTrace();
		}
		return listeCategorie;
	}

}
