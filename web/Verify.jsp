<%-- 
    Document   : Verify
    Created on : 2020-4-12, 20:00:16
    Author     : Jianqing Gao
    Desctription : This is a page where user can verify their account.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <title>Verify Your Account</title>
        <%--This is importing the class to call the static method of it--%>
        <jsp:useBean id="storageClass" class="canvas.connector.LuckyStorage" scope="page" ></jsp:useBean> 
        <%--Creates a default instance of the database access.--%>
        <c:set var="storage" value="${storageClass.getDefaultInstance()}" scope="page"></c:set>
        </head>
        <body style="background-image: url('images2/pinkbg.webp'); background-size: 100%; background-color: #ffcccc; ">
            <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
            <br>
            <h1 class='centralized center-text'>Verification</h1>
            <br>
        </header>
        <c:set var="bash" value="${param.bash}" />


        <!--Body content-->
        <div class='container '>
            <h4>Your verification status.</h4>
            <c:choose>
                <c:when test="${empty bash}">
                    <h2 class='red center-text'>&#128577;The bashcode is empty.Please check your URL.</h2>
                </c:when>
                <c:otherwise>
                    <%--Not empty bash--%>
                    <c:choose>
                        <%--User passed verification--%>
                        <c:when test="${storage.verifyUser_VerifyBash(bash)}">
                            <h2 class='green center-text'>Your account is verified! You may <a href="index">login</a> to your account now!</h2>
                        </c:when>
                        
                            <%--Verification not passed--%>
                            <c:otherwise>
                                <h2 class="red center-text">
                                    &#128577;The link is terminated. Please check your newest email for update.
                                </h2>
                                <p>
                                    Support: support@gaogato.com
                                </p>
                                
                            </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
