<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <form name="OwnerRoom" action="${pageContext.request.contextPath}/user/room_details/options" method="POST">  
        <input type="hidden" name="roomId" value="${list[0].id}" >
        <input type="hidden" name="ownerId" value="${User.id}" >
        <ul class="listing">
            <li>
                <div class="listinfo"> 
                    <img src="${User.photoList[0].photographUrl}" alt="photo" class="listingimage" style="width: 80px; height: 80px;" /> 
                    <h3 class="product-title" style="color: black">Owner Details</h3>
                    <div class="rating">
                        ${ownerOfTheRoomAvg[0][0]} Stars
                        <div class="stars">
                            <c:forEach begin="1" end="${ownerOfTheRoomAvg[0][0]}"  >
                                <span class="fa fa-star checked"></span>
                            </c:forEach>
                        </div>
                        <span class="review-no"> ${ownerOfTheRoomAvg[0][1]} reviews</span>
                    </div>
                </div>

                <table style="width: 100%">
                    <tr>
                        <td>
                            <h5>Nickname</h5>
                        </td>
                        <td>
                            <h5>
                                <c:out value="${User.nickname}"/>
                            </h5>
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <h5>Name</h5>
                        </td>
                        <td>
                            <h5>
                                <c:out value="${User.firstName}"/>
                            </h5>

                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <h5>Surname</h5>
                        </td>
                        <td>
                            <h5>
                                <c:out value="${User.surname}"/>
                            </h5>
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <h5>Email</h5>
                        </td>
                        <td>
                            <h5>
                                <c:out value="${User.email}"/>
                            </h5>
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <h5>Phone Number</h5>
                        </td>
                        <td>
                            <h5>
                                <c:out value="${User.phoneNumber}"/>
                            </h5>
                        </td> 
                    </tr>
                </table>
                <div>
                    <c:if test="${not empty book}">
                        <c:if test="${not empty isowner  || not empty isvisitor}">
                       
                            <div class="wrapper1"> 
                                <button name="contact" type="submit" value="contact" class="button">Contact Seller</button>
                            </div>
                        </c:if>
                    </c:if> 
                </div>
            </li>
        </ul>
    </form>
</div>
