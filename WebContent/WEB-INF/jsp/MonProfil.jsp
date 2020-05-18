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
<link rel="stylesheet" type="text/css" href="./css/monprofil.css">

<title>TrocEncheres - Mon Profil</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>

	<div class="container">

		<div class="center-content">

			<h2>Mon Profil</h2>

			<form class="d-none d-lg-block" action="./ServletMonProfil" method="post">
				<div class="form-row">
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputPseudo">Pseudo :</label>
						<input value="${utilisateur.pseudo}" name="inputPseudo" type="text" class="form-control" id="inputPseudo">
					</div>
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputNom">Nom :</label> 
						<input value="${utilisateur.nom}" name="inputNom" type="text" class="form-control" id="inputNom">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputPrenom">Prenom :</label>
						<input value="${utilisateur.prenom}" name="inputPrenom" type="text" class="form-control" id="inputPrenom">
					</div>
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputEmail">Email :</label> 
						<input value="${utilisateur.email}" name="inputEmail" type="email" class="form-control" id="inputEmail">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputTelephone">T�l�phone :</label>
						<input value="${utilisateur.telephone}" name="inputTelephone" type="tel" class="form-control" id="inputTelephone">
					</div>
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputRue">Rue :</label> 
						<input value="${utilisateur.rue}" name="inputRue" type="text" class="form-control" id="inputRue">
					</div>
				</div>	
				<div class="form-row">
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputCodePostal">Code Postal :</label>
						<input value="${utilisateur.codePostal}" name="inputCodePostal" type="text" class="form-control" id="inputCodePostal">
					</div>
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputVille">Ville :</label> 
						<input value="${utilisateur.ville}" name="inputVille" type="text" class="form-control" id="inputVille">
					</div>
				</div>	
				<div class="form-row">
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputMotDePasse">Mot de passe :</label>
						<input name="inputMotDePasse" type="password" class="form-control" id="inputMotDePasse">
					</div>
					<div class="form-group col-lg-6 mx-auto">
						<label for="inputConfirmation">Confirmation :</label> 
						<input name="inputConfirmation" type="password" class="form-control" id="inputConfirmation">
					</div>
				</div>
				<div class="form-group row ">
   					<label for="staticCredit" class="col-1 col-form-label-sm my-auto">Cr�dit :</label>
    				<div class="col-1">
      					<input value="${utilisateur.credit}" type="text" readonly class="form-control-plaintext my-auto" id="staticCredit" value="0">
    				</div>
 			    </div>
 			    
 			    <div class="row justify-content-around connexion text-center">
				
					<div class="col-10">
						<div class="d-inline">
							<button type="submit" name="submit" value="enregistrer" class="btn btn-enregistrer btn-primary">Enregistrer</button>
						</div>
						<div class="d-inline">
							<button type="submit" name="submit" value="supprimer" class="btn btn-supprimer btn-primary">Supprimer</button>
						</div>
						<div class="d-inline">
							<a class="btn btn-retour btn-primary" href="./ServletMonProfil" role="button">Retour</a>
						</div>
					</div>
				
				</div>
				
			</form>
			
			<!-- Formulaire pour mobile -->
			
			<form class="d-lg-none" action="./ServletMonProfil" method="post">
			
				<div class="form-group row justify-content-center">
				    <label for="inputPseudo" class="col-4 col-form-label-sm ">Pseudo :</label>
				    <div class="col-6">
				    	<input value="${utilisateur.pseudo}" name="inputPseudo" type="text" class="form-control form-control-sm" id="inputPseudo">
				    </div>
				</div>
				  	
				<div class="form-group row justify-content-center ">
					<label for="inputNom" class="col-4 col-form-label-sm">Nom :</label>
					<div class="col-6">
				    	<input value="${utilisateur.nom}" name="inputNom" type="text" class="form-control form-control-sm" id="inputNom">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputPrenom" class="col-4 col-form-label-sm">Pr�nom :</label>
					<div class="col-6">
				    	<input value="${utilisateur.prenom}" name="inputPrenom" type="text" class="form-control form-control-sm" id="inputPrenom">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputEmail" class="col-4 col-form-label-sm">Email :</label>
					<div class="col-6">
				    	<input value="${utilisateur.email}" name="inputEmail" type="email" class="form-control form-control-sm" id="inputEmail">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputTelephone" class="col-4 col-form-label-sm">T�l�phone :</label>
					<div class="col-6">
				    	<input value="${utilisateur.telephone}" name="inputTelephone" type="tel" class="form-control form-control-sm" id="inputTelephone">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputRue" class="col-4 col-form-label-sm">Rue :</label>
					<div class="col-6">
				    	<input value="${utilisateur.rue}" name="inputRue" type="text" class="form-control form-control-sm" id="inputRue">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputCodePostal" class="col-4 col-form-label-sm">Code postal :</label>
					<div class="col-6">
				    	<input value="${utilisateur.codePostal}" name="inputCodePostal" type="text" class="form-control form-control-sm" id="inputCodePostal">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputVille" class="col-4 col-form-label-sm">Ville :</label>
					<div class="col-6">
				    	<input value="${utilisateur.ville}" name="inputVille" type="text" class="form-control form-control-sm" id="inputVille">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputMotDePasse" class="col-4 col-form-label-sm">Mot de passe :</label>
					<div class="col-6">
				    	<input name="inputMotDePasse" type="password" class="form-control form-control-sm" id="inputMotDePasse">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center ">
					<label for="inputConfirmation" class="col-4 col-form-label-sm">Confirmation :</label>
					<div class="col-6">
				    	<input name="inputConfirmation" type="password" class="form-control form-control-sm" id="inputConfirmation">
				   	</div>
				</div>
				
				<div class="form-group row justify-content-center">
   					<label for="staticCredit" class="col-4 col-form-label-sm">Cr�dit :</label>
    				<div class="col-6">
      					<input value="${utilisateur.credit}" type="text" readonly class="form-control-plaintext" id="staticCredit">
    				</div>
 			    </div>
 			    
 			    <div class="row justify-content-around connexion text-center">
				
					<div class="col-10">
						<div class="d-inline">
							<button type="submit" name="submit" value="enregistrer" class="btn btn-enregistrer btn-primary">Enregistrer</button>
						</div>
						<div class="d-inline">
							<button type="submit" name="submit" value="supprimer" class="btn btn-supprimer btn-primary">Supprimer</button>
						</div>
						<div class="d-inline">
							<a class="btn btn-retour btn-primary" href="./ListeEnchereServlet" role="button">Retour</a>
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