<%-- 
    Document   : ChooseAssignment
    Created on : Apr 17, 2020, 7:34:15 PM
    Author     : jianqing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Assignment</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>

    </head>
    <body style="background-image: url('images/pinkbg.webp'); background-size: 100%; ">
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
            <div class="jumbotron jumbotron-fluid" style="background-color: pink;">
                <div class="container">
                    <h1>Choose An Assignment</h1>
                    <h4>
                        Please choose an assignment you would like us to show you the grade.
                    </h4>
                </div>
            </div>
        </header>

        ${chooseAssignmentMessage}
        <div class="container">
            <div class="table-responsive">
                <table class="table table-strip">
                    <thead>
                        <!--Sequence Number-->
                    <th>
                        #
                    </th>
                    <!--Name of the assignment-->
                    <th>
                        Name<form style="display:inline;" action="ChooseAssignment" method="GET">
                            <!--order by choose bar-->
                            <c:choose>
                                <c:when test="${param.order.equals('name')}">
                                    <button style="display:inline" type="submit" class="transparentButton">&#128317;</button>
                                    <input type="hidden" value="position" name="order">
                                    <input type="hidden" value="refresh" name="action">
                                </c:when>
                                <c:otherwise>
                                    <button style="display:inline;" type="submit" class="transparentButton">&#9210;</button>
                                    <input type="hidden" value="name" name="order">
                                    <input type="hidden" value="refresh" name="action">
                                </c:otherwise>

                            </c:choose>
                        </form>
                    </th>
                    <!--due date-->
                    <th>
                        Due At <form style="display:inline;" action="ChooseAssignment" method="GET">
                            <!--order by choose bar-->
                            <c:choose>
                                <c:when test="${param.order.equals('due_at')}">
                                    <button style="display:inline" type="submit" class="transparentButton">&#128317;</button>
                                    <input type="hidden" value="position" name="order">
                                    <input type="hidden" value="refresh" name="action">
                                </c:when>
                                <c:otherwise>
                                    <button style="display:inline;" type="submit" class="transparentButton">&#9210;</button>
                                    <input type="hidden" value="due_at" name="order">
                                    <input type="hidden" value="refresh" name="action">
                                </c:otherwise>

                            </c:choose>
                        </form>
                    </th>
                    <!--Number operations-->
                    <th>
                        Operations
                    </th>
                    <!--go forward-->
                    <th>
                        Launch
                    </th>
                    </thead>

                    <tbody>
                        <%--Main table of assignments--%>
                        <c:set var="i" value="0"></c:set>
                        <c:forEach var="assignment" items="${assignments}">
                            <tr>
                                <td>
                                    <!--Sequence number-->
                                    <c:out value="${i+1}"></c:out>
                                    </td>
                                    <td>
                                        <!--Name of the assignment-->
                                        <a href="${assignment.getHtml_url()}" target="_blank"><c:out value="${assignment.getName()}"></c:out></a>
                                    </td>
                                    <td>
                                        <!--Due date of the assignment-->
                                    <c:out value="${assignment.getDuedateCommonFormat()}"></c:out>
                                    </td>
                                    <td>
                                        <!--This is basically a set of icons-->

                                    <%--Due date--%>
                                    <c:if test="${assignment.isDue()}">
                                        <span title="This assignment is due." class="size32">&#9200;</span>
                                    </c:if>

                                    <%--Lock--%>

                                    <c:if test="${!assignment.isUnlocked()}">
                                        <span title="This assignment is not unlocked" class="size32">&#128274;</span>
                                    </c:if>



                                    <%--description--%>
                                    <c:if test="${assignment.getDescription()!=null}">
                                        <span title='view description' >
                                            <a href="#description-${assignment.getAssignment_id()}" class="size32">&#128221;</a>
                                        </span>
                                        <div id="description-${assignment.getAssignment_id()}" class="overlay">
                                            <div class="popup assignmentDescriptionPopup">
                                                <h2>Description for ${assignment.getName()}</h2>
                                                <a class="close" href="#">&times;</a>
                                                <div class="content" style="">
                                                    ${assignment.getDescription()}
                                                </div>
                                            </div>
                                        </div>

                                    </c:if>

                                </td>
                                <td>
                                    <a href="#popup-goal-${i}"><button type="submit" class="size32 transparentButton">&#128640;</button></a>
                                    <div id="popup-goal-${i}" class="overlay">
                                        <div class="popup">
                                            <h2>Are You Ready?</h2>
                                            <a class="close" href="#">&times;</a>
                                            <div class="content">
                                                <!--Launch button-->
                                                <%--<bs:alert level="success" title="Your assignment list is loaded successfully."></bs:alert>--%>
                                                <form action="LuckyPage" method="GET" class="form-inline" onkeydown="alert('Please click \'Continue\' to continue.') return event.key != 'Enter';" id="form-${i}">
                                                    <input type="hidden" name="index" value="${i}" />
                                                    <input type="hidden" name="action" value="confirm" />
                                                    <strong id="warning-message-${i}" class="red"></strong>
                                                    <c:set var="type" value="${assignment.getGradingTypeAsString()}" />
                                                    
                                                    <%--Determine the grading type--%>
                                                    <c:choose>
                                                        
                                                        <c:when test="${type.equals('percent') || type.equals('points')}">
                                                            <%--Grading type is percent or points.--%>
                                                            
                                                            <%--<label for="goodGrade">
                                                                <!--Popover about "good grade"-->
                                                                <bs:popover id="1" content="How much do you think will be considered as 'good'?" 
                                                                            target="popup-goal-${i}" 
                                                                            title="What is 'good' ?">
                                                                    <jsp:attribute name="body">
                                                                        Minimum grade for "good"
                                                                    </jsp:attribute>
                                                                </bs:popover>--%>
                                                                <!--    <%--Good grade--%>
                                                                <input id="good-grade-${i}" type="number" placeholder="Please enter 0-${type.equals('percent')?'100': assignment.getPoints_possible()}" class="form-control" id="goodGrade" name="goodGrade" required>
                                                                ${type.equals("percent")?"%":""}
                                                            </label>
                                                            <br>
                                                            <%--OK grade--%>
                                                            <label for="okGrade">Minimum grade for "ok" <input id="ok-grade-${i}" type="number" placeholder="Pleade enter 0-${type.equals('percent')?'100': assignment.getPoints_possible()}" class="form-control" id="okGrade" name="okGrade" required>${type.equals("percent")?"%":""}</label>
                                                            
                                                            <label for="delay">Delay(s): <input type="number" id="delay-${i}" name="delay"></label>
                                                            <button type="button" class="btn btn-primary" onclick="checkSubmitGrade_points(${i}, ${type.equals('percent')?100:assignment.getPoints_possible()}, 0)">Continue</button>-->
                                                            
                                                            <%--Script for checking user input--%>
                                                            <button type="submit" class="btn btn-primary">Continue!</button>
                                                        </c:when>
                                                        <c:when test="${type.equals('letter_grade')}">
                                                            <%--Letter grade as grading type.--%>
                                                            <!--Warning message-->
                                                            <bs:alert level="warning" title="Not Supported" >
                                                                
                                                                <jsp:attribute name="message">
                                                                    Type: letter_grade<br>
                                                                    This assignment appears to be a letter grade assignment. It is temporarily not supported. If you would like it to be supported as soon as possible, please contact support@gaogato.com
                                                                </jsp:attribute>
                                                            </bs:alert>
                                                        </c:when>
                                                        <c:when test="${type.equals('gpa_scale')}">
                                                            <%--GPA scale as grading type.--%>
                                                            <!--Warning message-->
                                                            <bs:alert level="danger" title="Not supported" >
                                                                
                                                                <jsp:attribute name="message">
                                                                    Type: gpa_scale<br>
                                                                    If you see this message,  please contact support@gaogato.com
                                                                </jsp:attribute>
                                                            </bs:alert>
                                                        </c:when>  
                                                        <c:when test="${type.equals('pass_fail')}">
                                                            <!--<input type="hidden" value="100" name="goodGrade">
                                                            <input type="hidden" value="0" name="okGrade">
                                                            <input type="number" id="delay-${i}" name="delay">-->
                                                            <%--Pass/fail as grading type--%>
                                                            <!---<button class="btn btn-primary">Continue</button>---> Not support pass/fail for now.
                                                        </c:when>
                                                        <c:otherwise>
                                                            <%--Other grading types--%>
                                                            <bs:alert level="danger" title="Not supported yet">
                                                                <jsp:attribute name="message">
                                                                    Type: ${assignment.getGradingTypeAsString()} <br>
                                                                    Please contact support@gaogato.com! Thanks!
                                                                </jsp:attribute>
                                                            </bs:alert>
                                                        </c:otherwise>
                                                                    
                                                    </c:choose>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}"></c:set>
                        </c:forEach>

                    </tbody>

                </table>


            </div>
        </div>



    </body>
</html>
