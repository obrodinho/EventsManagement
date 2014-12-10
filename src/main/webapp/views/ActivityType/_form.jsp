<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : rgcs
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activities Type</h2>
</div>    
<div>
    ${message}

</div>
<form:form method="POST" commandName="activityType" action="${pageContext.request.contextPath}/ActivityType/${empty activityTypeID ? action : action.concat('/').concat(activityTypeID)}">
    <input type="hidden" name="activityTypeID" value="${activityTypeID}" >
    <table>
        <tbody>
            <tr>
                <td>Short name:</td>
                <td><form:input path="shortname" /></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="${empty activityTypeID ? "Add" : "Save"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}">Home page</a></p>
<%@include file="../../templates/footer.jspf" %>

