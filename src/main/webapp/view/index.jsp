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

    <h1><fmt:message key="greetings"/></h1>

    <c:if test="${not empty requestScope.completedRegistration}">
        <fmt:message key="${completedRegistration}"/>
    </c:if>

</body>
</html>