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

<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
    <input type="hidden" name="command" value="login"/>
    <div>
        <span><fmt:message key="email"/><label>*</label></span>
        <input type="text" name="login" required="required">
    </div>
    <div>
        <span><fmt:message key="password"/><label>*</label></span>
        <input name="password" required="required" type="password">
    </div>

    <input type="submit" value=<fmt:message key="sign.in"/>>
</form>


</body></html>