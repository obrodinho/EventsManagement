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
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/signup">
    <input type="hidden" name="userID" value="${userID}" >
    <table>
        <tbody>
            <tr>
                <td>First name:</td>
                <td><form:input path="firstname" /></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><form:input path="lastname" /></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register" /></td>
            </tr>
        </tbody>
    </table>
</form:form>
<c:if test="${userID == null}">        
    <p>
        <a href="${pageContext.request.contextPath}/login">Already Registered?</a>
    </p>
</c:if>


    <%@include file="../../templates/footer.jspf" %>