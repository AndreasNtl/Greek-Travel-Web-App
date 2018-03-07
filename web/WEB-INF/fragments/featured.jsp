<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="recommendations">
    <div class="tab">
        <h2>Recommendations</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>

                <form name="searchFeatured" action="${pageContext.request.contextPath}/room/details" method="POST">
                    <c:forEach  items="${SearchListFeatures}" var="recommendations" >
                        


                        <li>
                            <div class="listinfo"> 
                                <img src="${recommendations.key.photoList[0].photographUrl}" alt="Photo" class="listingimage" style=" height: 150px; width: 195px;"/><!--width: 100px;-->
                                <!--<h3>Room id $ {recommendations.key.id};-->
                                <h3>                        
                                    <input type="hidden" value ="${recommendations.key.photoList[0].photographUrl}">
                                    ${recommendations.key.locationList[0].city}, ${recommendations.key.countryId.name} - ${recommendations.key.id} (${recommendations.key.roomTypeList[0].type})
                                </h3>
                                </br>
                                <span class="price">Cost per day: &euro;${recommendations.key.costPerDay}</span> 
                                <span class="price">Max people allowed: ${recommendations.key.maxPeople}</span>  
                                <span class="price">Area: ${recommendations.key.area}m&sup2;</span>  
                                <div class="rating">
                                    </br>
                                    <span class="review-no">

                                        <c:forEach begin="1" end="${recommendations.value[0]}">
                                            <span class="fa fa-star checked" style="color: #e6b800; font-size: 15px;"></span>
                                        </c:forEach>
                                        (${recommendations.value[1]} reviews)</span>
                                </div>
                                <div>     
                                    <button name="Room_id" type="submit" value="${recommendations.key.id}">More Details</button>
                                </div>
                            </div>
                            <input name="User_id" type="hidden" value="${recommendations.key.userList[0].id}"> 



                            <div class="clear">&nbsp;</div>
                        </li>



                    </c:forEach>
                </form>
            </ul>
        </div>
    </div>
</div>