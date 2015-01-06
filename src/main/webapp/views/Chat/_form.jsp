<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : kallenon
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Chat</h2>
</div>
<div>
    ${message}
</div>
<form:form method="POST" commandName="chat" action="${pageContext.request.contextPath}/Chat/${empty chatID ? action : action.concat('/').concat(chatID)}">
    <input type="hidden" name="chatID" value="${chatID}" >
    <table>
        <tbody>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Subject:</td>
                <td><form:input path="subject" /></td>
            </tr>
            <tr>
                <td>Doudt:</td>
                <td><form:input path="doudt" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="${empty chatID ? "Enter" : "Cancel"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

