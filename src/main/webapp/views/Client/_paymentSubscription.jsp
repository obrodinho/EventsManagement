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
        <c:if test="${not empty nonPaidActivities}">
        <thead>
            <tr>
                <th colspan="7">Pending Subscriptions</th>
            </tr>
        </thead>
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
            <c:forEach var="activity" items="${nonPaidActivities}">
                <tr>
                    <td>${activity.title}</td>
                    <td>${activity.description}</td>
                    <td>${activity.type.title}</td>
                    <td>${activity.workload}</td>
                    <td>${activity.start}</td>
                    <td>${activity.end}</td>
                    <td><a class="btn btn-primary btn-embossed" href="<c:url value="/Activity/confirmPaymentSubscription/${activitiesPaymentMap.get(activity).id}"/>">Pay</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </c:if>
        <c:if test="${empty nonPaidActivities or nonPaidActivities == null}">
            <tr>
                <th width="">Pending Subscriptions</th>
            </tr>
            <td align="center"><b>There is no pending payment! Did you made any subscription?</b></td>
        </c:if>
    </table>
    <table class="table table-bordered table-condensed table-hover">
        <c:if test="${not empty paidActivities}">
        <thead>
            <tr>
                <th colspan="7">Paid Subscriptions</th>
            </tr>
        </thead>
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
            <c:forEach var="activity" items="${paidActivities}">
                <tr>
                    <td>${activity.title}</td>
                    <td>${activity.description}</td>
                    <td>${activity.type.title}</td>
                    <td>${activity.workload}</td>
                    <td>${activity.start}</td>
                    <td>${activity.end}</td>
                    <td>Paid</td>
                </tr>
            </c:forEach>
        </tbody>
        </c:if>
        <c:if test="${empty paidActivities or paidActivities == null}">
            <tr>
                <th width="">Paid Subscriptions</th>
            </tr>
            <td align="center"><b>No payment was made, or you don't have any subscription!</b></td>
        </c:if>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

