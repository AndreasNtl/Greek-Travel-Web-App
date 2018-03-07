<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<jsp:include page="/WEB-INF/fragments/menu/menulist_block_common.jsp"/>

<c:if test="${empty isadmin && empty isvisitor && empty isowner}">    
    <jsp:include page="/WEB-INF/fragments/menu/menulist_block_guest.jsp"/>
</c:if>

<c:if test="${not empty isadmin}">
    <jsp:include page="/WEB-INF/fragments/menu/menulist_block_admin.jsp"/>
</c:if>

<c:if test="${not empty isvisitor}">
    <jsp:include page="/WEB-INF/fragments/menu/menulist_block_visitor.jsp"/>
</c:if>

<c:if test="${not empty isowner}">
    <jsp:include page="/WEB-INF/fragments/menu/menulist_block_owner.jsp"/>
</c:if>

<div class="clear">&nbsp;</div>