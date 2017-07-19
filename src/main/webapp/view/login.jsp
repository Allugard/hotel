<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="/view/head.jsp" %>
</head>

<body>
<%@include file="/view/header.jsp" %>
<%--
<form name="loginForm" method="POST" action="login/authorization" autocomplete="on">
--%>


<form class="register" method="POST" action="/login/signin" autocomplete="on">
    <span class="text-primary"><i class="fa fa-envelope-o" aria-hidden="true"></i>*</span>
    <input type="text" name="login" placeholder="example@mail.com" value="${login}" required="required">
    <br>
    <span class="text-primary"><i class="fa fa-unlock-alt" aria-hidden="true"></i>*</span>
    <input name="password" placeholder="&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;&#8226;" required="required"
           type="password">
    <br>
    <input class="submit-button" type="submit" value=<fmt:message key="sign.in"/>>
</form>

<c:forEach items="${errors}" var="item">
    <p class="text-danger"><fmt:message key="${item}"/></p>
    <br>
</c:forEach>

<%--
<c:if test="${not empty requestScope.errorLoginPassMessage}">
    <fmt:message key="${errorLoginPassMessage}"/>
</c:if>
--%>
<%@include file="/view/footer.jsp" %>

</body>
</html>