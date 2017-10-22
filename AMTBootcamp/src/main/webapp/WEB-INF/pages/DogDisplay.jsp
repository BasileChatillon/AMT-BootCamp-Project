<%-- 
    Document   : DogDisplay
    Created on : 13 oct. 2017, 11:27:56
    Author     : basilechatillon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dogs</title>
    </head>
    <body>
        <h1>Hello Doges!</h1>
        <table style=width:100%">
            <tr>
                <th>Nom du Chien</th>
                <th>Age du Chien</th>
                <th>Poids du Chien</th>
                <th>Le Chien dit :</th>
                <th>Pour tuer le chien</th>
            </tr>
            <c:forEach var="dog" items="${requestScope.dogs}" varStatus="status"> 
                <tr>
                    <td>${dog.name}</td>
                    <td>${dog.age}</td>
                    <td>${dog.weight}</td>
                    <td>${dog.quote}</td>
                    <td><a href="${requestScope.uris[status.index]}" onclick="return confirm('Do you really want to kill a puppy?? :(')"> Tuer le chien </a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
    
</html>
