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
<link rel="stylesheet" type="text/css" href="./css/nouvellevente.css">

<title>TrocEncheres - Nouvelle vente</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>

	<h2 class="text-center">Nouvelle vente</h2>

	<div class="container">

		<div class="row">

			<div class="col-lg-4 d-none d-lg-block">
				<div class="image"></div>
			</div>

			<div class="content-vente col-12 col-lg-7">

				<form action="./NouvelleVenteServlet" method="post">

					<div class="form-group row ">
						<label for="inputArticle" class="col-5 col-form-label-sm">Article
							:</label>
						<div class="col-7 col-lg-7">
							<input value="" name="inputArticle" type="text"
								class="form-control form-control-sm" id="inputArticle">
						</div>
					</div>
					<div class="form-group row">
						<label for="inputDescription" class="col-5 col-form-label-sm">Description:</label>
						<div class="col-7 col-lg-7">
							<textarea class="form-control" id="inputDescription" rows="3"></textarea>
						</div>
					</div>
					
					<div class="form-group row">
    					<label for="selectCategories" class="col-5 col-form-label-sm">Catégories :</label>
    					<div class="col-7 col-lg-7">
	    					<select class="form-control form-control-sm" name="selectCategories" id="selectCategories">
						    	<option>Maison</option>
						    	<option>Catégorie 2</option>
						    	<option>Catégorie 3</option>
						    	<option>Catégorie 4</option>
						    	<option>Catégorie 5</option>
	    					</select>
    					</div>
  					</div>

					<div class="form-group row">
						<label class="col-3 col-form-label-sm my-auto" for="uploadImage">Photo de l'article</label> <label class="btn btn-sm btn-primary my-auto"
						for="my-file-selector"> <input id="my-file-selector" type="file" class="d-none">Uploader</label>
					</div>

					<div class="row col-12 mx-auto d-lg-none">
						<div class="image mb-3"></div>
					</div>

					<div class="form-group row">
						<label for="inputPrixDeBase" class="col-5 col-form-label-sm">Prix initial :</label>
						<div class="col-7 col-lg-7">
							<input value="#" name="inputPrixDeBase" type="number" class="form-control form-control-sm" id="inputPrixDeBase" placeholder="Crédit">
						</div>
					</div>
					<div class="form-group row">
						<label for="inputFinEnchere" class="col-5 col-form-label-sm">Fin de l'enchère :</label>
						<div class="col-7 col-lg-7">
							<input value="#" type="date" name="inputFinEnchere" id="inputFinEnchere" max="3000-12-31" min="1000-01-01" class="form-control form-control-sm">
						</div>
					</div>
					
					<fieldset class="border pr-2 pl-2">
	   					<legend class="col-4 col-lg-3 my-auto">Retrait</legend>
	   					<div class="form-group row justify-content-center ">
							<label for="inputRue" class="col-5 col-form-label-sm">Rue :</label>
							<div class="col-7 col-lg-4">
								<input value="#" name="inputRue" type="text" class="form-control form-control-sm" id="inputRue">
							</div>
						</div>
						
						<div class="form-group row justify-content-center ">
							<label for="inputCodePostal" class="col-5 col-form-label-sm">Code Postal :</label>
							<div class="col-7 col-lg-4">
								<input value="#" name="inputCodePostal" type="text" class="form-control form-control-sm" id="inputCodePostal">
							</div>
						</div>
						
						<div class="form-group row justify-content-center ">
							<label for="inputVille" class="col-5 col-form-label-sm">Ville :</label>
							<div class="col-7 col-lg-4">
								<input value="#" name="inputVille" type="text" class="form-control form-control-sm" id="inputVille">
							</div>
						</div>
					</fieldset>		

					<div class="row justify-content-around connexion text-center mt-3 mb-3">

						<div class="col-12">
							<div class="d-inline mr-2">
								<button type="submit" name="submit" value="publier" class="btn btn-primary">Publier</button>
							</div>
							<div class="d-inline mr-2">
								<button type="submit" name="submit" value="enregistrer" class="btn btn-primary">Enregistrer</button>
							</div>
							<div class="d-inline">
								<a class="btn btn-retour btn-primary" href="./ListeEnchereServlet" role="button">Annuler</a>
							</div>
						</div>
					</div>
				</form>
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