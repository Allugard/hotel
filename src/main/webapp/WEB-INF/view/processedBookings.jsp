<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="custom" uri="http://allugard.ua" %>


<html>
<head>
    <%@include file="head.jsp" %>
</head>

<body>
<%@include file="header.jsp" %>

<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<form action="/profile/processedBookings/update" method="post">

    <table class="register mytable">
        <tr>

            <th>
                <div class="col-md-2">
                    <fmt:message key="registration.frist.name"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="registration.last.name"/>
                </div>
            </th>
            <th>
                <div class="col-md-2">
                    <fmt:message key="registration.phone"/>
                </div>

            </th>
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
                    <fmt:message key="apartments.number"/>
                </div>
            </th>
        </tr>

        <c:forEach items="${bookings}" var="item" varStatus="status">
            <input type="hidden" name="dateFrom" value="${item.dateFrom}"/>
            <input type="hidden" name="dateTo" value="${item.dateTo}"/>
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="hidden" name="persons" value="${item.persons}"/>
            <input type="hidden" name="apartmentsType" value="${item.apartmentsType}"/>
           <c:if test="${item.apartment!=null}">
               <input type="hidden" name="apartmentsId" value="${item.apartment.id}"/>
               <input type="hidden" name="apartmentsPrice" value="${item.apartment.price}"/>
           </c:if>



            <tr>
                <td>
                    <div class="col-md-2">
                        <c:out value="${item.user.firstName}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <c:out value="${item.user.lastName}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <c:out value="${item.user.phone}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <custom:formatDate value="${item.dateFrom}" pattern="dd/MM/yyyy"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <custom:formatDate value="${item.dateTo}" pattern="dd/MM/yyyy"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <c:out value="${item.persons}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <fmt:message key="${item.apartmentsType.toString()}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <c:if test="${item.apartment != null}">
                            <c:out value="${item.apartment.number}"/>
                        </c:if>
                        <c:if test="${item.apartment == null}">
                            <fmt:message key="apartments.occupied"/>
                        </c:if>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <select name="status">
                            <option value=" " selected></option>
                            <option value="rejected"><fmt:message key="reject"/></option>
                            <c:if test="${item.apartment != null}">
                                <option value="confirmed"><fmt:message key="confirm"/></option>
                            </c:if>

                        </select>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button class="submit-button" type="submit"><fmt:message key="booking.update"/></button>
</form>

<c:forEach items="${errors}" var="item">
    <p class="text-danger"><fmt:message key="${item}"/></p>
    <br>
</c:forEach>


<%--
         <input type="hidden" name="update" value="${item.id}" />
--%>

<c:if test="${currentPage != 1}">
    <td><a href="/profile/processedBookings?page=${currentPage - 1}">Previous</a></td>
</c:if>

<table>
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td class="my-pagination">${i}</td>
                </c:when>
                <c:otherwise>
                    <td class="my-pagination"><a href="/profile/processedBookings?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<c:if test="${currentPage lt noOfPages}">
    <td><a href="/profile/processedBookings?page=${currentPage + 1}">Next</a></td>
</c:if>

<%@include file="footer.jsp" %>

</body>
</html>