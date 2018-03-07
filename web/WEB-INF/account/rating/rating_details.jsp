<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<div id="recommendations">
    <div class="tab">
        <h2>Rate Room:</h2>
    </div>
    <div class="container">
        <div class="listing">
            <ul>
                <form  action="${pageContext.request.contextPath}/myrentings/submitrate" method="POST">

                    <li style="display: block; height: 200px; width: 95%;">
                        <div class="listinfo">
                            <img src="${renting.roomId.photoList[0].photographUrl}" alt="Photo" class="listingimage" style=" height: 150px;"/><!--width: 100px;-->
                            <!--<h3>Room id $ {recommendations.key.id};-->

                            <span class="price">Date From: ${renting.dateFrom}</span> 
                            <span class="price">Date To: ${renting.dateTo}</span>  
                            <br/>
                            
                            
                            <span class="rating"> Rate Room: 
                                <input type="radio" class="rating-input" value="5" 
                                       id="rating-input-2-5" name="ratingRoom">
                                <label for="rating-input-2-5" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="4" 
                                       id="rating-input-2-4" name="ratingRoom">
                                <label for="rating-input-2-4" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="3" 
                                       id="rating-input-2-3" name="ratingRoom">
                                <label for="rating-input-2-3" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="2" 
                                       id="rating-input-2-2" name="ratingRoom">
                                <label for="rating-input-2-2" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="1" 
                                       id="rating-input-2-1" name="ratingRoom">
                                <label for="rating-input-2-1" class="rating-star"></label>
                            </span>
                            <c:forEach items="${errorMap.ratingRoom}" var="obj">
                                <p class="fielderror">${obj}</p>
                            </c:forEach>
                            <br/>
                            <span class="rating">Rate Owner:
                                <input type="radio" class="rating-input" value="5" 
                                       id="rating-input-1-5" name="ratingOwner">
                                <label for="rating-input-1-5" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="4" 
                                       id="rating-input-1-4" name="ratingOwner">
                                <label for="rating-input-1-4" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="3" 
                                       id="rating-input-1-3" name="ratingOwner">
                                <label for="rating-input-1-3" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="2" 
                                       id="rating-input-1-2" name="ratingOwner">
                                <label for="rating-input-1-2" class="rating-star"></label>
                                <input type="radio" class="rating-input" value="1" 
                                       id="rating-input-1-1" name="ratingOwner">
                                <label for="rating-input-1-1" class="rating-star"></label>
                            </span>
                            <c:forEach items="${errorMap.ratingOwner}" var="obj">
                                <p class="fielderror">${obj}</p>
                            </c:forEach>

                            <div style="margin-bottom: 0px; margin-right: 11px;">
                                <button name="rentingId" type="submit" value="${renting.id}">Rate</button>
                            </div>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</div>

