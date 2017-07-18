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
<form action="/profile/processedBookings/update" method="post">

    <table border="1">
        <tr>
            <th><fmt:message key="bookings.dateFrom"/></th>
            <th><fmt:message key="bookings.dateTo"/></th>
            <th><fmt:message key="bookings.persons"/></th>
            <th><fmt:message key="bookings.apartments.type"/></th>
        </tr>

        <c:forEach items="${bookings}" var="item" varStatus="status">
            <input type="hidden" name="dateFrom" value="${item.dateFrom}"/>
            <input type="hidden" name="dateTo" value="${item.dateTo}"/>
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="hidden" name="persons" value="${item.persons}"/>
            <input type="hidden" name="apartmentsType" value="${item.apartmentsType}"/>


            <tr>
            <td><custom:formatDate value="${item.dateFrom}" pattern="dd/MM/yyyy" /></td>
            <td><custom:formatDate value="${item.dateTo}" pattern="dd/MM/yyyy" /></td>
            <td><c:out value="${item.persons}"/></td>
            <td><c:out value="${item.apartmentsType}"/></td>
            <td>
                    <select name="status">
                        <option value="rejected">reject</option>
                        <c:forEach items="${freeNumbersForBooking[status.index]}" var="apartmentItem">
                            <option value="${apartmentItem.id}"><c:out value="${apartmentItem.number}"/></option>
                        </c:forEach>

                    </select>
                   <%-- <c:if test="${item.id != null}">
                        <input type="hidden" name="update" value="ab1" />
                    </c:if>
                    <c:if test="${item.id == null}">
                        <input type="hidden" name="update" value="Rejected" />
                    </c:if>
                    <c:out value="${item.id}"/>--%>
<%--
                <form action="/profile/processedBookings/update" method="post">
                    <input type="hidden" name="command" value="updateBooking"/>
                    <input type="hidden" name="update" value="${item.id}" />
                    <button type="submit"><fmt:message key="booking.update"/></button>
                </form>
--%>
            </td>
        </tr>

        </c:forEach>
    </table>



    <%--
             <input type="hidden" name="update" value="${item.id}" />
    --%>
    <button type="submit"><fmt:message key="booking.update"/></button>
</form>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="/profile/processedBookings?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/profile/processedBookings?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="/profile/processedBookings?page=${currentPage + 1}">Next</a></td>
</c:if>



</body></html>