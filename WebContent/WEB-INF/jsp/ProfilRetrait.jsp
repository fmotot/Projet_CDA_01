<!-- Antony / code -->
<!-- Rudy / EL  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css" href="./css/profilutilisateur.css">

<title>TrocEncheres - Profil retrait</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>

	<div class="container">

		<div class="center-content">

			<h2>${vente.maxEnchere.acheteur.pseudo}</h2>

				<div class="form-group row justify-content-center">
   					<label for="staticPseudo" class="col-4 col-lg-2">Pseudo :</label>
    				<div class="col-6 col-lg-4">
      					<p>${vente.maxEnchere.acheteur.pseudo}</p>
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticRue" class="col-4 col-lg-2">Adresse :</label>
    				<div class="col-6 col-lg-4">
    						<p>${vente.maxEnchere.acheteur.rue}</p>
      						<p>${vente.maxEnchere.acheteur.codePostal} ${vente.maxEnchere.acheteur.ville}</p>
      					   					
    				</div>
 			    </div>

 			    <div class="form-group row justify-content-center">
   					<label for="staticTelephone" class="col-4 col-lg-2">Téléphone :</label>
    				<div class="col-6 col-lg-4">
      					<p>${vente.maxEnchere.acheteur.telephone}</p>
    				</div>
 			    </div>
 			    

			<div class="row justify-content-around connexion text-center">

				<div class="col-10">
					<div class="d-inline">
						<a class="btn btn-retour btn-primary" href="./FinDeVenteServlet?noVente=${vente.noVente}" role="button">Back</a>
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