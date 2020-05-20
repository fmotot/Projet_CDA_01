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
<link rel="stylesheet" type="text/css" href="./css/detailvente.css">

<title>TrocEncheres - Détail Vente</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>
	
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:parseDate value="${vente.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate> 

	<br>
	<h2 class="text-center"> ${myParseDate < now ? 'Enchère Terminée' : 'Enchère en cours'}</h2>

	<div class="container">

		<div class="row col-12 mx-auto d-lg-none">
			<h3 class="nomArticle">${vente.nomArticle}</h3>
			<div class="image mb-3"></div>
		</div>

		<div class="row">

			<div class="col-lg-4 d-none d-lg-block my-auto">
				<div class="image"></div>
			</div>

			<div class="content-vente col-12 col-lg-7">
				<div class="d-none d-lg-block mb-3">
					<h3 class="nomArticle">${vente.nomArticle}</h3>
				</div>

					<div class="form-group row">
						<label for="staticMeilleureOffre" class="col-5 col-lg-3 label-bold">Description :</label>
						<div class="col-6 col-lg-9">
							<p>${vente.description}</p>
						</div>
					</div>

				<div class="form-group row">
					<label for="staticMeilleureOffre" class="col-5 col-lg-3 label-bold">Meilleure offre :</label>
					<div class="col-6 col-lg-9">
					<c:choose>
						<c:when test="${!empty vente.listeEncheres}">
							<p>${vente.maxEnchere.mise} de ${vente.maxEnchere.acheteur.pseudo}</p>
						</c:when>
						<c:otherwise>
							<p> Pas encore d'enchérisseur. </p>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticMiseAPrix" class="col-5 col-lg-3 label-bold">Mise à Prix :</label>
					<div class="col-6 col-lg-4">
						<p> ${vente.miseAPrix} </p>		
				</div>
				</div>
				<div class="form-group row">
					<label for="staticFinEnchere" class="col-5 col-lg-3 label-bold">Fin de l'enchère :</label>
					<div class="col-6 col-lg-4">
						<p><fmt:parseDate value="${vente.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate> 
						<fmt:formatDate value="${myParseDate}"  pattern="dd/MM/yyyy"></fmt:formatDate></p>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticRetrait" class="col-5 col-lg-3 label-bold">Retrait :</label>
					<div class="col-6 col-lg-4">
    				<c:choose>
    					<c:when test="${empty vente.retrait}">
    						<p>${vente.vendeur.rue}</p>
      						<p>${vente.vendeur.codePostal} ${vente.vendeur.ville}</p>
      					</c:when>
    					<c:otherwise>
    						<p>${vente.retrait.rue}</p>
      						<p>${vente.retrait.codePostal} ${vente.retrait.ville}</p>
      					</c:otherwise>
    					</c:choose>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticVendeur" class="col-5 col-lg-3 label-bold">Vendeur :</label>
					<div class="col-6 col-lg-4">
						<a href="./ProfilUtilisateurServlet?pseudo=${vente.vendeur.pseudo}">${vente.vendeur.pseudo}</a>
					</div>
				</div>

				<form action="./DetailVenteServlet?noVente=${vente.noVente}" method="post">

					<div class="form-group row">
						<label for="inputMaProposition" class="col-5 col-lg-3 my-auto label-bold">Ma proposition :</label>
						<div class="col-3 col-lg-4 ">
							<input type="text" class="form-control" name="inputMaProposition" id="inputMaProposition">
						</div>
						<div class="col-3 col-lg-4">
							<button type="submit" class="btn btn-encherir btn-primary my-auto ">Enchérir</button>
						</div>
					</div>

				</form>

				<div class="row mt-3">
				<c:if test="${vente.classement != 0 }">
					<div class="col-8 col-lg-7 text-left">
						<a class="btn btn-back btn-primary" href="./AnnulerEnchere?noVente=${vente.noVente}" role="button">Annuler ma dernière enchère</a>
					</div> 
				</c:if>
					<div class="col-4 col-lg-4 text-left">
						<a class="btn btn-back btn-primary" href="./ListeEnchereServlet" role="button">Back</a>
					</div>
				</div>

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