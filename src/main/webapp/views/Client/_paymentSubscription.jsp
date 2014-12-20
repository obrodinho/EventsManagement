<%-- 
    Document   : _list
    Created on : Nov 15, 2014, 4:35:48 PM
    Author     : kallenon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../templates/header.jspf" %>
<div class="page-header">
    <h2>Activities</h2>
</div>
<div>
    ${message}
</div>
<form:form method="POST" action="${pageContext.request.contextPath}/Activity/payamentSubscription">
    <table class="table table-bordered table-condensed table-hover">
        <thead>
            <tr>
                <th width="">Title</th>
                <th width="">Description</th>
                <th width="">Type</th>
                <th width="">Workload</th>
                <th width="">Start</th>
                <th width="">End</th>
                <th width="">Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="classeSubscription" items="${classesSubscription}">
                <tr>
                    <td>${classeSubscription.classes.activity.title}</td>
                    <td>${classeSubscription.classes.activity.description}</td>
                    <td>${classeSubscription.classes.activity.type.title}</td>
                    <td>${classeSubscription.classes.activity.workload}</td>
                    <td>${classeSubscription.classes.activity.start}</td>
                    <td>${classeSubscription.classes.activity.end}</td>

                    <td>
                        <c:if test="${classesSubscription.payment.status == 'pending'}">
                            <a href="${pageContext.request.contextPath}/Activity/payamentSubscription">Pagar</a>
                        </c:if>
                        <c:if test="${classesSubscription.payment.status == 'paid'}">Pago</c:if>
                        </td>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

