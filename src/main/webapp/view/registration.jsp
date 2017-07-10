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
        <input type="text" name="login" required="required">
    </div>
    <div>
        <span><fmt:message key="password"/><label>*</label></span>
        <input name="password" required="required" type="password">
    </div>


    <div>
        <span><fmt:message key="registration.frist.name"/><label>*</label></span>
        <input type="text" name="firstName" required="required">
    </div>
    <div>
        <span><fmt:message key="registration.last.name"/><label>*</label></span>
        <input type="text" name="lastName" required="required">
    </div>

    <div>
        <span><fmt:message key="registration.phone"/><label>*</label></span>
        <input type="text" name="phone" required="required">
    </div>

    <input type="submit" value=<fmt:message key="sign.up"/>>
</form>


</body></html>