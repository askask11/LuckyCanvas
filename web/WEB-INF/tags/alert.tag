<%-- 
    Document   : newtag_file
    Created on : Apr 18, 2020, 9:35:29 PM
    Author     : jianqing
--%>

<%@tag description="This is a customized tag for bootstrap 4 alert" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message" required="false" fragment="true" description="This is the body of the message."%>
<%@attribute name="title" required="true" %>
<%@attribute name="level" required="true"  description="Accepted values are success, 
             info, warning, danger, primary, secondary, dark, light"%>

<%-- any content can be specified here e.g.: --%>
<div class="alert alert-${level} alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>${title}</strong> <jsp:invoke fragment="message"/>
</div>

