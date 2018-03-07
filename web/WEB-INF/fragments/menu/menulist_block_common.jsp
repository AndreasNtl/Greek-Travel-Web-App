<%-- 
    Document   : menu
    Created on : Jul 26, 2017, 10:55:51 AM
    Author     : psilos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="menulist block">
    <h2 class="sidebar_head">Generic options</h2>
    <ul class="normalmenu">
        <li><a href="${pageContext.request.contextPath}/user/profile">Profile</a></li>
        <li><a href="${pageContext.request.contextPath}/user/message/inbox">Messages</a></li>
        <li><a href="${pageContext.request.contextPath}/pages?page=advanced">Search</a></li>
    </ul>
    <div class="clear">&nbsp;</div>
</div>