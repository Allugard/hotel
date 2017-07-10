<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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
<form name="addBookingForm" method="POST" action="addBooking" autocomplete="on">
    <input type="hidden" name="command" value="addBooking"/>
    <div>
        <span>
            <fmt:message key="bookings.dateFrom"/><label>*</label>
            <input type="date" name="dateFrom" required="required">
        </span>
    </div>
    <div>
        <span>
            <fmt:message key="bookings.dateTo"/><label>*</label>
            <input type="date" name="dateTo" required="required">
        </span>

    </div>
    <div>
        <span><fmt:message key="bookings.persons"/><label>*</label></span>
        <input type="number" name="persons" required="required">
    </div>
    <div>
    <span><fmt:message key="bookings.apartments.type"/><label>*</label></span>

        <select name="apartmentsType" required=required>
            <option value="Standart">Standart</option>
            <option value="Suite">Suite</option>
        </select>
    </div>

    <input type="submit" value=<fmt:message key="bookings.create"/>>
</form>


</body>
</html>