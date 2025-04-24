<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
    <title>Liste des livres</title>
</head>
<body>
    <h2>Liste des livres</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Catégorie</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="livre" items="${listLivres}">
                <tr>
                    <td>${livre.id}</td>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur.nom}</td>
                    <td>${livre.categorie.nom_cat}</td>
                    <td>
                        <a href="<c:url value='/livres/edit/${livre.id}' />">Modifier</a>
                        <a href="<c:url value='/livres/delete/${livre.id}' />">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="<c:url value='/livres/new' />">Ajouter un nouveau livre</a>
</body>
</html>
