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
<link rel="stylesheet" type="text/css" href="./css/listeenchere.css">

<title>TrocEncheres - Liste Enchère</title>
</head>
<body>

	<div class="row">
		<div class="col-12 col-lg-7 my-auto">
			<h1>TrocEncheres.org</h1>
		</div>

		<div class="col-12 col-lg-5 my-auto">
			<ul>
				<li><a href="">Vendre un article</a></li>
				<li><a href="">Mon profil</a></li>
				<li><a href="">Déconnexion</a></li>
			</ul>
		</div>
	</div>

	<div id="liste-enchere" class="container">

		<h2>Filtres :</h2>

		<div class="row">

			<form action="./ListeEnchereServlet" method="post">

				<div class="col-12 col-lg-3">
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" name="mesVentes"
								id="gridCheck"> <label class="form-check-label"
								for="gridCheck">Mes ventes</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								name="mesEncheres" id="gridCheck"> <label
								class="form-check-label" for="gridCheck">Mes enchères en
								cours</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								name="mesAcquisitions" id="gridCheck"> <label
								class="form-check-label" for="gridCheck">Mes
								acquisitions</label>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox"
								name="autresEncheres" id="gridCheck"> <label
								class="form-check-label" for="gridCheck">Autres enchères</label>
						</div>
					</div>
				</div>

				<!-- 	Recherche pour le desktop	 -->
				<div class="col-12 col-lg-8 d-none d-lg-block mx-auto my-auto">
					<div class="input-group row col-10 col-lg-8 mb-3">
						<div class="input-group-prepend">
							<div class="input-group-text">0-</div>
						</div>
						<input type="text" class="form-control" id="inlineFormInputGroup"
							name="recherche" placeholder="Le nom de l'article contient">
					</div>

					<div class="form-group row col-12 col-lg-8">
						<label class="col-4 col-lg-3 my-auto" for="inputCategories">Catégories
							:</label> <select id="inputCategories" name="categorie"
							class="form-control col-8 col-lg-9">
							<option selected>Toutes</option>
						</select>
					</div>
				</div>

				<!-- 	Recherche pour le mobile	 -->
				<div class="col-12 col-lg-6 d-lg-none">
					<div class="form-group mt-3 row col-12 col-lg-6">
						<label class="my-auto col-4" for="inputCategories">Catégories</label>
						<select id="inputCategories" name="categorie"
							class="form-control col-8 my-auto">
							<option selected>Toutes</option>
						</select>
					</div>

					<div class="input-group mt-3 row col-10 mx-auto">
						<div class="input-group-prepend">
							<div class="input-group-text">0-</div>
						</div>
						<input type="text" class="form-control" id="inlineFormInputGroup"
							name="recherche" placeholder="Le nom de l'article contient">
					</div>
				</div>

				<div class="row col-lg-4 d-none d-lg-block mt-3">
					<button class="btn btn-rechercher btn-primary" type="submit">Rechercher</button>
				</div>

				<div class="row col-10 d-lg-none mx-auto mt-3">
					<button class="btn btn-rechercher btn-primary" type="submit">Rechercher</button>
				</div>

			</form>

		</div>

		<div class="container">

			<div class="row">
				<c:forEach var="vente" items="${listeVente}">
					<div class="jumbotron col-12 col-lg-5 mx-auto">
						<div class="row">
							<div class="col-3 ">
								<div class="image"></div>
							</div>

							<div class="col-8 ml-3">
								<a href="#">${vente.nomArticle}</a><a href="#"> En cours</a>
								<p>Prix : ${vente.maxEnchere} points  ${vente.classement != 0 ? 'Classement :' vente.classement : ''} </p>
								<p>Fin de l'enchère : ${vente.dateFinEncheres}</p>
								<p>Retrait : ${vente.retrait.rue}</p>
								<p>Retrait : ${vente.retrait.codePostal} ${vente.retrait.ville}</p>
								<p>Vendeur : ${vente.vendeur.pseudo}</p>
							</div>
						</div>
					</div>
				</c:forEach>
				
				
				<div class="jumbotron col-12 col-lg-5 mx-auto">
					<div class="row">
						<div class="col-3">
							<div class="image"></div>
						</div>

						<div class="col-8 ml-3">
							<a href="#">PC Gamer pour travailler</a><a href="#"> En cours</a>
							<p>Prix : $points Classement : $1</p>
							<p>Fin de l'enchère : $date</p>
							<p>Retrait : $adresse</p>
							<p>
								Vendeur : <a></a>$pseudo
							</p>
						</div>
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