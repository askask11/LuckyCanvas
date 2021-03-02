<%-- 
    Document   : index
    Created on : 2020-4-10, 16:43:31
    Author     : Jianqing Gao
    Desctription : This is the main page of this application.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <title>💯 The Lucky Canvas ✨</title>
    </head>
    <body style="background-color: #ffcccc; background-image: url('images2/pinkbg.webp'); background-size: 100%; ">
        <!--Main header-->
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        </header>


        <div class="jumbotron jumbotron-fluid pinkBg" >

            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="container">
                            <!-- Main title -->   
                            <h1>Welcome to use the Lucky Canvas ✨</h1>
                            <h4>Ready to check your grade?</h4>
                            <p>❌ xxx.instructure.com😐</p>
                            <p>✔ luckycanvas.cn😎</p>
                            <a href="SignUp">Sign-up now!</a>
                            <p>Note: You have to have a canvas student account to use this application.</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="container">
                            <img src="images/lucky-canvas.gif" width="393" alt="luckycanvas">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Main Introductory content-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <form action="SignUp" method="GET">
                        <button class="arrowbutton centralized center-text block" style="margin-top: 16%;" type="submit"><span>Sign up Now</span></button>
                    </form>
                </div>

                <div class="col">
                    <h4>Member Sign-in</h4>
                    ${signInMessage}

                    <form action="index" method="POST">

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
                            <input type="password" class="form-control" name="password" placeholder="Password" required id="password-input">
                            <br>
                        </div>
                        <input type="hidden" name="action" value="sign-in">
                        <div class="input-group mb-3 inline">
                            <img src="Captcha" onclick="this.src = 'Captcha'" style="cursor: pointer;" title="Click to change code" />
                            <input type="text" name="captcha" required placeholder="Enter verification code" autocomplete="off">
                        </div>
                        <button type="submit" class="btn btn-primary">&#128640; GO!</button>

                    </form>

                </div>

            </div>

        </div>


        <h2 class="center-text" style="background-color: #ff6666; padding-top: 6px; padding-bottom: 6px; margin-bottom: 20px; margin-top: 16px;">
            Our Promises
        </h2>

        <div class="container">
            <div class="row">
                <!-- A brief introduction about the interactive part -->
                <div class="col">
                    <h4 class="center-text">
                        Super Interactive🎉
                    </h4>
                    <p>
                        📩 <strong class="red">Assignment graded!</strong><BR>
                        <strong>Feel🧐</strong> your grade before you see your grade!
                        Get a 💯? Cheer! 🥂<br>
                        Not doing good?😕 Don't be too stressful🤗. <BR>
                        You are great!🤞 You deserve this little surprise in your life!
                    </p>
                </div>

                <!-- Promise the user the grade will be true -->
                <div class="col">
                    <h4>
                        Real Grade Promise 📃 ✔
                    </h4>
                    <p>
                        All feedback you see from this website comes from the actual canvas system. <br>
                        We will neither edit your data, nor make fake grades for you. <br>
                        There won't be such thing as "internal data" as well.
                    </p>
                </div>
            </div>

            <br>
            <!-- Privacy issue promise -->
            <div class="">
                <h4>
                    Privacy Promise 🔐 ✔
                </h4>

                We take your privacy seriously.<br>
                <ol class="no-indent-list" id="main-privacy-list">
                    <li>
                        None of your information will be shared outside of the security system.
                    </li>

                    <li>
                        A standard encryption is used to protect your data.
                    </li>

                    <li>
                        We will not use your canvas to check your grade unless you allow us to.
                        Your grade will still be confidential as your normal canvas system.
                    </li>

                    <li>
                        Your authorization code in your account will be used by you only.
                    </li>

                    <li>
                        Although we need your canvas authorization to process your data, 
                        saving the authorization code to your account is optional. If you choose not to,
                        your code will not be saved to our database(You will need to manually enter your code every time).
                    </li>
                </ol>


            </div>

        </div>

        <!-- Sign up pop up form without js-->
        <%--<div id="signUpPopup" class="overlay">
            <div class="popup" style="height: 300px; width:53%; ">
                <!--Title here-->
                <h2>Sign Up</h2>
                <a class="close" href="#">&times;</a>
                <!--Content here!-->
                <div style="height: 80%;">
                    <iframe src="SignUpEmbedded" frameborder="0" style="width: 100%; height: 100%; ">
                    Your browser does not support iframe. Please look into 
                    https://windowsreport.com/browser-does-not-support-iframes/
                    to see solutions. Thank you!
                    </iframe> 
                </div>
            </div>
        </div>--%>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
