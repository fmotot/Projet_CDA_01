<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

	<h1>TrocEncheres.org</h1>

	<div class="container">
		
		<div class="center-content">
		
		<h2>Creer un compte</h2>
		
		<div class="form-group row justify-content-center">
			<label for="inputPseudo" class="col-4 col-form-label-sm ">Pseudo :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputPseudo">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputNom" class="col-4 col-form-label-sm ">Nom :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputNom">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputPrenom" class="col-4 col-form-label-sm ">Prénom :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputPrenom">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputEmail" class="col-4 col-form-label-sm ">Email :</label>
			<div class="col-6">
				<input type="email" class="form-control form-control-sm" id="inputEmail">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputTelephone" class="col-4 col-form-label-sm ">Téléphone :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputTelephone">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputRue" class="col-4 col-form-label-sm ">Rue :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputRue">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputCodePostal" class="col-4 col-form-label-sm ">Code postal :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputCodePostal">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputVille" class="col-4 col-form-label-sm ">Ville :</label>
			<div class="col-6">
				<input type="text" class="form-control form-control-sm" id="inputVille">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputMotDePasse" class="col-4 col-form-label-sm ">Mot de passe :</label>
			<div class="col-6">
				<input type="password" class="form-control form-control-sm" id="inputMotDePasse">
			</div>
		</div>
		<div class="form-group row justify-content-center">
			<label for="inputConfirmation" class="col-4 col-form-label-sm ">Confirmation :</label>
			<div class="col-6">
				<input type="password" class="form-control form-control-sm" id="inputConfirmation">
			</div>
		</div>
		
			
			<div class="row justify-content-around connexion text-center">
			
				<div class="col-10 ">
					<div class="d-inline"><a class="btn btn-creation btn-primary" href="./ServletCreationCompte" role="button">Créer</a></div>
					<div class="d-inline"><a class="btn btn-annulation btn-primary" href="./ServletSeConnecter" role="button">Annuler</a></div>
				</div>
				
			</div>
		
		</div>

	</div>


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