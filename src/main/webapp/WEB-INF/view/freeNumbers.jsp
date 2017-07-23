<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="http://allugard.ua" %>


<html>
<head>
    <%@include file="/view/head.jsp" %>
</head>

<body>
<%@include file="/view/header.jsp" %>
<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<form class="register" name="addBookingForm" method="POST" action="/apartments/find" autocomplete="on">
    <input type="hidden" name="page" value="freeNumbers">
    <table align="center">
        <tr>
            <td><fmt:message key="bookings.dateFrom"/>*</td>
            <td><input type="date" value="${dateFrom}" name="dateFrom" required="required"></td>
        </tr>

        <tr>
            <td><fmt:message key="bookings.dateTo"/>*</td>
            <td><input type="date" value="${dateTo}" name="dateTo" required="required"></td>
        </tr>

        <tr>
            <td><fmt:message key="bookings.persons"/>*</td>
            <td><input type="number" value="${persons}" name="persons" required="required"></td>
        </tr>

        <tr>
            <td><fmt:message key="bookings.apartments.type"/>*</td>
            <td>
                <select name="apartmentsType" required=required>
                    <option value="Standart" ${apartmentsType == 'standart' ? 'selected' : ''}> <fmt:message key="standart"></fmt:message></option>
                    <option value="Suite" ${apartmentsType == 'suite' ? 'selected' : ''}><fmt:message key="suite"></fmt:message></option>
                </select>
            </td>
        </tr>

    </table>

    <input type="submit" class="submit-button" value=<fmt:message key="bookings.find"/>>
</form>
    <div class="register">
        <table align="center">
            <tr>
                <th>
                    <div class="col-md-2">
                        <fmt:message key="apartments.capacity"/>
                    </div>
                </th>
                <th>
                    <div class="col-md-2">
                        <fmt:message key="apartments.price"/>
                    </div>
                </th>
                <th>
                    <div class="col-md-2">
                        <fmt:message key="apartments.number"></fmt:message>
                    </div>
                </th>
                <th>>
                    <div class="col-md-2">
                        <fmt:message key="apartments.apartments.type"/>
                    </div>
                </th>
            </tr>
            <c:forEach items="${freeNumbersForBooking}" var="item">
                <tr>
                    <td>
                        <div class="col-md-2">
                            <c:out value="${item.capacity}"/>
                        </div>
                    </td>
                    <td>
                        <div class="col-md-2">
                            <c:out value="${item.price}"/>
                        </div>
                    </td>
                    <td>
                        <div class="col-md-2">
                            <c:out value="${item.number}"/>
                        </div>
                    </td>
                    <td>
                        <div class="col-md-2">
                            <fmt:message key="${item.apartmentsType.toString()}"></fmt:message>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


<c:forEach items="${errors}" var="item">
    <p class="text-danger"><fmt:message key="${item}"/></p>
    <br>
</c:forEach>


<%@include file="/view/footer.jsp" %>

</body>
</html>