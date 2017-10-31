<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Mise à jour de chien</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="https://www.nzhuntingandshooting.co.nz/attachments/f68/37001d1432844124-photos-your-dog-branch-manager.jpg">Hello Dogs!</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/AMTBootcamp-1.0-SNAPSHOT/">Home</a></li>
                    <li><a href="/AMTBootcamp-1.0-SNAPSHOT/dog">Affichage</a></li>
                    <li><a href="/AMTBootcamp-1.0-SNAPSHOT/dog/create">Création</a></li>
                    <li><a href="/AMTBootcamp-1.0-SNAPSHOT/dog/generate">Generator</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <h2>Modifier le chien!</h2>
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Nouveau nom :</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" value="${requestScope.dogToModify.name}" class="form-control" id="email" placeholder="Enter name" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Nouvel age:</label>
                    <div class="col-sm-10">
                        <input type="number" name="age" min="1" max="30" value="${requestScope.dogToModify.age}" class="form-control" id="email" placeholder="Enter age" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Nouveau poids:</label>
                    <div class="col-sm-10">
                        <input type="number" step="0.1" name="weight" min="0.1" value="${requestScope.dogToModify.weight}" class="form-control" id="email" placeholder="Enter weight" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Quote:</label>
                    <div class="col-sm-10">
                        <input type="text" name="quote" value="${requestScope.dogToModify.quote}" class="form-control" id="email" placeholder="Enter quote" required>
                    </div>
                </div>
                <input type="hidden" name="id" value="${requestScope.dogToModify.ID}">
                <input type="hidden" name="page" value="${requestScope.page}">
                <input type="hidden" name="entry" value="${requestScope.entry}">
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" value="Submit" class="btn btn-default">Modifier le chien</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
