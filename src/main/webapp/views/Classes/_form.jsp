<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : rgcs
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create and Update classes on this page</h1>
        <div>
            ${message}
        </div>
        <form:form method="POST" commandName="classes" action="${pageContext.request.contextPath}/Classes/${empty classesID ? action.concat('/').concat(activityID) : action.concat('/').concat(classesID)}">
            <input type="hidden" name="userID" value="${userID}" >
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

        <p><a href="${pageContext.request.contextPath}">Home page</a></p>
    </body>
</html>
