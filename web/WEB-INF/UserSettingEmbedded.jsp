<%-- 
    Document   : UserSettingEmbedded
    Created on : Apr 24, 2020, 8:46:42 PM
    Author     : jianqing
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <title>Setting</title>
    </head>
    <body>
        ${userSettingMessage}
        <c:if test="${empty user}">
            <bs:alert level="danger" title="warning">
                <jsp:attribute name="message">
                    You did not login. Please login.
                </jsp:attribute>
            </bs:alert> 
        </c:if>

        <c:if test="${user != null}">
            <form action="UserSettingEmbedded" method="POST">
                <input type="hidden" name="action" value="update-username">
                <strong>Username</strong>
                <div class="input-group" >
                    <div class="input-group-prepend">
                        <span class="input-group-text">🏷</span>
                    </div>
                    <input name="username" id="username" value="${user.getUsername()}" placeholder="username" required>
                    <button class="transparentButton" type="submit">&#9989;</button>
                </div>

            </form>

            <form action="UserSettingEmbedded" method="POST">
                <input type="hidden" name="action" value="update-password">
                <strong>Password</strong>
                <div class="input-group ">
                    <div class="input-group-prepend">
                        <span class="input-group-text">&#128274;</span>
                    </div>
                    <input name="username" id="password"  placeholder="password here..." required>
                    <button class="transparentButton" type="submit">&#9989;</button>

                </div>
            </form>

            <form action="UserSettingEmbedded" method="POST">
                <input type="hidden" name="action" value="update-api">
                <strong>Canvas API key</strong>
                <div class="input-group ">
                    <div class="input-group-prepend">
                        <span class="input-group-text">&#9975;</span>
                    </div>
                    <input name="apikey" id="apikey" value="${user.getCanvasAuth()}" placeholder="api key here..." required="">
                    <button class="transparentButton" type="submit">&#9989;</button>
                </div>
            </form>


        </c:if>
    </body>
</html>
