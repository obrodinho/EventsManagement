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
        <h1>Create and Update activitys on this page</h1>
        <div>
            ${message}
        </div>
        <form:form method="POST" commandName="activity" action="${pageContext.request.contextPath}/Activity/${empty activityID ? action : action.concat('/').concat(activityID)}">
            <input type="hidden" name="activityID" value="${activityID}" >
            <table>
                <tbody>
                    <tr>
                        <td>COLUMN</td>
<!--                        <td><//form:input path="ObjectAttribute" /></td>-->
                    </tr>
                    <tr>
                        <td><input type="submit" value="${empty activityID ? "Add" : "Save"}" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>

        <p><a href="${pageContext.request.contextPath}">Home page</a></p>
    </body>
</html>
