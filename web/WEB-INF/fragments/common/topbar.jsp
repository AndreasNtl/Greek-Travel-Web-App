<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="topbar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/pages?page=home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/pages?page=advanced">Advanced Search</a></li>

        <jsp:include page="/WEB-INF/fragments/menu/dropdown_account.jsp"/>

        <c:if test="${not empty isvisitor}">
            <jsp:include page="/WEB-INF/fragments/menu/dropdown_visitor.jsp"/>
        </c:if>

        <c:if test="${not empty isowner}">
            <jsp:include page="/WEB-INF/fragments/menu/dropdown_owner.jsp"/>
        </c:if>

        <c:if test="${not empty isadmin}">
            <jsp:include page="/WEB-INF/fragments/menu/dropdown_admin.jsp"/>
        </c:if>



    </ul>
</div>


