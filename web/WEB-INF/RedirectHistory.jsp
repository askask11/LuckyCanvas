<%-- 
    Document   : RedirectHistory
    Created on : Jun 7, 2020, 5:53:09 PM
    Author     : jianqing
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Webpage Redirect History</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
        <style>
            body{
                background: #ccffff;
            }
            .deepbg, historytable tr:nth-child(even)
            {
                background-color: #66ccff;
            }
            .shallowbg, historytable tr:nth-child(odd)
            {
                background-color: #99ffff;
            }

            .row div
            {
                border-color: #000;
                border-width: 1px;
                border-style: solid;
            }
        </style>
    </head>
    <body>
        <header>

            <br>
            <h1 class='center-text'>&#128221; Webpage Redirect History &#128221; </h1>

        </header>
        ${redirectHistoryMessage}
        <div class="container">
            <form action="MyRedirectsEmbedded">
                <button class='arrowbutton' type="submit" style="background-color: #3399ff; "><span>Return</span></button> 
                <input type="hidden" value="select" name="action">
                <!--<input type="hidden" value="name.desc" name="sort">-->
                <input type="hidden" class="offset-input" name="offset">
            </form>

            <br><br>

            <div class="row">
                <div class="col center-text deepbg" style="">
                    Basic Information
                </div>
            </div>

            <div class="row">
                <div class="col-md-2 deepbg center-text">
                    Name
                </div>
                <div class="col-md-5 shallowbg center-text">
                    <c:out value="${proxy.getName()}"></c:out>
                    </div>
                    <div class="col-md-2 deepbg center-text">
                        Id
                    </div>
                    <div class="col-md-3 shallowbg center-text">
                    ${proxy.getId()}
                </div>
            </div>

            <div class="row">
                <div class="col-md-2 deepbg center-text">
                    Date Created
                </div>
                <div class="col-md-5 shallowbg center-text">
                    ${proxy.getGeneratedDate()} (UTC)
                </div>
                <div class="col-md-2 deepbg center-text">
                    Visits
                </div>
                <div class="col-md-3 shallowbg center-text">
                    ${proxy.getVisits()}
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-2 deepbg">
                    Redirect From
                </div>
                <div class="col-md-10 shallowbg">
                    <a href="${proxy.getProxyURL()}">${proxy.getProxyURL()}</a>
                </div>
            </div>
            <h3 class="center-text">&#11015;</h3>
            <div class="row">
                <div class="col-md-2 deepbg">
                    Redirect To
                </div>
                <div class="col-md-10 shallowbg">
                    <a href="${proxy.getRedirectTo()}">${proxy.getRedirectTo()}</a>
                </div>
            </div>

            <br>
            <div class="row">
                <div class="col deepbg center-text">
                    Visit History
                </div>
            </div>

            <!--History Table-->
            <table class="table table-strip">

                <th>
                    Date
                </th>

                <th>
                    User IP
                </th>

                <th>
                    Action
                </th>

                <c:forEach var="row" items="${dbObj}">
                    <tr>
                        <td>
                            <!--Generated date for id ${proxy.getId()}-->
                            ${row.getFormattedDateTime()}
                        </td>
                        <td>
                            ${row.getIp()}
                        </td>
                        <td>
                            <form action="RedirectHistory" method="GET">
                                <input name="action" type="hidden" value="delete">
                                <input name="id" type="hidden" value="${proxy.getId()}">
                                <input name="datetime" type="hidden" value="${row.getFormattedDateTime()}">
                                <button class="transparentButton" style="font-size: 32px;">&#128465;</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <script>
                var offset = -1 * (new Date().getTimezoneOffset());
                var docs = document.getElementsByClassName('offset-input');
                for (var i = 0, max = docs.length; i < max; i++) {
                    docs[i].value = offset;
                }
            </script>
        </div>
    </body>
</html>
