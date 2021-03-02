<%-- 
    Document   : MyRedirectsEmbedded
    Created on : May 6, 2020, 8:04:55 PM
    Author     : jianqing
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <jsp:useBean id="timeParser" scope="page" class="canvas.utils.TimeConverter">

        </jsp:useBean>
        <title>My Redirects</title>
    </head>
    <body style="width: 100%; background-color: rgba(255,255,255,0)">
        ${myRedirectEmbeddedMessage}
        <table class="table table-strip" style="width: 100%;">
            <th>
                Name
                <form class="form-inline" action="MyRedirectsEmbedded" method="GET">
                    <input type="hidden" name="offset" value="${offset}">
                    <!--determine the sort mode-->
                    <c:choose>

                        <c:when test='${tableSort.equals("name.DESC")}'>
                            <c:set var="buttontext" value="&#128317;"></c:set>
                            <c:set var="nextsort" value="name.ASC"></c:set>
                        </c:when>
                        <c:when test='${tableSort.equals("name.ASC")}'>
                            <c:set var="buttontext" value="&#128316;"></c:set>
                            <c:set var="nextsort" value="name.DESC"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buttontext" value="&#9210;"></c:set>
                            <c:set var="nextsort" value="name.DESC"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="action" value="select">
                    <input type="hidden" value="${nextsort}" name="sort">
                    <button type="submit" class="transparentButton">
                        ${buttontext}
                    </button>
                </form>
            </th>
            <th>
                Generated Time
                <form class="form-inline " action="MyRedirectsEmbedded" method="GET">
                    <input type="hidden" name="offset" value="${offset}">
                    <!--determine the sort mode-->
                    <c:choose>
                        <c:when test='${tableSort.equals("generatedTime.DESC")}'>
                            <c:set var="buttontext2" value="&#128317;"></c:set>
                            <c:set var="nextsort2" value="generatedTime.ASC"></c:set>
                        </c:when>
                        <c:when test='${tableSort.equals("generatedTime.ASC")}'>
                            <c:set var="buttontext2" value="&#128316;"></c:set>
                            <c:set var="nextsort2" value="generatedTime.DESC"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buttontext2" value="&#9210;"></c:set>
                            <c:set var="nextsort2" value="generatedTime.DESC"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="action" value="select">
                    <input type="hidden" value="${nextsort2}" name="sort">
                    <button type="submit" class="transparentButton">
                        ${buttontext2}
                    </button>
                </form>
            </th>
            <th>
                ID
                <form class="form-inline " action="MyRedirectsEmbedded" method="GET">
                    <input type="hidden" name="offset" value="${offset}">
                    <!--determine the sort mode-->
                    <c:choose>
                        <c:when test='${tableSort.equals("id.DESC")}'>
                            <c:set var="buttontext2" value="&#128317;"></c:set>
                            <c:set var="nextsort2" value="id.ASC"></c:set>
                        </c:when>
                        <c:when test='${tableSort.equals("id.ASC")}'>
                            <c:set var="buttontext2" value="&#128316;"></c:set>
                            <c:set var="nextsort2" value="id.DESC"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buttontext2" value="&#9210;"></c:set>
                            <c:set var="nextsort2" value="id.DESC"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="action" value="select">
                    <input type="hidden" value="${nextsort2}" name="sort">
                    <button type="submit" class="transparentButton">
                        ${buttontext2}
                    </button>
                </form>
            </th>
            <th>
                Targeted URL
            </th>
            <th>
                Visits
                <form class="form-inline " action="MyRedirectsEmbedded" method="GET">
                    <input type="hidden" name="offset" value="${offset}">
                    <!--determine the sort mode-->
                    <c:choose>
                        <c:when test='${tableSort.equals("visited.DESC")}'>
                            <c:set var="buttontext2" value="&#128317;"></c:set>
                            <c:set var="nextsort2" value="visited.ASC"></c:set>
                        </c:when>
                        <c:when test='${tableSort.equals("visited.ASC")}'>
                            <c:set var="buttontext2" value="&#128316;"></c:set>
                            <c:set var="nextsort2" value="visited.DESC"></c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buttontext2" value="&#9210;"></c:set>
                            <c:set var="nextsort2" value="visited.DESC"></c:set>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="action" value="select">
                    <input type="hidden" value="${nextsort2}" name="sort">
                    <button type="submit" class="transparentButton">
                        ${buttontext2}
                    </button>
                </form>
            </th>
            <th>
                Actions
            </th>

            <tbody>
                <c:forEach var="row" items="${dbObj}">
                    <tr>
                        <td>
                            <!--Name-->

                            <form action="MyRedirectsEmbedded" method="GET">
                                <input type="hidden" value="update-name" name="action">
                                <input type="hidden" value="${row.getId()}" name="id">
                                <input type="text" class="transparentButton" name="name" value="<c:out value="${row.getName()}"></c:out>">
                                </form>
                            </td>
                            <td>
                                <!--Generated Time-->
                            ${timeParser.formatDateTime(row.getGeneratedDate().plusMinutes(offset))}
                        </td>
                        <td>
                            <!--ID-->
                            ${row.getId()}
                        </td>
                        <td>
                            <!--URL Area-->
                            <form action="MyRedirectsEmbedded" method="GET">
                                <input type="hidden" value="update-url" name="action">
                                <input type="hidden" value="${row.getId()}" name="id">
                                <input type="text" size="50" class="transparentButton" name="url" value="<c:out value="${row.getRedirectTo()}"></c:out>">
                                </form>

                            </td>
                            <td>
                            ${row.getVisits()}
                        </td>
                        <td>
                            <!--Copy the link-->
                            <button class='transparentButton' title='copy link' onclick='copy("<c:out value="${row.getProxyURL()}"/>");'>
                                &#128279;
                            </button>

                            <!--Copy the code-->
                            <button class='transparentButton' title='copy code for embedding your content' onclick="copy('<c:out value="${row.getEmbedCode()}"/>')">
                                &#60;&#47;&#62;
                            </button>

                            <!--File download-->

                            <a href="#filedownload${row.getId()}" title="Download .html files">&#128196</a>

                            <div id="filedownload${row.getId()}" class="overlay">
                                <div class="popup" style="width: 44%;">
                                    <h2>Generate .html file</h2>
                                    <a class="close" href="#">&times;</a>
                                    <div class="content">
                                        <br>
                                        <form action="AgentFileDownload" method="POST" class="centralized block center-text" target="_blank">
                                            <!--<input type="hidden" name="type" value="${param.type}">-->
                                            File type: 
                                            <select name="type">
                                                <option value="1" selected>
                                                    A file itself changes dynamically
                                                </option>
                                                <option value="2">
                                                    A file that redirects when opened
                                                </option>
                                            </select>
                                            <input type="hidden" name="url" value="${row.getProxyURL()}">
                                            <input type="hidden" name="urltype" value="1">
                                            <br><br>
                                            <label for="title-input">
                                                Title:
                                                <input type="text" placeholder="Title of the page" name="title" value="${row.getName()}" required id="title-input">
                                            </label><br><br>
                                            <button class="btn btn-primary">
                                                Download Now
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            
                                <form action="RedirectHistory" method="GET" style="display:inline;">
                                    <button class="transparentButton" type="submit" title="More details about this link">
                                        &#128221;
                                    </button>
                                    <input type="hidden" name="id" value="${row.getId()}">
                                </form>                
                            
                            <a href="#deleteconf${row.getId()}">&#128465;</a>
                            <%--confirmation frame--%>
                            <!--Confirmation frame for #deleteconf${row.getId()}-->

                            <div id="deleteconf${row.getId()}" class="overlay">
                                <div class="popup" style="width: 44%;">
                                    <h2>Confirmation</h2>
                                    <a class="close" href="#">&times;</a>
                                    <div class="content">
                                        Are you sure to delete this record?<br>
                                        Link Name: <c:out value="${row.getName()}"></c:out>
                                            <div class="row">
                                                <!--Yes-->
                                                <div class="col">
                                                    <form action="MyRedirectsEmbedded" method="GET">
                                                        <input type="hidden" name="action" value="delete" />
                                                        <input type="hidden" name="id" value="${row.getId()}" />
                                                    <button class="btn btn-danger" type="submit" title="Delete this redirect">Yes &#128520;</button>
                                                </form>
                                            </div>
                                            <!--No-->   
                                            <div class="col">
                                                <a href="#">
                                                    <button class="btn btn-success">
                                                        No &#128519;
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
        <c:if test="${dbObj.isEmpty()}">
        <center>You don't have any redirect links set up yet!</center>
        </c:if>

</body>
</html>
