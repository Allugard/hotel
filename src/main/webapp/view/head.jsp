<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<%--c:set var="language" scope="session"
       value="${empty sessionScope.locale ? 'en_US' : sessionScope.locale}" />--%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang" />
