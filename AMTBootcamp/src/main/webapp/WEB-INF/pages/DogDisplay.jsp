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
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Dogs Display</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="https://www.nzhuntingandshooting.co.nz/attachments/f68/37001d1432844124-photos-your-dog-branch-manager.jpg">Hello Dogs!</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="http://localhost:9090/AMTBootcamp-1.0-SNAPSHOT/">Home</a></li>
                    <li><a href="http://localhost:9090/AMTBootcamp-1.0-SNAPSHOT/dog">Affichage</a></li>
                    <li><a href="http://localhost:9090/AMTBootcamp-1.0-SNAPSHOT/dog/create">Création</a></li>
                    <li><a href="http://localhost:9090/AMTBootcamp-1.0-SNAPSHOT/dog/generate">Generator</a></li>
                </ul>
            </div>
        </nav>

        <table>
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
                                <td >
                                    <a href=${requestScope.urisDelete[status.index]} onclick="return confirm('Do you really want to kill a puppy?? :(')"> Tuer le chien </a>
                                </td>
                                <td>
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
