<!-- Antony / code -->
<!-- Rudy / EL  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="fr">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/listeenchere.css">

<title>TrocEncheres - Liste Ench�re</title>
</head>
<body>

		<%@ include file="Header.jspf" %>
			
		<%@ include file="Alerte.jspf" %>

	<div id="liste-enchere" class="container">

		<h2>Filtres :</h2>
		
		<div class="row">
		
		<div class="col-lg-12">

			<form action="./ListeEnchereServlet" method="post">

			<div class="row">

				<div class="col-12 col-lg-3">
					<div class="form-group">
						<div class="form-check">
						<c:if test="${!empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="mesVentes"id="mesVentes"> 
						</c:if>	
						<c:if test="${empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="mesVentes"id="mesVentes" disabled> 
						</c:if>	
								<label class="form-check-label" for="mesVentes">Mes ventes</label>
								
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
						<c:if test="${!empty utilisateur}">
							<input class="form-check-input" type="checkbox"name="mesEncheres" id="mesEncheres"> 
						</c:if>	
						<c:if test="${empty utilisateur}">
							<input class="form-check-input" type="checkbox"name="mesEncheres" id="mesEncheres" disabled>
						</c:if> 	
							<label class="form-check-label" for="mesEncheres">Mes ench�res en cours</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
						<c:if test="${!empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="mesAcquisitions" id="mesAcquisitions">
						</c:if>	
						<c:if test="${empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="mesAcquisitions" id="mesAcquisitions" disabled>
						</c:if>			
							<label class="form-check-label" for="mesAcquisitions">Mes acquisitions</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
						<c:if test="${!empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="autresEncheres" id="autresEncheres" > 
						</c:if>
						<c:if test="${empty utilisateur}">
							<input class="form-check-input" type="checkbox" name="autresEncheres" id="autresEncheres" checked> 
						</c:if>
							<label class="form-check-label" for="autresEncheres">Autres ench�res</label>
						</div>
					</div>
				</div>

				<div class="col-lg-7 my-auto">
				
					<div class="input-group row col-lg-11 mb-3">
						<div class="input-group-prepend">
							<div class="input-group-text">0-</div>
						</div>
						<input type="text" class="form-control" id="inlineFormInputGroup" name="recherche" placeholder="Le nom de l'article contient">
					</div>
					<div class="form-group row col-lg-11">
						<label class="col-lg-4 my-auto" for="inputCategories">Cat�gories :</label> 
						<select id="inputCategories" name="categorie" class="form-control col-lg-8">
							<option value="toutes">Toutes</option>
							<c:forEach var="categorie" items="${listeCategorie}">>
						    	<option value="${categorie.noCategorie}">${categorie.libelle}</option>
						    </c:forEach>
						</select>
					</div>
				</div>

			</div>

				<div class="row col-10 col-lg-4 mx-auto mt-3">
					<button class="btn btn-rechercher btn-primary" type="submit">Rechercher</button>
				</div>

			</form>
			
			</div>
			
		</div>

		<div class="container">

			<div class="row">
				<c:forEach var="vente" items="${listeVentes}">
					<div class="jumbotron col-12 col-lg-5 mx-auto">
						<div class="row">
							<div class="col-3 ">
								<div class="image"></div>
							</div>

							<div class="col-8 ml-3">
								<jsp:useBean id="now" class="java.util.Date"/>
								<fmt:parseDate value="${vente.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate> 
								<c:choose>
								<c:when test="${myParseDate < now }">
								<a href="./FinDeVenteServlet?noVente=${vente.noVente}">${vente.nomArticle}</a>
								</c:when>
								<c:otherwise>
								<a href="./DetailVenteServlet?noVente=${vente.noVente}">${vente.nomArticle}</a>
								</c:otherwise>
								</c:choose>
								
								<p>Prix : ${!empty vente.maxEnchere.mise ? vente.maxEnchere.mise : vente.miseAPrix} points 
								<c:choose>
									<c:when test="${vente.classement != 0 }"></c:when>
									<c:otherwise>
										</p>
									</c:otherwise>
								</c:choose>
								
								<p>Fin de l'ench�re : <fmt:formatDate value="${myParseDate}"  pattern="dd/MM/yyyy"></fmt:formatDate></p>
								<c:choose>
    								<c:when test="${empty vente.retrait}">
    									<p>Retrait : ${vente.vendeur.rue}</p>
      									<p>${vente.vendeur.codePostal} ${vente.vendeur.ville}</p>
      								</c:when>
    								<c:otherwise>
    									<p>Retrait : ${vente.retrait.rue}</p>
      									<p>${vente.retrait.codePostal} ${vente.retrait.ville}</p>
      								</c:otherwise>
    							</c:choose>
    							
								<c:choose>
								<c:when test="${vente.vendeur.pseudo != utilisateur.pseudo}">
								<p>Vendeur :<a href="./ProfilUtilisateurServlet?pseudo=${vente.vendeur.pseudo}"> ${vente.vendeur.pseudo}</a></p>
								</c:when>
								<c:otherwise>
								<p>Vendeur : ${vente.vendeur.pseudo}</p>
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>
	
	<%@ include file="Footer.jspf" %>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>