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

<div class="row">
<div class="col-md-4 col-md-offset-4">

    <form:form method="POST" commandName="payment" action="${pageContext.request.contextPath}/Payment/${empty paymentID ? action : action.concat('/').concat(paymentID)}">
    <input type="hidden" name="paymentID" value="${paymentID}" >
    <div class="form-group">
                <label>Status:</label>
                <div ><form:input path="status" cssClass="form-control"/></div>
            </div>
            <div class="form-group">
                <label>Type:</label>
                <div ><form:input path="type" cssClass="form-control"/></div>
            </div>
            <input type="submit" value="${empty paymentID ? "Add" : "Save"}" class="btn btn-default btn-embossed btn-primary"/>
            
</form:form>
    </div>
</div>
<%@include file="../../templates/footer.jspf" %>
