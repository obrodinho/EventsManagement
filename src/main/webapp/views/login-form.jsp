<%-- 
    Document   : login
    Created on : Nov 28, 2014, 1:14:37 PM
    Author     : Murilo
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
        <h1>Create and Update users on this page</h1>
        <div>
            ${message}
        </div>
        <form:form method="POST" commandName="login" action="${pageContext.request.contextPath}/doLogin">
            <table>
                <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><form:input path="username" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><form:password path="password" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Entrar" /></td>
                    </tr>
                </tbody>
            </table>
        </form:form>

        <p><a href="${pageContext.request.contextPath}">Home page</a></p>
    </body>
</html>
