<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : rgcs
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Register Yourself!</h2>
</div>
<div>
    ${message}
</div>

<div class="row">
<div class="col-md-4 col-md-offset-4">
    
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/signup">
    <input type="hidden" name="userID" value="${userID}" >
    <div class="form-group">
                <label>First name:</label>
                <div ><form:input path="firstname" cssClass="form-control" /></div>
            </div>
            <div class="form-group">
                <label>Last name:</label>
                <div ><form:input path="lastname" cssClass="form-control" /></div>
            </div>
            <div class="form-group">
                <label>Username:</label>
                <div ><form:input path="username" cssClass="form-control" /></div>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <div ><form:input path="password" cssClass="form-control" /></div>
            </div>
            
            <input type="submit" value="Register" class="btn btn-default btn-embossed btn-primary" />
            
</form:form>
    </div>
</div>
    <div class="row">
<c:if test="${userID == null}">        
    <p>
        <a href="${pageContext.request.contextPath}/login">Already Registered?</a>
    </p>
</c:if>
    </div>

    <%@include file="../../templates/footer.jspf" %>
