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
<link rel="stylesheet" type="text/css" href="./css/profilutilisateur.css">

<title>TrocEncheres - Profil Utilisateur</title>
</head>
<body>

	<h1>TrocEncheres.org</h1>

	<div class="container">

		<div class="center-content">

			<h2>$Utilisateur.pseudo</h2>

				<div class="form-group row justify-content-center">
   					<label for="staticPseudo" class="col-4 col-lg-2 col-form-label-sm my-auto">Pseudo :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticPseudo" value="Splanch">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticNom" class="col-4 col-lg-2 col-form-label-sm my-auto">Nom :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticNom" value="Rupin">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticPrenom" class="col-4 col-lg-2 col-form-label-sm my-auto">Prénom :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticPrenom" value="Jean">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticEmail" class="col-4 col-lg-2 col-form-label-sm my-auto">Email :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticEmail" value="jeanrupin@example.com">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticTelephone" class="col-4 col-lg-2 col-form-label-sm my-auto">Téléphone :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticTelephone" value="0102030405">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticRue" class="col-4 col-lg-2 col-form-label-sm my-auto">Rue :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticRue" value="15 rue de l'eni">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticCodePostal" class="col-4 col-lg-2 col-form-label-sm my-auto">Code postal :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticCodePostal" value="35000">
    				</div>
 			    </div>
 			    <div class="form-group row justify-content-center">
   					<label for="staticVille" class="col-4 col-lg-2 col-form-label-sm my-auto">Ville :</label>
    				<div class="col-6 col-lg-4">
      					<input type="text" readonly class="form-control-plaintext my-auto" id="staticVille" value="Rennes">
    				</div>
 			    </div>
 			    

			<div class="row justify-content-around connexion text-center">

				<div class="col-10">
					<div class="d-inline">
						<a class="btn btn-retour btn-primary" href="./ServletMonProfil" role="button">Retour</a>
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