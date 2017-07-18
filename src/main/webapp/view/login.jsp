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

<%
    request.setCharacterEncoding("UTF-8");
%>

ПРИВЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕЕТ
<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<form method="POST" action="/login/signin" autocomplete="on">
    <input type="hidden" name="command" value="signin"/>
    <div>
        <span><fmt:message key="email"/><label>*</label></span>
        <input type="text" name="login" value="${login}" required="required">
    </div>
    <div>
        <span><fmt:message key="password"/><label>*</label></span>
        <input name="password" required="required" type="password">
    </div>

    <input type="submit" value=<fmt:message key="sign.in"/>>
</form>

<c:forEach items="${errors}" var="item">
    <fmt:message key="${item}"/>
    <br>
</c:forEach>

<%--
<c:if test="${not empty requestScope.errorLoginPassMessage}">
    <fmt:message key="${errorLoginPassMessage}"/>
</c:if>
--%>

</body></html>