<%-- 
    Document   : login_success
    Created on : Dec 1, 2020, 11:01:47 AM
    Author     : jianqing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="${empty param.delay?"0":param.delay}; url=${empty user?"./index":"./Userhome?filter=all"}">
        <title>Logged In</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
    </head>
    <body style="background-image: url('images/pinkbg.webp'); background-size: 100%; background-color: #ffcccc; ">
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        </header>
        <br>
    <div class="jumbotron text-center" style="background-color: pink;">
        <h1>Processing your request...</h1><br>
        ${empty user?"<h1>You are not logged in!</h1>":""}
        Now, please wait or <a href="${empty user?"./index":"./Userhome?filter=all"}">click here</a>
    </div>
        
    </body>
</html>
