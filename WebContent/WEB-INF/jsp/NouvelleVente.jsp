<!-- Antony / code -->
<!-- Rudy / EL  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.trocencheres.messages.LecteurMessages" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

	<div class="container">

		<div class="row">

			<div class="col-lg-4 d-none d-lg-block">
				<div class="image row align-items-center justify-content-center">Image</div>
			</div>

			<div class="content-vente col-12 col-lg-7">
			
				<h2 class="titre text-center">Nouvelle vente</h2>

				<form action="./NouvelleVenteServlet" method="post">

					<div class="form-group row ">
						<label for="inputArticle" class="col-5 col-form-label-sm label-bold">Article:</label>
						<div class="col-7 col-lg-7">
							<input value="${!empty vente.nomArticle ? vente.nomArticle : '' }" name="inputArticle" type="text"
								class="form-control form-control-sm" id="inputArticle" placeholder="Le nom qui s'affichera dans le titre de la vente" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputDescription" class="col-5 col-form-label-sm label-bold">Description:</label>
						<div class="col-7 col-lg-7">
							<textarea class="form-control" name="inputDescription" id="inputDescription" rows="3" placeholder="Une description de votre article" required>${!empty vente.description ? vente.description : '' }</textarea>
						</div>
					</div>
					
					<div class="form-group row">
    					<label for="selectCategories" class="col-5 col-form-label-sm label-bold">Catégories :</label>
    					<div class="col-7 col-lg-7">
	    					<select class="form-control form-control-sm" name="selectCategories" id="selectCategories" required>
	    					<c:forEach var="categorie" items="${listeCategorie}">
						    	<option value="${categorie.noCategorie}" 
						    	<c:if test="${categorie.noCategorie == vente.categorie.noCategorie }">
						    		selected="selected"
						    	</c:if>
						    	>${categorie.libelle}</option>
						    	</c:forEach>
	    					</select>
    					</div>
  					</div>

					<div class="form-group row">
						<label class="col-7 col-lg3 col-form-label-sm my-auto label-bold" for="uploadImage">Photo de l'article :</label>  
<!-- 						<label class="btn btn-sm my-auto"for="my-file-selector"> --> 
<!-- 						<input id="my-file-selector" type="submit" class="d-none" disabled>Uploader </label> --> 
						<button type="submit" name="submit" value="uploader" class="btn btn-secondary btn-sm" disabled>Uploader</button> 
					</div>

					<div class="image row col-12 mx-auto d-lg-none align-items-center justify-content-center">Image</div>

					<div class="form-group row">
						<label for="inputPrixDeBase" class="col-5 col-form-label-sm label-bold">Prix initial :</label>
						<div class="col-7 col-lg-7">
							<input value="${!empty vente.miseAPrix ? vente.miseAPrix : '' }" name="inputPrixDeBase" type="number" class="form-control form-control-sm" id="inputPrixDeBase" placeholder="Mise à prix" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputFinEnchere" class="col-5 col-form-label-sm label-bold">Fin de l'enchère :</label>
						<div class="col-7 col-lg-7">
    					<fmt:parseDate value="${vente.dateFinEncheres}" pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate>
							<input value="<fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd"></fmt:formatDate>" type="date" name="inputFinEnchere" id="inputFinEnchere" max="3000-12-31" min="1000-01-01" class="form-control form-control-sm" required>
						</div>
					</div>
					
					<fieldset class="border pr-2 pl-2">
	   					<legend class="col-4 col-lg-3 my-auto">Retrait</legend>
	   					<div class="form-group row justify-content-center ">
							<label for="inputRue" class="col-5 col-form-label-sm label-bold">Rue :</label>
							<div class="col-7 col-lg-4">
								<input value="${!empty vente.retrait.rue ? vente.retrait.rue : '' }" name="inputRue" type="text" class="form-control form-control-sm" id="inputRue">
							</div>
						</div>
						
						<div class="form-group row justify-content-center ">
							<label for="inputCodePostal" class="col-5 col-form-label-sm label-bold">Code Postal :</label>
							<div class="col-7 col-lg-4">
								<input value="${!empty vente.retrait.codePostal ? vente.retrait.codePostal : '' }" name="inputCodePostal" type="text" class="form-control form-control-sm" id="inputCodePostal">
							</div>
						</div>
						
						<div class="form-group row justify-content-center ">
							<label for="inputVille" class="col-5 col-form-label-sm label-bold">Ville :</label>
							<div class="col-7 col-lg-4">
								<input value="${!empty vente.retrait.ville ? vente.retrait.ville : '' }" name="inputVille" type="text" class="form-control form-control-sm" id="inputVille">
							</div>
						</div>
					</fieldset>		

					<div class="row justify-content-around connexion text-center">

						<div class="col-12">
							<div class="d-inline mr-2">
								<button type="submit" name="submit" value="publier" class="btn btn-site btn-primary">Publier</button>
							</div>
							<div class="d-inline mr-2">
								<button type="submit" name="submit" value="enregistrer" class="btn btn-site btn-secondary " disabled>Enregistrer</button>
							</div>
							<div class="d-inline">
								<a class="btn btn-site btn-primary" href="./ListeEnchereServlet" role="button">Annuler</a>
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