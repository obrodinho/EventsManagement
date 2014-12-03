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
    <h2>Class</h2>
</div>        <div>
            ${message}
        </div>
        <form:form method="POST" commandName="classes" action="${pageContext.request.contextPath}/Classes/${empty classesID ? action.concat('/').concat(activityID) : action.concat('/').concat(classesID)}">
            <input type="hidden" name="classesID" value="${classesID}" >
            <pre>${classes}</pre>
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
                        <td><input type="submit" value="${empty classesID ? "Add" : "Save"}" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>

        
<%@include file="../../templates/footer.jspf" %>

