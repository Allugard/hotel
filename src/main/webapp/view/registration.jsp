<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<html>
<head>
    <%@include file="/view/head.jsp" %>
    <title><fmt:message key="registration.page"/> </title>
</head>

<body>
<%@include file="/view/header.jsp" %>

<form method="POST" action="/signup" autocomplete="on">
    <input type="hidden" name="command" value="signup"/>
    <div>
        <span><fmt:message key="email"/><label>*</label></span>
        <input type="text" name="login" value="${login}" required="required">
    </div>
    <div>
        <span><fmt:message key="password"/><label>*</label></span>
        <input type="password" name="password"  required="required" >
    </div>


    <div>
        <span><fmt:message key="registration.frist.name"/><label>*</label></span>
        <input type="text" name="firstName" value="${firstName}" required="required">
    </div>
    <div>
        <span><fmt:message key="registration.last.name"/><label>*</label></span>
        <input type="text" name="lastName" value="${lastName}" required="required">
    </div>

    <div>
        <span><fmt:message key="registration.phone"/><label>*</label></span>
        <input type="text" name="phone" value="${phone}" required="required">
    </div>

    <input type="submit" value=<fmt:message key="sign.up"/>>
</form>

<c:forEach items="${errors}" var="item">
    <fmt:message key="${item}"/>
    <br>
</c:forEach>


</body>
</html>