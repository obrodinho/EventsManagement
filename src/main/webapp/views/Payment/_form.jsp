<%-- 
    Document   : _form
    Created on : Nov 15, 2014, 4:35:08 PM
    Author     : rss
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Payment</h2>
</div>
<div>
    ${message}
</div>
<form:form method="POST" commandName="payment" action="${pageContext.request.contextPath}/Payment/${empty paymentID ? action : action.concat('/').concat(paymentID)}">
    <input type="hidden" name="paymentID" value="${paymentID}" >
    <table>
        <tbody>
            <tr>
                <td>Status:</td>
                <td><form:input path="status" /></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><form:input path="type" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="${empty paymentID ? "Add" : "Save"}" /></td>
            </tr>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>
