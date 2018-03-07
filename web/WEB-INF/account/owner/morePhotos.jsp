<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Photo Gallery</h1>

<c:forEach var="photos" items="${photos}" varStatus="status" >
    <form action="${pageContext.request.contextPath}/owner/photos/delete" method="GET" style="display: inline-block;">
        <div class="container" style="display: inline-block;">
            <input type="hidden" name="photoId" value="${photos.id}">
            <input type="hidden" name="roomId" value="${roomId}">
            <img src="${photos.photographUrl}" alt="photo" class="listingimage" style="height: 180px"/>
            <c:if test="${deletePhoto}">
                <button type="submit" style="position: absolute; margin-left: -55px;">Delete</button>
            </c:if> 
        </div>
    </form>
</c:forEach>


<div id="paginations">
    <ul> 
        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${1}&roomId=${roomId}">&laquo;</a>
            </c:if>
        </li>

        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${currentPage - 1}&roomId=${roomId}">&lt;</a>
            </c:if>
        </li>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="currentPage eq i">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${i}&roomId=${roomId}"></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${i}&roomId=${roomId}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${currentPage + 1}&roomId=${roomId}">&gt;</a>
            </c:if>
        </li>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/user/room_details/options?morePhoto=morePhoto&currentPage=${numberOfPages}&roomId=${roomId}">&raquo;</a>
            </c:if>
        </li>
    </ul>
    <div class="clear">&nbsp;</div>
</div>
