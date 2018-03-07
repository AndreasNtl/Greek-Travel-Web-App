<%-- 
    Document   : menu
    Created on : Jul 26, 2017, 10:55:51 AM
    Author     : psilos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="dropdown">
    <button class="dropbtn">Admin Options</button>
    <div class="dropdown-content">
        <a href="${pageContext.request.contextPath}/admin/users">Activate Users</a>
        <a href="${pageContext.request.contextPath}/admin/export">XML Export</a>

        <!--        <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>-->
    </div>
</div>