<%-- 
    Document   : SignInEmbedded
    Created on : Apr 15, 2020, 4:03:13 PM
    Author     : jianqing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In Embedded&#128273;</title>
    </head>
    <body>
        ${signInMessage}
        <form action="SignInEmbedded" method="POST">
            
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">&#128238;</span>
                    </div>
                    
                    <input type="email" class="form-control" name="email" placeholder="Email" required id="email-input">
                    <br>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">&#128273;</span>
                    </div>
                    <input type="password" class="form-control" name="password" placeholder="password" required id="password-input">
                    <br>
                </div>
                <input type="hidden" value="sign-in" name="action">
                <div class="input-group">
                     <img src="Captcha" onclick="this.src = 'Captcha' " style="cursor: pointer;" title="Click to change code" />
                     <input type="text" name="captcha" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">&#128640; GO!</button>
            
            
        </form>
    </body>
</html>
