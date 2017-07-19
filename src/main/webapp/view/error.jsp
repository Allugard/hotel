<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>

<c:forEach items="${errors}" var="item">
    <p class="text-danger"><fmt:message key="error.in.server"/></p>
    <br>
</c:forEach>

<%@include file="footer.jsp" %>
</body>
</html>
