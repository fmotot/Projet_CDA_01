<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.trocencheres.messages.LecteurMessages" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="./css/creation.css">

<title>TrocEncheres - Création de compte</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>

	<div class="container">

		<div class="center-content">

			<h2>Creer un compte</h2>
			
			<form action="./ServletCreationCompte" method="post">

				<div class="form-group row justify-content-center">
				    <label for="inputPseudo" class="col-4 col-lg-2 col-form-label-sm ">Pseudo :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputPseudo" type="text" class="form-control form-control-sm" id="inputPseudo" value="${pseudo}">
				    </div>
				    <label for="inputNom" class="col-4 col-lg-2 col-form-label-sm ">Nom :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputNom" type="text" class="form-control form-control-sm" id="inputNom" value="${nom}">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputPrenom" class="col-4 col-lg-2 col-form-label-sm ">Prénom :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputPrenom" type="text" class="form-control form-control-sm" id="inputPrenom" value="${prenom}">
				    </div>
				    <label for="inputEmail" class="col-4 col-lg-2 col-form-label-sm ">Email :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputEmail" type="email" class="form-control form-control-sm" id="inputEmail" value="${email}">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputTelephone" class="col-4 col-lg-2 col-form-label-sm ">Téléphone :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputTelephone" type="text" class="form-control form-control-sm" id="inputTelephone" value="${telephone}">
				    </div>
				    <label for="inputRue" class="col-4 col-lg-2 col-form-label-sm ">Rue :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputRue" type="text" class="form-control form-control-sm" id="inputRue" value="${rue}">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputCodePostal" class="col-4 col-lg-2 col-form-label-sm ">Code postal :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputCodePostal" type="text" class="form-control form-control-sm" id="inputCodePostal" value="${codePostal}">
				    </div>
				    <label for="inputVille" class="col-4 col-lg-2 col-form-label-sm ">Ville :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputVille" type="text" class="form-control form-control-sm" id="inputVille" value="${ville}">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputMotDePasse" class="col-4 col-lg-2 col-form-label-sm ">Mot de passe :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputMotDePasse" type="password" class="form-control form-control-sm" id="inputMotDePasse" value="${motDePasse}">
				    </div>
				    <label for="inputConfirmation" class="col-4 col-lg-2 col-form-label-sm ">Confirmation :</label>
				    <div class="col-6 col-lg-3">
				    	<input name="inputConfirmation" type="password" class="form-control form-control-sm" id="inputConfirmation" value="${confirmationMDP}">
				    </div>
				</div>
				
				<div class="row justify-content-around connexion text-center">

					<div class="col-10 ">
						<div class="d-inline">
							<button class="btn btn-creation btn-primary" type="submit">Créer</button>
						</div>
						<div class="d-inline">
							<a class="btn btn-annulation btn-primary" href="./ServletSeConnecter" role="button">Annuler</a>
						</div>
					</div>

				</div>
			
			</form>

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