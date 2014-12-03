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
                <td>COLUMN</td>
                <!--                        <td><//form:input path="ObjectAttribute" /></td>-->
            </tr>
            <tr>
                <td><input type="submit" value="${empty eventID ? "Add" : "Save"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

