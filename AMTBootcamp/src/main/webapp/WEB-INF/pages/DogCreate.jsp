<%-- 
    Document   : DogCreate
    Created on : 22 oct. 2017, 13:10:30
    Author     : basilechatillon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a new Dog</title>
    </head>
    <body>
        <form method="post">
            Enter the informations of the new dog to create !
            <br> Name of the dog : <input type="text" name="name" required><br>
            <br> Age of the dog : <input type="number" name="age" required><br>
            <br> Weight of the dog : <input type="number" step="0.1" name="weight" required><br>
            <br> Quote of the dog : <input type="text" name="quote" required><br>

            <input type="submit" value="Submit">
        </form> 
    </body>
</html>
