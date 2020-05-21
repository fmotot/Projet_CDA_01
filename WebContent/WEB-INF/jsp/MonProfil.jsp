<!-- Antony / code -->
<!-- Rudy / EL  -->
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
<link rel="stylesheet" type="text/css" href="./css/monprofil.css">

<title>TrocEncheres - Mon Profil</title>
</head>
<body>

	<%@ include file="Header.jspf" %>
	
	<%@ include file="Alerte.jspf" %>
	
	<c:if test="${saved}">
		<div class="container mt-5 text-center" id="success-alert">
			<div class="alert alert-success" role="alert" >
				<strong>Vos modifications ont été enregistrées</strong>
			</div>
		</div>
	</c:if>
	
	<div class="container">

		<div class="center-content">

			<h2 class="text-center mt-5 mb-5">Mon Profil</h2>
			
			<form class="" action="./ServletMonProfil" method="post">
			
				<div class="form-group row justify-content-center">
				    <label for="inputPseudo" class="col-4 col-lg-2 col-form-label-sm label-bold">Pseudo :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.pseudo}" name="inputPseudo" type="text" class="form-control form-control-sm" id="inputPseudo">
				    </div>
				    <label for="inputNom" class="col-4 col-lg-2 col-form-label-sm label-bold">Nom :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.nom}" name="inputNom" type="text" class="form-control form-control-sm" id="inputNom">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputPrenom" class="col-4 col-lg-2 col-form-label-sm label-bold">Prénom :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.prenom}" name="inputPrenom" type="text" class="form-control form-control-sm" id="inputPrenom">
				    </div>
				    <label for="inputEmail" class="col-4 col-lg-2 col-form-label-sm label-bold">Email :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.email}" name="inputEmail" type="email" class="form-control form-control-sm" id="inputEmail">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputTelephone" class="col-4 col-lg-2 col-form-label-sm label-bold">Téléphone :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.telephone}" name="inputTelephone" type="text" class="form-control form-control-sm" id="inputTelephone">
				    </div>
				    <label for="inputRue" class="col-4 col-lg-2 col-form-label-sm label-bold">Rue :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.rue}" name="inputRue" type="text" class="form-control form-control-sm" id="inputRue">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputCodePostal" class="col-4 col-lg-2 col-form-label-sm label-bold">Code postal :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.codePostal}" name="inputCodePostal" type="text" class="form-control form-control-sm" id="inputCodePostal">
				    </div>
				    <label for="inputVille" class="col-4 col-lg-2 col-form-label-sm label-bold">Ville :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="${utilisateur.ville}" name="inputVille" type="text" class="form-control form-control-sm" id="inputVille">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
				    <label for="inputMotDePasse" class="col-4 col-lg-2 col-form-label-sm label-bold">Mot de passe :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="" name="inputMotDePasse" type="password" class="form-control form-control-sm" id="inputMotDePasse">
				    </div>
				    <label for="inputConfirmation" class="col-4 col-lg-2 col-form-label-sm label-bold">Confirmation :</label>
				    <div class="col-6 col-lg-3">
				    	<input value="" name="inputConfirmation" type="password" class="form-control form-control-sm" id="inputConfirmation">
				    </div>
				</div>
				
				<div class="form-group row justify-content-center">
   					<label for="staticCredit" class="col-4 col-form-label-sm label-bold">Crédit :</label>
    				<div class="col-6">
      					<input value="${utilisateur.credit}" type="text" readonly class="form-control-plaintext" id="staticCredit">
    				</div>
 			    </div>
 			    
 			    <div class="row justify-content-around connexion text-center mt-3">
				
					<div class="col-10">
						<div class="d-inline">
							<button type="submit" name="submit" value="enregistrer" class="btn btn-site btn-primary">Enregistrer</button>
						</div>
						<div class="d-inline">
							<button type="submit" name="submit" value="supprimer" class="btn btn-site btn-primary">Supprimer</button>
						</div>
						<div class="d-inline">
							<a class="btn btn-site btn-primary" href="./ListeEnchereServlet" role="button">Retour</a>
						</div>
					</div>
				
				</div>
				
			</form>

		</div>
	</div>
	
	<%@ include file="Footer.jspf" %>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script>
		$("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
		    $("#success-alert").slideUp(500);
		});
	</script>
</body>
</html>