<!-- Antony  -->

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
<link rel="stylesheet" type="text/css" href="./css/administrateur.css">

<title>TrocEncheres - Panel Administrateur</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>

	<div class="container">
		
		<div class="center-content justify-content-center">
	
			<form action="./ServletSeConnecter" method="post">
				<div class="form-group">
    				<label for="exampleFormControlSelect1">Example select</label>
				    <select class="form-control" id="exampleFormControlSelect1">
				      <option>Utilisateur</option>
				    </select>
  				</div>
			</form>
			
			<div class="row justify-content-around connexion text-center">
				
					<div class="col-10">
						<div class="d-inline">
							<button type="submit" name="submit" value="enregistrer" class="btn btn-enregistrer btn-primary">Désactiver</button>
						</div>
						<div class="d-inline">
							<button type="submit" name="submit" value="supprimer" class="btn btn-supprimer btn-primary">Supprimer</button>
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