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
        <c:forEach items="${apartments}" var="item">

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
                        <c:out value="${item.apartmentsType}"/>
                    </div>
                </td>
                <td>
                    <div class="col-md-2">
                        <form action="/profile/apartments/delete" method="post">
                            <button class="submit-button" type="submit"><fmt:message key="apartments.delete"/></button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>