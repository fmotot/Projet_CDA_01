<%@page import="fr.eni.trocencheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur"); %>
<%=utilisateur.toString() %>
</body>
</html>