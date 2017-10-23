<%-- 
    Document   : DogUpdate
    Created on : 22 oct. 2017, 13:10:41
    Author     : basilechatillon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a Dog</title>
    </head>
    <body>
        <form method="post">
            Enter the dog's attributs that you want to update! (The one you do not touch will stay the same)
            <br> Name of the dog : <input type="text" name="name" value=${requestScope.dogToModify.name}><br>
            <br> Age of the dog : <input type="number" name="age" value=${requestScope.dogToModify.age}><br>
            <br> Weight of the dog : <input type="number" step="0.1" name="weight" value=${requestScope.dogToModify.weight}><br>
            <br> Quote of the dog : <input type="text" name="quote" value=${requestScope.dogToModify.quote}><br>
            <br><input type="hidden" name="id" value=${requestScope.dogToModify.ID}><br>

            <input type="submit" value="Submit">
        </form> 
    </body>
</html>
