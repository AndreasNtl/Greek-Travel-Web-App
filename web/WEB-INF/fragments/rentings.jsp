<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<div id="recommendations">
    <div class="tab">
        <h2>My Bookings</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>
                <form action="${pageContext.request.contextPath}/myrentings/rating" method="POST">
                    <c:forEach var="rentings" items="${list}">
                        <li style="display: block; height: 200px; width: 95%;">
                            <div class="listinfo">
                                <img src="${rentings.roomId.photoList[0].photographUrl}" alt="Photo" class="listingimage" style=" height: 150px;"/><!--width: 100px;-->
                                <!--<h3>Room id $ {recommendations.key.id};-->
                                <h3>
                                    ${rentings.roomId.locationList[0].city}, ${rentings.roomId.countryId.name} (${rentings.roomId.roomTypeList[0].type})
                                </h3>
                                </br>
                                <span class="price">Date From: ${rentings.dateFrom}</span> 
                                <span class="price">Date To: ${rentings.dateTo}</span>  
                                <c:if test="${not empty rentings.roomRating && not empty rentings.ownerRating }">
                                    </br>

                                    <div class="rating">

                                        <span class="review-no">
                                            Room Rating:
                                            <c:forEach begin="1" end="${rentings.roomRating}">
                                                <span class="fa fa-star checked" style="color: #e6b800; font-size: 15px;"></span>
                                            </c:forEach>
                                        </span>
                                    </div>

                                    </br>
                                    <div class="rating">
                                        <span class="review-no">
                                            Owner Rating:
                                            <c:forEach begin="1" end="${rentings.ownerRating}">
                                                <span class="fa fa-star checked" style="color: #e6b800; font-size: 15px;"></span>
                                            </c:forEach>
                                        </span>
                                    </div>
                                </c:if>
<!--                                <form action="$ {pageContext.request.contextPath}/room/details" method="POST">-->
<!--                                    <div>     
                                        <button name="Room_id" type="submit" value="$ {rentings.roomId}">More Details</button>
                                    </div>-->
                                <!--</form>-->
                            </div>
                            <c:if test="${empty rentings.roomRating && empty rentings.ownerRating }">
                                <fmt:formatDate value="${now}" pattern="yyyy/MM/dd" var="currentDate"/>
                                <fmt:formatDate value="${rentings.dateTo}" pattern="yyyy/MM/dd" var="dateToCommper" />
                                <!-- >= -->
                                <%--<c:if test="${dateToCommper le currentDate}">--%> 
                                <!-- > -->
                                <c:if test="${dateToCommper lt currentDate}">
                                    <div>
                                        <button name="rentingId" type="submit" value="${rentings.id}" class="ViewDetails">Rate Room</button>
                                    </div>
                                </c:if>
                            </c:if>
                        </li>
                    </c:forEach>
                </form>
            </ul>
        </div>
    </div>
</div>




<div id="paginations">
    <ul> 
        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${1}">&laquo;</a>
            </c:if>
        </li>

        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${currentPage - 1}">&lt;</a>
            </c:if>
        </li>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="currentPage eq i">
                    <li>
                        <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${i}"></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${currentPage + 1}">&gt;</a>
            </c:if>
        </li>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/visitor/rentings?currentPage=${numberOfPages}">&raquo;</a>
            </c:if>
        </li>
    </ul>
    <div class="clear">&nbsp;</div>
</div>

