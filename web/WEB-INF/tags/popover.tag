<%-- 
    Document   : popover
    Created on : Apr 19, 2020, 6:09:27 AM
    Author     : jianqing
--%>

<%@tag description="This is the bootstrap class for popover" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute  name="id" description="The id that will be assigned to the popup" required="true" %>
<%@attribute name="title" description="This will appear on top of the pop up." type="java.lang.String"%>
<%@attribute name="content" description="The body content of the pop up." type="java.lang.String"%>
<%@attribute name="body" fragment="true"%>
<%@attribute name="target" description="The id where is the target after onclick." %>
<%-- any content can be specified here e.g.: --%>
<a href="#${target}" data-toggle="popover${id}" title="${title}" data-content="${content}"><jsp:invoke fragment="body"></jsp:invoke></a>
<script>
$(document).ready(function(){
  $('[data-toggle="popover${id}"]').popover();   
});
</script>