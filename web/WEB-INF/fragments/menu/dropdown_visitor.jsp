<%-- 
    Document   : menu
    Created on : Jul 26, 2017, 10:55:51 AM
    Author     : psilos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dropdown">
    <button class="dropbtn">Visitor Options</button>
    <div class="dropdown-content">
        <a href="${pageContext.request.contextPath}/visitor/rentings">My bookings</a>
    </div>
</div>