<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dropdown">
    <button class="dropbtn">Account Options</button>
    <div class="dropdown-content">
        <c:choose>
            <c:when test="${empty isadmin && empty isvisitor && empty isowner}">    
                <a href="${pageContext.request.contextPath}/pages?page=register">Register</a>
                <a href="${pageContext.request.contextPath}/pages?page=login">Login</a>
            </c:when>  
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/user/profile">Profile</a>

                <c:if test="${empty isadmin}">
                    <a href="${pageContext.request.contextPath}/user/message/inbox">Messages</a>
                </c:if>
            </c:otherwise>
        </c:choose>



        <!--        <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>-->
    </div>
</div>