<%-- 
    Document   : AgentFileEmbedded
    Created on : Apr 29, 2020, 11:30:58 PM
    Author     : jianqing
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
    </head>
    <body style="background-color: rgba(255,255,255,0)">
        ${agentFileEmbedded}

        <c:if test="${passAgentFile}">
            <c:if test="${param.type == 0}">
                <c:out value="${agentFileOutputCode}"></c:out>
            </c:if>

            
            <c:if test="${param.type==1||param.type==2}">
                <br><br>
                <%--Iframe page download--%>
                <form action="AgentFileDownload" method="POST" class="centralized block center-text" target="_blank">
                    <input type="hidden" name="type" value="${param.type}">
                    <input type="hidden" name="url" value="${param.url}">
                    <!--<input type="hidden" name="url-type" value="${urltype}">-->
                    You are generating ${param.type==1?"embed content file":"auto redirect file"}<br>
                   
                    <label for="title-input">
                       
                           <input type="text" placeholder="Title of the page" name="title" required id="title-input">
                    </label>
                    <button class="btn btn-primary">
                        Download Now
                    </button>
                </form>
            
            </c:if>
            
                
        </c:if>
        <c:set var="agentFileOutputCode" scope="session" value=""></c:set>
    </body>
</html>
