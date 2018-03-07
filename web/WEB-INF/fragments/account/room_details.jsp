<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">


<form name="OwnerRoom" action="${pageContext.request.contextPath}/user/room_details/options" method="POST">   

    <div class="tab">
        <h2>View Room Details</h2>
    </div>
    <c:forEach var="room" items="${list}">  
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <div class="container">
            <div class="card">

                <!--<div class="tab-pane active" id="pic-1">-->

                <img src="${room.photoList[0].photographUrl}" alt="photo" class="listingimage" style="height: 180px; float: left; margin-right:50px;" />
                <p><b>Room Description:</b> 
                    </br>
                    ${room.description}
                </p>
                </br>
                </br>
                <div class="rating">
                    <div class="stars">Room rating: 
                        <c:forEach begin="1" end="${avgOfTheRoom}"  >
                            <span class="fa fa-star checked"></span>
                        </c:forEach>(${reviewsOfTheRoom} reviews)
                    </div>
                </div>
                <h3 class="price">price per day: <span> <c:out value="${room.costPerDay}" /> $</span>                
                </h3>               
                <h3 class="price">extra per person: <span> <c:out value="${room.costPerPerson}" /> $</span>             
                    <c:if test="${not empty book}">
                        <button style="margin-left: 150px;" name="book" type="submit" value="book">Book</button>
                    </c:if>                 
                </h3>
                <c:if test="${not empty booked}">
                    
                    <h4>The room is booked</h4>
                </c:if>
                <c:if test="${not empty available}">
                    <h4>The room is available</h4>
                </c:if>

                <p style="clear: both;"> </p>

                <button name="morePhoto" type="submit" value="morePhoto">View more photos</button>

                <input type="hidden" name="roomId" value="${room.id}" >
                <input type="hidden" name="ownerId" value="${room.userList[0].id}" >
                <c:if test="${not empty roomsOwner}">

                    <button  style="margin-left: 170px; padding-left: 10%; padding-right: 10%;" name="edit" type="submit" value="edit">Edit</button>

                </c:if>

            </div>
        </div>

        <div id="moredetails">
            <div id="listing_details" style="float: left; width: 50%">
                <!--<div class="grid_12">-->
                <div class="map">
                    <iframe style="width: 85%; height: 220px" src = "https://maps.google.com/maps?q=${room.locationList[0].latitude},${room.locationList[0].longitude}&hl=es;z=14&amp;output=embed">
                    </iframe>

                </div>
                <!--</div>-->
                <!--<div class="clear">&nbsp;</div>-->
            </div>
            <jsp:include page="/WEB-INF/fragments/account/owner_details.jsp"/> 
            <div style="clear:both;"></div>
            <jsp:include page="/WEB-INF/fragments/single_item/moredetails.jsp"/>
        </div>
    </c:forEach>
</form>
