package fr.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.trocencheres.dal.ConnectionProvider;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requete = "SELECT * FROM utilisateurs WHERE no_utilisateur=1;";
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmtRepas = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = stmtRepas.executeQuery(requete);

			while (rs.next()) {
				String nom = (rs.getString("nom"));
				String prenom = (rs.getString("prenom"));
				String pseudo = (rs.getString("pseudo"));
				System.out.println(nom +" "+ prenom +" " +pseudo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/jsp/SeConnecter.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
