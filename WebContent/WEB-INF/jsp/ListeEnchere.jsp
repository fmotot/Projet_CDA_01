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
<link rel="stylesheet" type="text/css" href="./css/listeenchere.css">

<title>TrocEncheres - Liste Enchère</title>
</head>
<body>

	<div class="container">

		<h1>TrocEncheres.org</h1>
		
		<div class="UtilisateurConnecte">$pseudo est connecté</div>

		<div class="row">
			<ul>
				<li><a href="">Vendre un article</a></li>
				<li><a href="">Mon profil</a></li>
				<li><a href="">Déconnexion</a></li>
			</ul>
		</div>

		<h2>Filtres :</h2>

		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck">Mes ventes</label>
			</div>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck">Mes enchères
					en cours</label>
			</div>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck">Mes
					acquisitions</label>
			</div>
		</div>
		<div class="form-group">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" id="gridCheck">
				<label class="form-check-label" for="gridCheck">Autres
					enchères</label>
			</div>
		</div>

		<div class="form-group mt-3 row col-12">
			<label class="my-auto col-4" for="inputCategories">Catégories</label> <select
				id="inputCategories" class="form-control col-8 my-auto">
				<option selected>Toutes</option>
			</select>
		</div>

		<div class="input-group mt-3 row col-10 mx-auto">
			<div class="input-group-prepend">
				<div class="input-group-text">0-</div>
			</div>
			<input type="text" class="form-control" id="inlineFormInputGroup"
				placeholder="Le nom de l'article contient">
		</div>

		<div class="row col-10 mx-auto justify-content-center">
			<button class="btn btn-rechercher btn-primary" type="submit">Rechercher</button>
		</div>

		<div class="jumbotron">
			<div class="row">
				<div class="col-3">
					<div class="image"></div>
				</div>

				<div class="col-8 ml-3">
					<a href="#">PC Gamer pour travailler</a><a href="#"> En cours</a>
					<p>Prix : $points Classement : $1</p>
					<p>Fin de l'enchère : $date</p>
					<p>Retrait : $adresse</p>
					<p>Vendeur : $pseudo</p>
				</div>
			</div>
		</div>

		<div class="jumbotron">
			<div class="row">
				<div class="col-3">
					<div class="image"></div>
				</div>

				<div class="col-8 ml-3">
					<a href="#">PC Gamer pour travailler</a><a href="#"> En cours</a>
					<p>Prix : $points Classement : $1</p>
					<p>Fin de l'enchère : $date</p>
					<p>Retrait : $adresse</p>
					<p>Vendeur : <a></a>$pseudo</p>
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