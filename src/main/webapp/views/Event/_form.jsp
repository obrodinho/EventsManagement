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
    <h2>Event</h2>
</div>
<div>
    ${message}
</div>
<form:form method="POST" commandName="event" action="${pageContext.request.contextPath}/Event/${empty eventID ? action : action.concat('/').concat(eventID)}">
    <input type="hidden" name="eventID" value="${eventID}" >
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
                <td>Start:</td>
                <td><form:input path="dateStart" /> Ex: dd-mm-yyyy hh:mm:ss</td>
            </tr>
            <tr>
                <td>End:</td>
                <td><form:input path="dateEnd" /> Ex: dd-mm-yyyy hh:mm:ss</td>
            </tr>
            <tr>
                <td>Owner:</td>
                <td>
                    <form:select 
                        path="userID" 
                        name="user"
                        cssClass="selectpicker" 
                        style="height: 35px; width: 197px"
                        items="${users}" 
                        itemLabel="username" 
                        itemValue="id" />
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="${empty eventID ? "Add" : "Save"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

