<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : rss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>

<div class="page-header">
    <h2>Payments</h2>
</div>
<div>
    ${message}
</div>
<table class="table table-bordered table-condensed table-hover">
    <thead>
        <tr>
            <th width="">ID</th>
            <th width="">Status</th>
            <th width="">Type</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="payment" items="${payments}">
            <tr>
                <td>${payment.id}</td>
                <td>${payment.status}</td>
                <td>${payment.type}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/Payment/edit/${payment.id}" class="btn btn-primary btn-embossed">Edit</a> | 
                    <a href="${pageContext.request.contextPath}/Payment/delete/${payment.id}" class="btn btn-default btn-danger btn-embossed">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="../../templates/footer.jspf" %>
