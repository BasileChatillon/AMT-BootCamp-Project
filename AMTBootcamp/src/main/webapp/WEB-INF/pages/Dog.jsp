<%-- 
    Document   : Dog
    Created on : 13 oct. 2017, 11:27:56
    Author     : basilechatillon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dogs</title>
    </head>
    <body>
        <h1>Hello Doges!</h1>
        <table>
            <th>
                <td>Nom du Chien</td>
                <td>Age du Chien</td>
                <td>Poids du Chien</td>
                <td>Le Chien dit :</td>
                <td>Pour tuer le chien</td>
            </th>
            <c:forEach var="dog" items="${requestScope.dogs}" varStatus="status">
                <tr>
                    <td>${dog.name}</td>
                    <td>${dog.age}</td>
                    <td>${dog.weight}</td>
                    <td>${dog.quote}</td>
                    tdli>${requestScope.uris[status.index]}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
    
</html>
