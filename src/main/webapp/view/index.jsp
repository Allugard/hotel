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

<div class="row ">
    <p class="welcome-title"><fmt:message key="greetings"/></p>
</div>

<%--<c:if test="${not empty requestScope.completedRegistration}">
    <p class="my-title"> <fmt:message key="${completedRegistration}"/></p>
</c:if>--%>

<%@include file="footer.jsp" %>
</body>
</html>