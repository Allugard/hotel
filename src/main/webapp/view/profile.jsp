<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<html>
<head>
    <%@include file="head.jsp" %>
    <title><fmt:message key="main.page"/> </title>

</head>

<body>
    <%@include file="header.jsp" %>

        <c:if test="${user.userAuthentication.role.toString() == 'client'}">
            <a href="/addBooking"><fmt:message key="addBoking.page"/></a>
            <a href="/viewBookings"><fmt:message key="bookings.page"/></a>
        </c:if>

        <c:if test="${user.userAuthentication.role.toString() == 'admin'}">
            <a href="/addApartment"><fmt:message key="addApartment.page"/></a>
            <a href="/allApartments"><fmt:message key="allApartments.page"/></a>
        </c:if>





</body>
</html>