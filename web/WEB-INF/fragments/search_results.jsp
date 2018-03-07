<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false"%>


<div id="recommendations">
    <div class="tab">
        <h2>Search Results</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>

                <form name="searchFeatured" action="${pageContext.request.contextPath}/room/details" method="POST">
                    <c:forEach  items="${list}" var="room" >



                        <li>
                            <div class="listinfo"> 
                                <img src="${room.photoList[0].photographUrl}" alt="Photo" class="listingimage" style=" height: 150px; width: 195px"/><!--width: 100px;-->

                                <h3>                        
                                    <input type="hidden" value ="${rooom.photoList[0].photographUrl}">
                                    ${room.locationList[0].city}, ${room.countryId.name} - ${room.id} (${room.roomTypeList[0].type})
                                </h3>
                                </br>
                                <span class="price">Cost per day: &euro;${room.costPerDay}</span> 
                                <span class="price">Max people allowed: ${room.maxPeople}</span>  
                                <span class="price">Area: ${room.area}m&sup2;</span>  
                                <div class="rating">
                                    </br>
                                    <span class="review-no">

                                        <c:forEach  items="${SearchListFeatures}" var="search" >
                                            <c:if test="${room.id == search.key.id}">
                                                <c:forEach begin="1" end="${search.value[0]}">
                                                    <span class="fa fa-star checked" style="color: #e6b800; font-size: 15px;"></span>
                                                </c:forEach>
                                                (${search.value[1]} reviews)
                                            </c:if>  
                                        </c:forEach>
                                     </span>
                                </div>
                                <div>     
                                    <button name="Room_id" type="submit" value="${room.id}">More Details</button>
                                </div>
                            </div>
                            <input name="User_id" type="hidden" value="${room.userList[0].id}"> 



                            <div class="clear">&nbsp;</div>
                        </li>



                    </c:forEach>
                </form>
            </ul>
        </div>
    </div>
</div>

<c:if test="${not empty basicSearch}">
    <div id="paginations"> 
        <ul> 
            <li>
                <c:if test="${currentPage != 1}">    
                    <a href="${pageContext.request.contextPath}/user/search?currentPage=${1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}">&laquo;</a>
                </c:if>
            </li>

            <li>
                <c:if test="${currentPage != 1}">    
                    <a href="${pageContext.request.contextPath}/user/search?currentPage=${currentPage - 1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}">&lt;</a>
                </c:if>
            </li>

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="currentPage eq i">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/search?currentPage=${i}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}"></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/search?currentPage=${i}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <li>
                <c:if test="${currentPage lt numberOfPages}">
                    <a href="${pageContext.request.contextPath}/user/search?currentPage=${currentPage + 1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}">&gt;</a>
                </c:if>
            </li>
            <li>
                <c:if test="${currentPage lt numberOfPages}">
                    <a href="${pageContext.request.contextPath}/user/search?currentPage=${numberOfPages}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}">&raquo;</a>
                </c:if>
            </li>
        </ul>
        <div class="clear">&nbsp;</div>
    </div>
</c:if> 

<c:if test="${not empty advancedSearch}">
    <div id="paginations"> 
        <ul> 
            <li>
                <c:if test="${currentPage != 1}">    
                    <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}">&laquo;</a>
                </c:if>
            </li>

            <li>
                <c:if test="${currentPage != 1}">    
                    <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${currentPage - 1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}">&lt;</a>
                </c:if>
            </li>

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="currentPage eq i">
                        <li>
                            <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${i}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}"></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${i}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <li>
                <c:if test="${currentPage lt numberOfPages}">
                    <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${currentPage + 1}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}">&gt;</a>
                </c:if>
            </li>
            <li>
                <c:if test="${currentPage lt numberOfPages}">
                    <a href="${pageContext.request.contextPath}/user/advanced_search?currentPage=${numberOfPages}&dateFrom=${dateFrom}&dateTo=${dateTo}&peopleNum=${peopleNum}&costPerDay=${costPerDay}&distrinct=${distrinct}&city=${city}&country=${country}&address=${address}&type=${type}&wifi=${wifi}&refridgerator=${refridgerator}&parking=${parking}&elevator=${elevator}&kitchen=${kitchen}&aircondition=${aircondition}&heating=${heating}&tv=${tv}&livingRoom=${livingRoom}&smoking=${smoking}&pets=${pets}&events=${events}&floor=${floor}&area=${area}&bedNumber=${bedNumber}&bedroomNumber=${bedroomNumber}&wcNumber=${wcNumber}&minimumDays=${minimumDays}">&raquo;</a>
                </c:if>
            </li>
        </ul>
        <div class="clear">&nbsp;</div>
    </div>
</c:if> 