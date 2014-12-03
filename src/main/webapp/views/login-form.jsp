<%-- 
    Document   : login
    Created on : Nov 28, 2014, 1:14:37 PM
    Author     : Murilo
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../templates/header.jspf" %>
<div class="page-header">
    <h2>Please, Login!</h2>
</div>
<div class="row">
    <div>
        ${message}
    </div>
</div>
<div class="row">
    <div class="well col-md-4 col-md-offset-4">
        <form:form method="POST" commandName="login" action="${pageContext.request.contextPath}/doLogin">
            <div class="form-group">              
                <label>Username:</label>
                <form:input path="username" id="username" class="form-control" />
            </div>
            <div class="form-group">              
                <label>Password:</label>
                <form:input path="password" id="password" class="form-control" />
            </div>
            <button type="submit" class="btn btn-default btn-embossed btn-large btn-primary"><span class="fui-check-circle"></span> Login</button>
        </form:form>
    </div>
</div>
<%@include file="../templates/footer.jspf" %>
