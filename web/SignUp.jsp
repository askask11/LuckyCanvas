<%-- 
    Document   : SignUpEmbedded
    Created on : 2020-4-12, 2:04:18
    Author     : Jianqing Gao
    Desctription : A page for users to sign up.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" 
        src="https://cdn.enzoic.com/js/enzoic.min.js" ></script>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>

        <title>Sign up</title>
    </head>
    <body style="background-color: #ffcccc">
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        </header>
        <div class="jumbotron jumbotron-fluid pinkBg">
            <div class="container">
                <hgroup>
                    <h1>🎉You have made a right decision!</h1>
                    <h3>We just need few more things.😛</h3>
                </hgroup>
            </div>
        </div>
        <div class="container" style="max-width: 60%;">
            ${signUpMessage}
            <form action="SignUp" method="POST" id="signUpForm">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">@</span>
                    </div>
                    <input type="email" class="form-control" name="email" placeholder="Email" required id="email-input">
                    <br><strong id="emailMessage" class="red"></strong>
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">🏷</span>
                    </div>
                    <input type="text" class="form-control" name="username" placeholder="Username" required id="username-input" autocomplete="off">
                    <br><strong id="usernameMessage" class="red"></strong>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">🔑</span>
                    </div>
                    <input type="password" class="form-control" name="password" placeholder="Password" required id="password-input">
                    <br><strong id="passwordMessage" class="red"></strong>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">🔐</span>
                    </div>
                    <input type="text" class="form-control" name="canvasauth" placeholder="Canvas Auth Code(Optional)" id="canvas-code-input" autocomplete="off">
                    <a href="https://community.canvaslms.com/docs/DOC-16005-42121018197" target="_blank"><img src="images/wen16.svg" alt="?"/></a>
                </div>

                <!--<div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">&#9200;</span>
                    </div></div>-->
                <input type="hidden" class="form-control" name="timezone" id="timezone-input">
                <strong class="red" id="captcha-message"></strong>
                <p>
                    <!--<iframe class="captcha-image" src="Captcha" frameborder="0"></iframe>-->
                    <img src="Captcha" alt="captcha" onclick="this.src = 'Captcha'" style="cursor: pointer; " title="Click to change a code.">
                    Verification Code : <input type="text" size="7" name="captcha" id="captcha-input"/>
                </p>

                <button type="button" class="btn btn-outline-primary" onclick="submitSignUp();">Sign up!</button>
            </form>
        </div>


        <!-- Apply password meter -->
        <script type="text/javascript">
            var passwordEl = document.getElementById('password-input');
            Enzoic.applyToInputElement(passwordEl);
        </script>


    </body>
</html>
