<!-- Antony / code -->
<!-- Rudy / EL  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="./css/findevente.css">

<title>TrocEncheres - Fin de vente</title>
</head>
<body>

	<%@ include file="Header.jspf"%>

	<%@ include file="Alerte.jspf"%>

	<c:if test="${saved}">
		<div class="container mt-5 text-center" id="success-alert">
			<div class="alert alert-success" role="alert">
				<strong>Le retrait de l'article a été pris en compte</strong>
			</div>
		</div>
	</c:if>

	<h2 class="text-center">
		<c:choose>
			<c:when
				test="${utilisateur.noUtilisateur == vente.maxEnchere.acheteur.noUtilisateur}">
				<p>Vous avez remporté la vente.</p>
			</c:when>
			<c:otherwise>
				<p>${vente.maxEnchere.acheteur.pseudo} a remporté la vente</p>
			</c:otherwise>
		</c:choose>
	</h2>

	<div class="container">

		<div class="row col-12 mx-auto d-lg-none">
			<h3 class="nomArticle">${vente.nomArticle}</h3>
			<div class="image mb-3"></div>
		</div>

		<div class="row">

			<div class="col-lg-4 d-none d-lg-block my-auto">
				<div class="image"></div>
			</div>

			<div class="content-vente col-12 col-lg-7">
				<div class="d-none d-lg-block mb-3">
					<h3 class="nomArticle">${vente.nomArticle}</h3>
				</div>

				<div class="form-group row">
					<label for="staticMeilleureOffre" class="col-5 col-lg-3 label-bold">Description
						:</label>
					<div class="col-6 col-lg-9">
						<p>${vente.description}</p>
					</div>
				</div>

				<div class="form-group row">
					<label for="staticMeilleureOffre" class="col-5 col-lg-3 label-bold">Meilleure
						offre :</label>
					<div class="col-6 col-lg-9">
						<p>${vente.maxEnchere.mise}</p>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticMiseAPrix" class="col-5 col-lg-3 label-bold">Mise
						à Prix :</label>
					<div class="col-6 col-lg-4">
						<p>${vente.miseAPrix}</p>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticFinEnchere" class="col-5 col-lg-3 label-bold">Fin
						de l'enchère :</label>
					<div class="col-6 col-lg-4">
						<fmt:parseDate value="${vente.dateFinEncheres}"
							pattern="yyyy-MM-dd'T'HH:mm" var="myParseDate"></fmt:parseDate>
						<p>
							<fmt:formatDate value="${myParseDate}" pattern="dd/MM/yyyy"></fmt:formatDate>
						</p>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticRetrait" class="col-5 col-lg-3 label-bold">Retrait
						:</label>
					<div class="col-6 col-lg-4">
						<c:choose>
							<c:when test="${empty vente.retrait}">
								<p>${vente.vendeur.rue}</p>
								<p>${vente.vendeur.codePostal}${vente.vendeur.ville}</p>
							</c:when>
							<c:otherwise>
								<p>${vente.retrait.rue}</p>
								<p>${vente.retrait.codePostal}${vente.retrait.ville}</p>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
				<div class="form-group row">
					<label for="staticVendeur" class="col-5 col-lg-3 label-bold">Vendeur
						:</label>
					<div class="col-6 col-lg-4">
						<a
							href="./ProfilUtilisateurServlet?pseudo=${vente.vendeur.pseudo}">${vente.vendeur.pseudo}</a>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticFinEnchere" class="col-5 col-lg-3 label-bold">Téléphone
						:</label>
					<div class="col-6 col-lg-4">
						<p>${vente.vendeur.telephone}</p>
					</div>
				</div>

				<div class="row text-center text-lg-left connexion mt-3 mb-3">

					<div class="col-12">

						<c:if
							test="${utilisateur.noUtilisateur == vente.vendeur.noUtilisateur}">
							<c:if test="${!vente.retraitArticle}">
								<div class="d-inline">
									<a class="btn btn-retrait btn-primary"
										href="./RetraitEffectue?noVente=${vente.noVente}"
										role="button">Retrait effectué</a>
								</div>
							</c:if>
							<div class="d-inline">
								<a class="btn btn-contacter btn-primary"
									href="./ProfilRetraitServlet?noVente=${vente.noVente}"
									role="button">Contacter ${vente.maxEnchere.acheteur.pseudo}</a>
							</div>
						</c:if>

						<div class="d-inline">
							<a class="btn btn-back btn-primary" href="./ListeEnchereServlet"
								role="button">Back</a>
						</div>

					</div>

				</div>

			</div>


		</div>

	</div>

	<%@ include file="Footer.jspf"%>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.js"
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
		$("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
			$("#success-alert").slideUp(500);
		});
	</script>
</body>
</html>