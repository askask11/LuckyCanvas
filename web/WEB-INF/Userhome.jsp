<%-- 
    Document   : Userhome
    Created on : Apr 14, 2020, 3:33:38 AM
    Author     : jianqing
    Description : This is the page where user will select the class.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.getUsername()}'s home</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
    </head>
    <body style="background-image: url('images/pinkbg.webp'); background-size: 100%; background-color: #ffcccc; ">
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        
        <div class="jumbotron" style="background-color: pink;">
            <h1 class="text-center">Welcome!</h1>
            <c:if test="${user.getCanvasAuth()!=null}">
                <p>Please choose a class to continue!</p>
                <h4>${user.getUsername()}&#39;s classes</h4>
            </c:if>
            <c:if test="${empty user.getCanvasAuth()}">
                <p>&#128522; Please enter your canvas API auth token 
                     <strong>Token:</strong><br>
                    <input type="text" class="form-control" autofocus name="token" placeholder="API token here" required />
                    <button class="btn btn-primary" type="button" style="margin-left: 16px;">Submit</button>
                </p>
            </c:if>
        </div>
            ${userhomeMessage}
        

        <!--filter-->
        <form class="form form-inline" action="Userhome" method="GET" id="filter-form">
            <p style="font-size: 18px;"> Filter : Begin Year
                <select name="filter" onchange="document.getElementById('filter-form').submit();">
                    <option value="all">All</option>
                    <optgroup label="Select a year">
                        <option value="2018" ${param.filter.equals("2018")?"selected":""}>2018</option>
                        <option value="2019" ${param.filter.equals("2019")?"selected":""} ${empty param.filter?"selected":""}>2019</option>
                        <option value="2020">2020</option>
                    </optgroup>
                </select>
                <button type="submit" class="btn btn-primary">Confirm</button>
            </p>
        </form>



        <div class="table-responsive">
            <table class="table table-strip">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Class Name</th>
                        <th>Start Year</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="i" value="0"></c:set>
                    <c:forEach var="course" items="${courses}">
                        
                            <tr>
                                <!--index-->
                                <td><c:out value="${i+1}"></c:out></td>
                            <!--Course Name-->
                            <td>${course.getName()}(${course.getCourse_code()})</td>
                            <!--start year-->
                            <td>${course.getStart_at()}</td>
                            <!--actions-->
                            <td>
                                <form action="ChooseAssignment" method="GET">
                                    <input type="hidden" value="${i}" name="course_index">
                                    <input type="hidden" value="confirm-flow" name="action">
                                    <input type="hidden" value="due_at" name="order">
                                    <button type="submit" class="transparentButton"><img src="images/rocket32.png" alt=""/></button>
                                    
                                </form>
                                </td>
                        </tr>
                       <c:set var="i" value="${i+1}"></c:set> 
                    </c:forEach>
                        
                </tbody>
            </table>
        </div>
                    <br>
                    
    </body>
</html>
