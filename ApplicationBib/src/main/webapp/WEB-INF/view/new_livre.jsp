<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.bib.app.entities.Livre" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Ajouter un nouveau livre</title>
</head>
<body>
    <h2>Ajouter un nouveau livre</h2>

    <form action="<%= request.getContextPath() %>/livres/save" method="post">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required /><br/><br/>

        <label for="auteur">Auteur :</label>
        <select id="auteur" name="auteur.id" required>
            <%
                List<com.bib.app.entities.Auteur> auteurs = (List<com.bib.app.entities.Auteur>) request.getAttribute("auteurs");
                for (com.bib.app.entities.Auteur auteur : auteurs) {
            %>
                <option value="<%= auteur.getId() %>"><%= auteur.getNom() %></option>
            <% } %>
        </select><br/><br/>

        <label for="categorie">Catégorie :</label>
        <select id="categorie" name="categorie.id" required>
            <%
                List<com.bib.app.entities.Categorie> categories = (List<com.bib.app.entities.Categorie>) request.getAttribute("categories");
                for (com.bib.app.entities.Categorie categorie : categories) {
            %>
                <option value="<%= categorie.getId_cat() %>"><%= categorie.getNom_cat() %></option>
            <% } %>
        </select><br/><br/>

        <button type="submit">Enregistrer</button>
    </form>

    <br/><a href="<%= request.getContextPath() %>/livres">Retour à la liste des livres</a>
</body>
</html>
