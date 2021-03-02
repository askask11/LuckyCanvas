<%-- 
    Document   : AgentFile
    Created on : Apr 28, 2020, 1:25:17 AM
    Author     : jianqing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agent File Tool</title>
        <%@include file="/WEB-INF/jspf/headtags.jspf" %>
    </head>
    <body style="background-image: url('images/flbg.png'); background-size: 100%;">
        <header>
            <%@include file="/WEB-INF/jspf/navbar.jspf" %>
            <div class="jumbotron" style="background-color: #ffccff; ">
                <div class="container">
                    <h1>
                        Welcome to use Agent File!
                    </h1>
                    <!--Tell user the purpose of this page.-->
                    <c:if test="${user.isAgentFileUser()}">
                        <p>
                            This is a page to help you hand in homework and allows you to change them remotely.
                        </p>
                    </c:if>
                    <c:if test="${!user.isAgentFileUser()}">
                        <p>
                            You are currently not a user of agent file. Contact support@gaogato.com
                            if you believe that this is an error.
                        </p>
                    </c:if>
                </div>
            </div>
        </header>

        <c:if test="${user.isAgentFileUser()}">
            <div style='background-color: rgba(153, 255, 255, 0.8);' class='container'>
                <h4>
                    <br>
                    Use this tool to help you generate a file or an embedded content for submission. 
                    <br>
                </h4>
                <form class="form form-inline" action="AgentFileEmbedded" target="result-iframe" method="POST" >
                    <select class="form-control" name="type">
                        <optgroup label="What do you need?">
                            <option selected value="0">
                                HTML code for embed
                            </option>
                            <option value="1">
                                File changes dynamically
                            </option>
                            <option value="2">
                                File redirects when open
                            </option>
                        </optgroup>
                    </select>
                    <br>
                    <input size="50" type="url" name="url" placeholder="Please enter the url of webpage to embed." class='form-control' required>
                    <br>

                    <select name="urltype" class='form-control'>
                        <optgroup label="What is the type of your url?">
                            <option value="0">
                                Google Document
                            </option>
                            <option value="1">
                                Others
                            </option>
                        </optgroup>
                    </select>
                    <div class="input-group">
                        <img id="captcha-image" src="Captcha" alt="Captcha" title="click to change" onclick="this.src = 'Captcha';" >
                        <input type="text" size="6" placeholder="verify" title="enter the code on the image" name="captcha"
                               autocomplete="off" required>
                    </div>
                    <button class="btn btn-primary" >
                        Submit
                    </button>
                </form>
                <br>
                <hr>
                <h4 class="center-text">
                    Next Step...
                </h4>
                <iframe id="result-frame" name="result-iframe" frameborder="0" class="centralized" style="width: 100%; height: 100%;">

                </iframe>
            </div>

            <hr>

            <div class="container" style="background-color: rgba(153, 255, 255, 0.8)">
                <h4>
                    Redirect Links <a href="#redirHelp"><img src="images/wen16.svg" alt="help" style="cursor: pointer;" /></a>

                </h4>

                <%--Insert form--%>

                <form class="form-inline"  action="MyRedirectsEmbedded" target="MyRedirectsEmbedded">
                    <input type="hidden" class="offset-input" name="offset" >
                    <input class="form-control" type="text" placeholder="Name" name="name" required>
                    <input class="form-control" size="60" type="url" placeholder="Enter the destination URL here" name="url"  required>
                    <input type="hidden" name="action" value="insert">
                    <!--<a href="#googledoc">Google doc?</a>--> 
                    <input type="checkbox" name="appendGoogleDoc" id="appendGoogleDocBox">
                    <label for="appendGoogleDocBox">Hide GoogleDoc default frame <a href="#googledoc"><img src="images/wen16.svg" alt="help"/></a></label>
                    <button type="submit" class="btn btn-outline-success">Add</button>
                </form>



                <%--Generate request content: "show" --%>
                <form class="form" action="MyRedirectsEmbedded" target="MyRedirectsEmbedded" id='showAgentFile'>
                    <!--<input type="text" class="form-control" placeholder="Friendly Name">
                    <input placeholder="Please enter the destination website URL" required type="url" class="form-control">-->
                    <button type="submit" class="btn btn-outline-primary">Show</button>
                    <input type="hidden" value="select" name="action">
                    <!--<input type="hidden" value="name.desc" name="sort">-->
                    <input type="hidden" class="offset-input" name="offset">

                </form>
                
                <script>
                    var offset = -1 * (new Date().getTimezoneOffset());
                    var docs = document.getElementsByClassName('offset-input');
                    for (var i = 0, max = docs.length; i < max; i++) {
                        docs[i].value = offset;
                    }
                </script>

                <iframe name="MyRedirectsEmbedded" style="width: 100%; height: 600px;" frameborder="0">
                    Your browser does not support iframe!
                </iframe>
                <script>
                    function triggerloaded()
                    {
                        //alert('i');
                        document.getElementById('showAgentFile').submit();
                    }
                    window.addEventListener("loaded", triggerloaded());
                </script>
            </div>

            <div id="redirHelp" class="overlay">
                <div class="popup">
                    <h2>What is redirect link?</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        This is a place where you can generate a permanent link,
                        and use that link without warring about the destination website url being changed.
                        <br>Because once you submit the permanent url you generated, it self stays,
                        but you may change the address it redirects to anytime. <br>
                        <strong>Perma.cc</strong>, who provides similar service, cost $25 for unlimited subscription.
                        <br> You may easily generate unlimited permanent link in our site.
                    </div>
                </div>
            </div>
            <div id="googledoc" class="overlay">
                <div class="popup" style="width: 44%;">
                    <h2>Redirecting Google Doc?</h2>
                    <a class="close" href="#">&times;</a>
                    <div class="content">
                        If you would like the default border of google doc to be 
                        hidden, 
                        please append <code>?embedded=true</code> at the end of your 
                        google doc URL!<br>
                        Example: Original url is https://docs.google.com/document/d/e/XXXXXXX/pub<br>
                        You may write it as: https://docs.google.com/document/d/e/XXXXXXX/pub<strong>?embedded=true</strong>
                        <br>Note: if you check the <strong><input type="checkbox" /> "hide google doc default frame"</strong>, this code will be automatically added for you.
                    </div>
                </div>
            </div>

        </c:if>





        <br>
        <br>
        <br>
        <footer>
            <center style="background-color: pink; ">This page is open for internal testers only.</center>
        </footer>

    </body>
</html>
