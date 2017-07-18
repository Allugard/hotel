<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="custom" uri="http://allugard.ua" %>


<html>
<head>
    <%@include file="/view/head.jsp" %>
    <title><fmt:message key="login.page"/> </title>
</head>

<body>
<%@include file="/view/header.jsp" %>

<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<c:forEach items="${bookings}" var="item">


    <table border="1">
        <tr>
            <th><fmt:message key="bookings.dateFrom"/></th>
            <th><fmt:message key="bookings.dateTo"/></th>
            <th><fmt:message key="bookings.persons"/></th>
            <th><fmt:message key="bookings.apartments.type"/></th>
            <th><fmt:message key="bookings.status"/></th>
        </tr>
        <tr>
            <td><custom:formatDate value="${item.dateFrom}" pattern="dd/MM/yyyy" /></td>
            <td><custom:formatDate value="${item.dateTo}" pattern="dd/MM/yyyy" /></td>
            <td><c:out value="${item.persons}"/></td>
            <td><c:out value="${item.apartmentsType}"/></td>
            <td><c:out value="${item.status}"/></td>
            <td>
                <form action="/profile/bookings/delete" method="post">
                    <input type="hidden" name="command" value="deleteBooking"/>
                    <input type="hidden" name="delete" value=${item.id} />
                    <button type="submit"><fmt:message key="booking.delete"/></button>
                </form>
            </td>
        </tr>
    </table>




</c:forEach>


</body></html>