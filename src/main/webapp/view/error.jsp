<%--
  Created by IntelliJ IDEA.
  User: allugard
  Date: 16.07.17
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="head.jsp" %>
    <title>Title</title>

</head>
<body>
<%@include file="header.jsp" %>

Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}

</body>
</html>
