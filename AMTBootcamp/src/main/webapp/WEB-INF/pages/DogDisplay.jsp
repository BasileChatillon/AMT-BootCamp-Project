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
            padding: 5px;
            
        }
        table {
            border-spacing: 15px;
            width:100%
        }
        
        table#tabledisplaydog tr:nth-child(even) {
            background-color: #eee;
        }
        table#tabledisplaydog tr:nth-child(odd) {
           background-color:#fff;
        }
        table#tabledisplaydog th {
            background-color: black;
            color: white;
        }

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dogs</title>
    </head>
    <body>
        <h1>Hello Doges!</h1>
        <table>
            <tr>
                <td text-align="rights">générer des chiens aléatoires <a href="http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/generate">x</a></td>
            </tr>
            <tr>
                <td text-align="right">Créer un chien <a href="http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create">x</a></td>
            </tr>
            <tr>
            <table id="tabledisplaydog">
                <tr>
                    <th>Nom du Chien</th>
                    <th>Age du Chien</th>
                    <th>Poids du Chien</th>
                    <th>Le Chien dit :</th>
                    <th>Pour tuer le chien</th>
                    <th>Pour modifier le chien
                </tr>
                <c:forEach var="dog" items="${requestScope.dogs}" varStatus="status"> 
                    <tr>
                        <td>${dog.name}</td>
                        <td>${dog.age}</td>
                        <td>${dog.weight}</td>
                        <td>${dog.quote}</td>
                        <td><a href="${requestScope.urisDelete[status.index]}" onclick="return confirm('Do you really want to kill a puppy?? :(')"> Tuer le chien </a></td>
                        <td><a href="${requestScope.urisUpdate[status.index]}" > modifier </a></td>
                    </tr>
                </c:forEach>
            </table>
        </tr>
    </table>

</body>

</html>
