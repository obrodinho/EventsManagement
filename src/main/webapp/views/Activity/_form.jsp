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
    <h2>Activity</h2>
</div>    
<div>
    ${message}

</div>
<form:form method="POST" commandName="activity" action="${pageContext.request.contextPath}/Activity/${empty activityID ? action : action.concat('/').concat(activityID)}">
    <input type="hidden" name="userID" value="${userID}" >
    <table>
        <tbody>
            <tr>
                <td>Title:</td>
                <td><form:input path="title" /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description" /></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" /></td>
            </tr>
            <tr>
                <td>Workload:</td>
                <td><form:input path="workload" /></td>
            </tr>
            <tr>
                <td>Start:</td>
                <td><form:input path="dateStart" /> Ex: dd-mm-yyyy hh:mm:ss</td>
            </tr>
            <tr>
                <td>End:</td>
                <td><form:input path="dateEnd" /> Ex: dd-mm-yyyy hh:mm:ss</td>
            </tr>
            <tr>
                <td><input type="submit" value="${empty activityID ? "Add" : "Save"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}">Home page</a></p>
<%@include file="../../templates/footer.jspf" %>

