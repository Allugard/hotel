<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="head.jsp" %>
    <title><fmt:message key="main.page"/></title>

</head>

<body>
<%@include file="header.jsp" %>


<c:if test="${user.userAuthentication.role.toString() == 'client'}">
    <%--
                <a href="/addBooking"><fmt:message key="addBoking.page"/></a>
    --%>
    <div class="col-md-2 menu">
        <a href="/profile/addBooking"><fmt:message key="addBoking.page"/></a>
    </div>


    <%--<form action="/profile/addBooking" method="post">
        <input type="hidden" name="command" value="redirect"/>
        <input type="hidden" name="page" value="addBooking"/>
        <button type="submit"><fmt:message key="addBoking.page"/></button>
    </form>--%>

    <%--
                <a href="/viewBookings"><fmt:message key="bookings.page"/></a>
    --%>
    <div class="col-md-2">
    </div>

    <div class="col-md-2 menu">
        <a href="/profile/bookings"><fmt:message key="bookings.page"/></a>
    </div>

    <%--<form action="/profile/bookings" method="post">
        <input type="hidden" name="command" value="bookingsByUser"/>
        <button type="submit"><fmt:message key="bookings.page"/></button>
    </form>--%>

</c:if>

<c:if test="${user.userAuthentication.role.toString() == 'admin'}">
    <%--
                <a href="/addApartment"><fmt:message key="addApartment.page"/></a>
    --%>
    <div class="col-md-2 menu">
        <a href="/profile/addApartment"><fmt:message key="addApartment.page"/></a>
    </div>

    <%--<form action="/profile/addApartment" method="post">
        <input type="hidden" name="command" value="redirect"/>
        <input type="hidden" name="page" value="addApartment"/>
        <button type="submit"><fmt:message key="addApartment.page"/></button>
    </form>--%>
    <div class="col-md-2">
    </div>

    <div class="col-md-2 menu">
        <a href="/profile/apartments"><fmt:message key="apartments.page"/></a>
    </div>
    <%--<form action="/profile/apartments" method="post">
        <input type="hidden" name="command" value="allApartments"/>
        <button type="submit"><fmt:message key="apartments.page"/></button>
    </form>--%>

    <div class="col-md-2">
    </div>

    <div class="col-md-2 menu">
        <a href="/profile/processedBookings"><fmt:message key="booking.processed.page"/></a>
    </div>

    <%--<form action="/profile/processedBookings" method="post">
        <input type="hidden" name="command" value="processedBookings"/>
        <button type="submit"><fmt:message key="booking.processed.page"/></button>
    </form>--%>
    <%--
                <a href="/allApartments"><fmt:message key="allApartments.page"/></a>
    --%>
</c:if>


<%@include file="/view/footer.jsp" %>

</body>
</html>