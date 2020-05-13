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
<link rel="stylesheet" type="text/css" href="./css/connexion.css">

<title>TrocEncheres - Se connecter</title>
</head>
<body>

	<h1>TrocEncheres.org</h1>

	<div class="container">
		
		<div class="center-content justify-content-center">
	
			<form>
				<div class="form-group row justify-content-center">
				    <label for="inputId" class="col-4 col-form-label-sm ">Identifiant :</label>
				    <div class="col-6">
				    	<input type="text" class="form-control form-control-sm" id="inputId">
				    </div>
				</div>
				  	
				<div class="form-group row justify-content-center ">
					<label for="inputPassword" class="col-4 col-form-label-sm">Mot de passe :</label>
					<div class="col-6">
				    	<input type="password" class="form-control form-control-sm" id="inputPassword">
				   	</div>
				</div>
				
				<div class="row justify-content-center connexion mb-5 text-center">
				
					<div class="col-5">
						<button type="submit" class="btn btn-primary">Connexion</button>
					</div>
					
					  <div class="custom-control custom-checkbox my-1 mr-sm-2">
    					<input type="checkbox" class="custom-control-input" id="customControlInline">
    					<label class="custom-control-label" for="customControlInline">Se souvenir de moi</label>
 					  </div>
				
				</div>
				
			</form>
			
			<div class="row justify-content-center connexion text-center">
			
				<div class="col-10 text-center">
					<a class="btn btn-creation btn-primary" href="./ServletCreationCompte" role="button">Cr�er un compte</a>
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