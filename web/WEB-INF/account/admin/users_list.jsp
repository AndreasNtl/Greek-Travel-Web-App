<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div id="recommendations">
    <div class="tab">
        <h2>Users List</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>

                <form action="${pageContext.request.contextPath}/admin/users/user" method="GET">
                    <c:forEach var="users" items="${list}" varStatus="index">
                        <li style="display: block; width: 95%">
                            <div class="listinfo"> 
                                <img src="${listPhotosUrl[index.count - 1]}" alt="Photo" class="listingimage" style=" height: 150px;"/><!--width: 100px;-->
                                <!--<h3>Room id $ {recommendations.key.id};-->
                                <h3>                        
                                    ${users.firstName}  ${users.surname}
                                    </br>
                                    <c:if test="${users.active == '1'}">
                                        <text style="color: red; padding: 5px; ">
                                        Pending activate request!  
                                        </text>
                                    </c:if>
                                </h3>
                                </br>
                                <input type="hidden" name="nickname" value="${users.nickname}">
                                <span class="price">Nickname: ${users.nickname}</span> 
                                <span class="price">E-mail: ${users.email}</span> 
                                <span class="price">Phonenumber: ${users.phoneNumber}</span> 
                                <input type="submit" value="View Details" id="view_details" name="view_details" class="ViewDetails">
                            </div>
                            <input name="User_id" type="hidden" value="${search.key.userList[0].id}"> 
                            <div class="clear">&nbsp;</div>
                        </li>
                    </c:forEach>
                </form>

            </ul>
        </div>
    </div>
</div>




<div id="paginations">
    <!--<form name="admin_users_paginations" action="$ {pageContext.request.contextPath}/admin/users" method="POST">-->
    <ul> 
        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/admin/users?currentPage=${1}">&laquo;</a>
            </c:if>
        </li>

        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/admin/users?currentPage=${currentPage - 1}">&lt;</a>
            </c:if>
        </li>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="currentPage eq i">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}"></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/users?currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/admin/users?currentPage=${currentPage + 1}">&gt;</a>
            </c:if>
        </li>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/admin/users?currentPage=${numberOfPages}">&raquo;</a>
            </c:if>
        </li>
    </ul>
    <div class="clear">&nbsp;</div>
    <!--</form>-->
</div>