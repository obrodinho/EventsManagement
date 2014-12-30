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
                <th width="">Pending Subscriptions</th>
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
            <c:forEach var="classesSubscriptionPaymentPending" items="${classesSubscriptionPaymentPending}">
                <tr>
                    <td>${classesSubscriptionPaymentPending.classes.activity.title}</td>
                    <td>${classesSubscriptionPaymentPending.classes.activity.description}</td>
                    <td>${classesSubscriptionPaymentPending.classes.activity.type.title}</td>
                    <td>${classesSubscriptionPaymentPending.classes.activity.workload}</td>
                    <td>${classesSubscriptionPaymentPending.classes.activity.start}</td>
                    <td>${classesSubscriptionPaymentPending.classes.activity.end}</td>
                    <td><a href="<c:url value="/Activity/confirmPayamentSubscription/${classesSubscriptionPaymentPending.payment.id}"/>">Pay</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table table-bordered table-condensed table-hover">
        <thead>
            <tr>
                <th width="">Paid Subscriptions</th>
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
            <c:forEach var="classesSubscriptionPaymentPaid" items="${classesSubscriptionPaymentPaid}">
                <tr>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.title}</td>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.description}</td>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.type.title}</td>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.workload}</td>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.start}</td>
                    <td>${classesSubscriptionPaymentPaid.classes.activity.end}</td>
                    <td>Paid</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form:form>

<%@include file="../../templates/footer.jspf" %>

