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
            border-spacing: 15px;
            padding: 5px;
        }
        table {
            width:100%;
        }

        table#tabledisplaydog{
            border: 1px solid black;
            border-collapse: collapse;
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
        a{
            text-decoration:none
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
                <td align="right" colspan="2">Générer des chiens aléatoires <a href="http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/generate">x</a></td>
            </tr>
            <tr>
                <td align="right" colspan="2">Créer un chien <a href="http://192.168.99.100:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create">x</a></td>
            </tr>
            <tr>
                <td colspan="2">
                    <table id="tabledisplaydog">
                        <tr>
                            <th>Nom</th>
                            <th>Age</th>
                            <th>Poids</th>
                            <th>Le Chien dit</th>
                            <th>Tuer le chien</th>
                            <th>Modifier le chien</th>
                        </tr>
                        <c:forEach var="dog" items="${requestScope.dogs}" varStatus="status"> 
                            <tr>
                                <td>${dog.name}</td>
                                <td>${dog.age}</td>
                                <td>${dog.weight}</td>
                                <td>${dog.quote}</td>
                                <td align="middle" >
                                    <a href=${requestScope.urisDelete[status.index]} onclick="return confirm('Do you really want to kill a puppy?? :(')"> Tuer le chien </a>
                                </td>
                                <td align="middle">
                                    <a href=${requestScope.urisUpdate[status.index]} > modifier </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <tr>
                <td align="left">
                    Nombre de chien par pages
                    <c:forEach var="uriEntries" items="${requestScope.urisEntries}" varStatus="status"> 
                        <a href=${uriEntries}> ${requestScope.entrieValues[status.index]} </a>
                    </c:forEach>
                </td>
                <td align="right">
                    Gestion des pages 
                    <a href=${requestScope.firstPage}> << </a>
                    <a href=${requestScope.previousPage}> < </a>
                    <a href=${requestScope.nextPage}> > </a>
                    <a href=${requestScope.lastPage}> >> </a>
                </td>
            </tr>

            <tr>
                <td align="right" colspan="2">
                    Page n°${requestScope.pageActual} sur ${requestScope.pageMax}
                </td>
            </tr>
        </table>

    </body>

</html>
