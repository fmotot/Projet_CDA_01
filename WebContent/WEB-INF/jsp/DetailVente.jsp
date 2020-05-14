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
<link rel="stylesheet" type="text/css" href="./css/detailvente.css">

<title>TrocEncheres - D�tail Vente</title>
</head>
<body>

	<h1>TrocEncheres.org</h1>

	<h2 class="text-center">enchere.etat</h2>
	<h2 class="text-center">${ !empty enchere.etat ? enchere.etat : '' }</h2>

	<div class="container">

		<div class="row col-12 mx-auto d-lg-none">
			<h3>PC GAMER POUR TRAVAILLER</h3>
			<div class="image mb-3"></div>
		</div>

		<div class="row">

			<div class="col-lg-4 d-none d-lg-block">
				<div class="image"></div>
			</div>

			<div class="content-vente col-12 col-lg-7">
				<div class="d-none d-lg-block">
					<h3>PC GAMER POUR TRAVAILLER</h3>
				</div>

				<div class="d-none d-lg-block">
					<div class="form-group row">
						<label for="staticMeilleureOffre"
							class="col-5 col-lg-3 col-form-label-sm my-auto">Description :</label>
						<div class="col-6 col-lg-9">
							<input type="text" readonly class="form-control-plaintext my-auto" id="staticMeilleureOffre" value="#">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<label for="staticMeilleureOffre"
						class="col-5 col-lg-3 col-form-label-sm my-auto">Meilleure offre :</label>
					<div class="col-6 col-lg-9">
						<input type="text" readonly class="form-control-plaintext my-auto" id="staticMeilleureOffre" value="#">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticMiseAPrix"
						class="col-5 col-lg-3 col-form-label-sm my-auto">Mise � Prix :</label>
					<div class="col-6 col-lg-4">
						<input type="text" readonly class="form-control-plaintext my-auto" id="staticMiseAPrix" value="#">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticFinEnchere"
						class="col-5 col-lg-3 col-form-label-sm my-auto">Fin de l'ench�re :</label>
					<div class="col-6 col-lg-4">
						<input type="text" readonly class="form-control-plaintext my-auto" id="staticFinEnchere" value="#">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticRetrait"
						class="col-5 col-lg-3 col-form-label-sm my-auto">Retrait :</label>
					<div class="col-6 col-lg-4">
						<input type="text" readonly class="form-control-plaintext my-auto" id="staticRetrait" value="#">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticVendeur"
						class="col-5 col-lg-3 col-form-label-sm my-auto">Vendeur :</label>
					<div class="col-6 col-lg-4">
						<a href="./ProfilUtilsateurServlet?pseudo=${vente.vendeur.pseudo}">#</a>
					</div>
				</div>

				<form action="./DetailVenteServlet" method="post">

					<div class="form-group row">
						<label for="inputMaProposition" class="col-5 col-lg-3 col-form-label-sm">Ma proposition :</label>
						<div class="col-3 col-lg-4">
							<input type="text" class="form-control" id="inputMaProposition">
						</div>
						<div class="col-3 col-lg-4">
							<button type="submit" class="btn btn-encherir btn-primary my-auto">Ench�rir</button>
						</div>
					</div>

				</form>

				<div class="row mt-3">
				<!-- EXPRESSION LANGAGE POUR LE BOUTON ANNULER DERNIERE ENCHERE SOUS CONDITION
					<div class="col-8 col-lg-7 text-left">
						<a class="btn btn-back btn-primary" href="./ServletCreationCompte" role="button">Annuler ma derni�re ench�re</a>
					</div> -->
				
					<div class="col-4 col-lg-4 text-left">
						<a class="btn btn-back btn-primary" href="./ServletCreationCompte" role="button">Back</a>
					</div>
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