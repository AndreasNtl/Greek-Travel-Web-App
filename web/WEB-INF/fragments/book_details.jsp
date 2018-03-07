<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="recommendations">
    <div class="tab">
        <h2>Confirm Booking</h2>
    </div>
    <div class="container">

        <form name="book_room" action="${pageContext.request.contextPath}/room/booking" method="POST">
            <ul class="listing" style="margin-left: 150px;" >

                <li>
                    <div class="listinfo"> 

                        <div>
                            <table>


                                <c:if test="${not empty success}">
                                    <li style="color: #009933; background:lightgray; padding: 5px; ">
                                        Your booking has been successfully created.
                                    </li>
                                </c:if>  
                                <br>

                                <c:if test="${ empty success}">
                                    <li style="color:#164c51; background:lightgray;   padding: 5px; ">
                                        Check Availability :
                                        <c:forEach items="${errorMap.availability}" var="obj">
                                            <p class="fielderror">${obj}</p>
                                        </c:forEach>
                                    </li
                                </c:if>  

                                <br>

                                <c:if test="${ empty success}">

                                    <tr>
                                        <td class="label">Date From</td>
                                        <td>
                                            <input placeholder ="Click to select date from"  type="text" name="dateFrom" id="start" class="text" value="${dateFrom}" />
                                            <c:forEach items="${errorMap.dateFrom}" var="obj">
                                                <p class="fielderror">${obj}</p>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">Date To</td>
                                        <td>
                                            <input placeholder ="Click to select date to"  type="text" name="dateTo" id="end" class="text" value="${dateTo}"/>
                                            <c:forEach items="${errorMap.dateTo}" var="obj">
                                                <p class="fielderror">${obj}</p>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:if>  
                            </table>  
                        </div>
                        <div>
                            <br>
                            <c:if test="${empty success}">
                                <input type="hidden" value="${ownerId}" name="ownerId" >
                                <input type="hidden" value="${roomId}" name="roomId" >
                                <input type="submit" value="Book" name="Book">
                            </c:if>  

                        </div>  
                    </div>            
                    <div class="clear">&nbsp;</div>
                </li>
            </ul>
        </form>
    </div>
</div>