<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="recommendations">
    <div class="tab">
        <h2>My Listings</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>

                <form name="OwnerRooms" action="${pageContext.request.contextPath}/owner/room/details" method="GET">
                    <c:forEach var="rooms" items="${list}">
                        <li style="display: block; height: 200px; width: 95%;">
                            <div class="listinfo"> 
                                <img src="${rooms.key.photoList[0].photographUrl}" alt="Photo" class="listingimage" style=" height: 150px; width: 195px"/><!--width: 100px;-->

                                <h3>                        
                                    ${rooms.key.locationList[0].city}, ${rooms.key.countryId.name} - ${rooms.key.id} (${rooms.key.roomTypeList[0].type})
                                </h3>
                                </br>
                                <span class="price">Cost per day: &euro;${rooms.key.costPerDay}</span> 
                                <span class="price">Max people allowed: ${rooms.key.maxPeople}</span>  
                                <span class="price">Area: ${rooms.key.area}m&sup2;</span>  
                                <div class="rating">
                                    </br>
                                    <span class="review-no">

                                        <c:forEach begin="1" end="${rooms.value[0]}">
                                            <span class="fa fa-star checked" style="color: #e6b800; font-size: 15px;"></span>
                                        </c:forEach>
                                        (${rooms.value[1]} reviews)</span>
                                </div>
                                <div>     
                                    <button name="Room_id" type="submit" value="${rooms.key.id}">More Details</button>
                                </div>
                            </div>

                            <div class="clear">&nbsp;</div>
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
                <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${1}">&laquo;</a>
            </c:if>
        </li>

        <li>
            <c:if test="${currentPage != 1}">    
                <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${currentPage - 1}">&lt;</a>
            </c:if>
        </li>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="currentPage eq i">
                    <li>
                        <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${i}"></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${currentPage + 1}">&gt;</a>
            </c:if>
        </li>
        <li>
            <c:if test="${currentPage lt numberOfPages}">
                <a href="${pageContext.request.contextPath}/owner/roomsList?currentPage=${numberOfPages}">&raquo;</a>
            </c:if>
        </li>
    </ul>
    <div class="clear">&nbsp;</div>
</div>
