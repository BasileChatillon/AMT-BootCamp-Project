<%-- 
    Document   : DogGeneration
    Created on : 22 oct. 2017, 13:10:50
    Author     : basilechatillon
--%>

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
        <title>chien generation</title>

    </head>
    <body>
        <div class="container">
            <h2>Générer des chiens!</h2>
            <form class="form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Nombre de chiens</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" max="123456" name="nbtogenerate" class="form-control" id="email" placeholder="Enter number" value="1" required>
                    </div>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" value="Submit" class="btn btn-default">générer le(s) chien(s)</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
