<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="header">
    <div id="sitename">
        <br></br>
        <h1 id="logo"><a href="${pageContext.request.contextPath}/">GreekTravel</a></h1>
    </div>
<!--    <div id="shoutout">
        <img src="$ {pageContext.request.contextPath}/images/logo.png" alt="" width="168" height="126">
    </div>-->


    <c:choose>
        <c:when test="${empty sessionuser}">
            <jsp:include page="/WEB-INF/fragments/guestactions.jsp"/>
        </c:when>    
        <c:otherwise>
            <jsp:include page="/WEB-INF/fragments/useractions.jsp"/>
        </c:otherwise>
    </c:choose>
</div>