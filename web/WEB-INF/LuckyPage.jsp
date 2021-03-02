<%-- 
    Document   : LuckyPage
    Created on : Apr 22, 2020, 10:37:30 PM
    Author     : jianqing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <script src="js/canvasScript.js"></script>
        <title>Show Grade Time</title>
       
    </head>
    <body style="height:100%;">
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        </header>
        <iframe style="width: 100%; height: 100%;" frameborder="0" src="https://anime.luckycanvas.cn/LuckyMain.html?${assignment.getName()}∧${assignment.getDuedateCommonFormat()}∧${grade}∧${assignment.getPoints_possible()}∧1000∧1000∧0∧idk|∨">
            Your browser does not support iFrame!
        </iframe>
    </body>
</html>
