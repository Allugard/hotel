<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="custom" uri="http://allugard.ua" %>


<html>
<head>
    <%@include file="/view/head.jsp" %>
    <title><fmt:message key="login.page"/></title>
</head>

<body>
<%@include file="/view/header.jsp" %>

<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<c:forEach items="${bookings}" var="item">

    <table class="register mytable" align="center">
        <tr>
            <th>
                <div class="col-md-2">
                    <fmt:message key="bookings.dateFrom"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="bookings.dateTo"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="bookings.persons"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="bookings.apartments.type"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="bookings.status"/>
                </div>
            </th>

            <c:if test="${item.status.toString() == 'confirmed'}">
                <th>
                    <div class="col-md-2">
                        <fmt:message key="bill.price"/>
                    </div>
                </th>
            </c:if>
        </tr>
        <tr>
            <td>
                <div class="col-md-2">
                    <custom:formatDate value="${item.dateFrom}" pattern="dd/MM/yyyy"/>
                </div>
            </td>
            <th>
                <div class="col-md-2">
                    <custom:formatDate value="${item.dateTo}" pattern="dd/MM/yyyy"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <c:out value="${item.persons}"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <c:out value="${item.apartmentsType}"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <c:out value="${item.status}"/>
                </div>
            </th>

            <c:if test="${item.status.toString() == 'confirmed'}">
                <td>
                    <div class="col-md-2">
                        <c:out value="${item.bill.price}"/>
                    </div>
                </td>
            </c:if>
            <td>
                <div class="col-md-2">
                    <form action="/profile/bookings/delete" method="post">
                        <input type="hidden" name="command" value="deleteBooking"/>
                        <input type="hidden" name="delete" value=${item.id}/>
                        <button class="submit-button" type="submit"><fmt:message key="booking.delete"/></button>
                    </form>
                </div>
            </td>
        </tr>
    </table>


</c:forEach>


</body>
</html>