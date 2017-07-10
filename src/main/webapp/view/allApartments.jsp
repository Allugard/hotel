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
<c:forEach items="${apartments}" var="item">


    <table border="1">
        <tr>
            <th><fmt:message key="apartments.capacity"/></th>
            <th><fmt:message key="apartments.price"/></th>
            <th><fmt:message key="apartments.number"/></th>
            <th><fmt:message key="apartments.apartments.type"/></th>
        </tr>
        <tr>
            <td><c:out value="${item.capacity}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.apartmentsType}"/></td>
            <td><a href="/deleteApartment?delete=${item.id}"/>Delete</td>
        </tr>
    </table>




</c:forEach>


</body></html>