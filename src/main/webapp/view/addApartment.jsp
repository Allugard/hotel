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
<form name="addBookingForm" method="POST" action="/profile/addApartment/add" autocomplete="on">
    <input type="hidden" name="command" value="addApartment"/>

    <div>
        <span><fmt:message key="apartments.capacity"/><label>*</label></span>
        <input type="number" value="${capacity}" name="capacity" required="required">
    </div>

    <div>
        <span><fmt:message key="apartments.price"/><label>*</label></span>
        <input type="number" value="${price}" name="price" required="required">
    </div>

    <div>
        <span><fmt:message key="apartments.number"/><label>*</label></span>
        <input type="text" value="${number}" name="number" required="required">
    </div>

    <div>
    <span><fmt:message key="apartments.apartments.type"/><label>*</label></span>

        <select name="apartmentsType" required=required>
            <option value="Standart" ${apartmentsType == 'standart' ? 'selected' : ''}>Standart</option>
            <option value="Suite" ${apartmentsType == 'suite' ? 'selected' : ''}>Suite</option>
        </select>
    </div>

    <input type="submit" value=<fmt:message key="bookings.create"/>>
</form>
<c:forEach items="${errors}" var="item">
    <fmt:message key="${item}"/>
    <br>
</c:forEach>


</body>
</html>