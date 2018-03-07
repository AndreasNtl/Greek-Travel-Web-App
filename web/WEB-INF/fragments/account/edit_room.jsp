<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDlU4thxK_Whhxd8QQrv-ihGJUJuG4wIfA&callback=initMap"
type="text/javascript"></script>


<div id="search">
    <div class="tab">
        <h2>Search Property To Rent</h2>
    </div>
    <div class="container">
        <form action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form1" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">


            <table class="search_form" style="width:100%; border:none;">

                <tr>
                    <td class="label">Area</td>
                    <td>
                        <input  placeholder ="70-150 " type="text" name="area" id="area" class="text" value="${roomThatWillByChange.area}"/>

                        <c:forEach items="${errorMap.area}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Max People</td>
                    <td>
                        <input  type="text" name="maxPeople" id="maxPeople" class="text" value="${roomThatWillByChange.maxPeople}" />
                        <c:forEach items="${errorMap.maxPeople}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">Cost Per Day</td>
                    <td>
                        <input placeholder ="50" type="text" name="costPerDay" id="costPerDay" class="text" value="${roomThatWillByChange.costPerDay}" />
                        <c:forEach items="${errorMap.costPerDay}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Cost Per Person</td>
                    <td>
                        <input  placeholder ="2" type="text" name="costPerPerson" id="costPerPerson" class="text" value="${roomThatWillByChange.costPerPerson}" />
                        <c:forEach items="${errorMap.costPerPerson}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>                

                <tr>
                    <td class="label">Bed Number</td>
                    <td>
                        <input  type="text" name="bedNumber" id="bedNumber" class="text"  value="${roomThatWillByChange.bedNumber}" />
                        <c:forEach items="${errorMap.bedNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Bedroom Number</td>
                    <td>
                        <input type="text" name="bedroomNumber" id="bedroomNumber" class="text" value="${roomThatWillByChange.bedroomNumber}" />
                        <c:forEach items="${errorMap.bedroomNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">WC Number</td>
                    <td>
                        <input  type="text" name="wcNumber" id="wcNumber" class="text" value="${roomThatWillByChange.wcNumber}" />
                        <c:forEach items="${errorMap.wcNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Minimum Days</td>
                    <td>
                        <input type="text" name="minimumDays" id="minimumDays" class="text" value="${roomThatWillByChange.minimumDays}" />
                        <c:forEach items="${errorMap.minimumDays}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">Wifi</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.wifi}">
                                <select name="wifi" id="wifi" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.wifi}">
                                <select name="wifi" id="wifi" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                    <td class="label">Parking</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.parking}">
                                <select name="parking" id="parking" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.parking}">
                                <select name="parking" id="parking" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Elevator</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.elevator}">
                                <select name="elevator" id="elevator" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.elevator}">
                                <select name="elevator" id="elevator" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                    <td class="label">Kitchen</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.kitchen}">
                                <select name="kitchen" id="kitchen" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.kitchen}">
                                <select name="kitchen" id="kitchen" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Air condition</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.aircondition}">
                                <select name="aircondition" id="aircondition" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.aircondition}">
                                <select name="aircondition" id="aircondition" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    <td class="label">Heating</td>
                    <td>
                        <label>

                            <c:if test="${roomThatWillByChange.heating}">
                                <select name="heating" id="heating" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.heating}">
                                <select name="heating" id="heating" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>

                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">TV</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.tv}">
                                <select name="tv" id="tv" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.tv}">
                                <select name="tv" id="tv" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                    <td class="label">Living Room</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.livingRoom}">
                                <select name="livingRoom" id="livingRoom" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.livingRoom}">
                                <select name="livingRoom" id="livingRoom" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Smoking</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.smoking}">
                                <select name="smoking" id="smoking" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.smoking}">
                                <select name="smoking" id="smoking" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                    <td class="label">Pets</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.pets}">
                                <select name="pets" id="pets" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.pets}">
                                <select name="pets" id="pets" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>

                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">Events</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.events}">
                                <select name="events" id="events" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.events}">
                                <select name="events" id="events" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                    <td class="label">Refrigerator</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.refridgerator}">
                                <select name="refridgerator" id="refridgerator" class="select_field" >
                                    <option selected="selected" value="true">
                                        Yes
                                    </option>                            
                                    <option value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${not roomThatWillByChange.refridgerator}">
                                <select name="refridgerator" id="refridgerator" class="select_field" >
                                    <option value="true">
                                        Yes
                                    </option>                            
                                    <option selected="selected"  value="false" >
                                        No
                                    </option>
                                </select>
                            </c:if>
                        </label>
                    </td>
                </tr> 

                <tr>
                    <td class="label">Description</td>
                    <td>
                        <textarea name="description" id="description" rows="5" class="text longfield">${roomThatWillByChange.description}                                         
                        </textarea>
                        <c:forEach items="${errorMap.description}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Floor</td>

                    <td>
                        <c:if test="${empty roomThatWillByChange.floor}">
                            <select name="floor" id="floor" class="select_field">

                                <option value="1" selected="selected">
                                    1
                                </option>  
                                <option value="2">
                                    2
                                </option>  
                                <option value="3">
                                    3
                                </option>  
                                <option value="4">
                                    4
                                </option>  
                                <option value="5">
                                    5
                                </option>  
                            </select>
                        </c:if>

                        <c:if test="${roomThatWillByChange.floor eq 1}">
                            <select name="floor" id="floor" class="select_field">

                                <option value="1" selected="selected">
                                    1
                                </option>  
                                <option value="2">
                                    2
                                </option>  
                                <option value="3">
                                    3
                                </option>  
                                <option value="4">
                                    4
                                </option>  
                                <option value="5">
                                    5
                                </option>  
                            </select>
                        </c:if>

                        <c:if test="${roomThatWillByChange.floor eq 2}">
                            <select name="floor" id="floor" class="select_field">

                                <option value="1">
                                    1
                                </option>  
                                <option value="2"  selected="selected">
                                    2
                                </option>  
                                <option value="3">
                                    3
                                </option>  
                                <option value="4">
                                    4
                                </option>  
                                <option value="5">
                                    5
                                </option>  
                            </select>
                        </c:if>

                        <c:if test="${roomThatWillByChange.floor eq 3}">
                            <select name="floor" id="floor" class="select_field">

                                <option value="1">
                                    1
                                </option>  
                                <option value="2"  >
                                    2
                                </option>  
                                <option value="3" selected="selected">
                                    3
                                </option>  
                                <option value="4">
                                    4
                                </option>  
                                <option value="5">
                                    5
                                </option>  
                            </select>
                        </c:if>

                        <c:if test="${roomThatWillByChange.floor eq 4}">
                            <select name="floor" id="floor" class="select_field">
                                <option value="1">
                                    1
                                </option>  
                                <option value="2">
                                    2
                                </option>  
                                <option value="3">
                                    3
                                </option>  
                                <option value="4" selected="selected">
                                    4
                                </option>  
                                <option value="5">
                                    5
                                </option>  
                            </select>
                        </c:if>

                        <c:if test="${roomThatWillByChange.floor eq 5}">
                            <select name="floor" id="floor" class="select_field">
                                <option value="1">
                                    1
                                </option>  
                                <option value="2">
                                    2
                                </option>  
                                <option value="3">
                                    3
                                </option>  
                                <option value="4">
                                    4
                                </option>  
                                <option value="5" selected="selected">
                                    5
                                </option>  
                            </select>
                        </c:if>
                    </td>
                </tr>
            </table>
            <div class="wrapper">
                <input type="submit" name="room_details" value="Details" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.detail}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div>       
        </form>

        <hr/>

        <form  action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form2" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">
            <table class="search_form" style="width:100%; border:none;">

                <div id="map_canvas" style="width:98%; height:55%; margin-left:10px"></div>
                <!--                <input type="hidden" id="lat" value="$ {roomThatWillByChange.locationList[0].latitude}"/>
                                <input type="hidden" id="lng" value="$ {roomThatWillByChange.locationList[0].longitude}" />
                                <input type="button" value="Set market to current location" onclick="pan()" />-->
                <tr>
                    <td class="label">Longitude</td>
                    <td>
                        <input  type="text" name="longitude" id="longitude" class="text" value="${roomThatWillByChange.locationList[0].longitude}" />
                        <c:forEach items="${errorMap.longitude}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Latitude</td>
                    <td>
                        <input  type="text" name="latitude" id="latitude" class="text" value="${roomThatWillByChange.locationList[0].latitude}" />
                        <c:forEach items="${errorMap.latitude}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>


                <tr>
                    <td class="label">District</td>
                    <td>
                        <input placeholder ="Hlisia "  type="text" name="distrinct" id="distrinct" class="text" value="${roomThatWillByChange.locationList[0].distrinct}"/>
                        <c:forEach items="${errorMap.distrinct}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>                    
                    <td class="label">City</td>
                    <td>
                        <input placeholder ="Athens"  type="text" name="city" id="city" class="text" value="${roomThatWillByChange.locationList[0].city}"  />
                        <c:forEach items="${errorMap.city}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">Postcode</td>
                    <td>
                        <input  type="text" name="postcode" id="postcode" class="text" value="${roomThatWillByChange.locationList[0].postcode}" />
                        <c:forEach items="${errorMap.postcode}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>            
                    <td  class="label">Address</td>
                    <td >
                        <input  placeholder ="Athens Address 1"type="text" name="address" id="address" class="text" value="${roomThatWillByChange.locationList[0].address}" />
                        <c:forEach items="${errorMap.address}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td  class="label">Access Comments</td>
                    <td >
                        <input  placeholder ="140 The Gost of Zwgrafou"type="text" name="accessComments" id="accessComments" class="text" value="${roomThatWillByChange.locationList[0].accessComments}" />
                        <c:forEach items="${errorMap.accessComments}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

            </table>
            <div class="wrapper">
                <input type="submit" name="room_location" value="Location" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.location}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div>  
        </form>  

        <hr/>
        <form action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form4" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">
            <table class="search_form" style="width:100%; border:none;">
                <td class="label">Country</td>
                <td>
                    <input placeholder ="Greece"  type="text" name="country" id="country" class="text"  value="${roomThatWillByChange.countryId.name}"  />
                    <c:forEach items="${errorMap.country}" var="obj">
                        <p class="fielderror">${obj}</p>
                    </c:forEach>
                </td>
            </table>
            <div class="wrapper">
                <input type="submit" name="room_country" value="Country" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.countryA}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div> 
        </form>
        <hr/>   

        <form action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form3" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">
            <table class="search_form" style="width:100%; border:none;">

                <tr>
                    <td class="label">Available From</td>
                    <td>
                        <input placeholder ="Click to select available from"  type="text" name="dateFrom" id="start" class="text" value="${roomThatWillByChange.availabilityList[0].dateFrom}" />
                        <c:forEach items="${errorMap.dateFrom}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Available To</td>
                    <td>
                        <input placeholder ="Click to select available to"  type="text" name="dateTo" id="end" class="text" value="${roomThatWillByChange.availabilityList[0].dateTo}"/>
                        <c:forEach items="${errorMap.dateTo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <div class="wrapper">
                <input type="submit" name="room_availability" value="Availability" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.availability}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div> 
        </form>

        <hr/> 

        <form action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form5" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">
            <table class="search_form" style="width:100%; border:none;">
                <td  class="label">Photo</td>
                <td >
                    <input  type="text" name="photo" id="photo" class="text" value="${roomThatWillByChange.photoList[0].photographUrl}" />
                    <c:forEach items="${errorMap.photo}" var="obj">
                        <p class="fielderror">${obj}</p>
                    </c:forEach>
                </td>
            </table>
            <div class="wrapper">
                <input type="submit" name="room_photo" value="Photo" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.photos}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div> 
        </form>

        <hr/>

        <form  action="${pageContext.request.contextPath}/owner/room/edit" method="POST">
            <input type="hidden" name="form" value="form6" />
            <input type="hidden" name="id" value="${roomThatWillByChange.id}">
            <table class="search_form" style="width:100%; border:none;">

                <tr>
                    <td class="label">Room Type</td>
                    <td>
                        <label>
                            <c:if test="${roomThatWillByChange.roomTypeList[0].type == 'private room'}">
                                <select name="type" id="type" class="select_field" >
                                    <option selected="selected" value="private room">
                                        Private room
                                    </option>                            
                                    <option value="shared room" >
                                        Shared room
                                    </option>
                                    <option value="house" >
                                        House
                                    </option>
                                    <option value="villa" >
                                        Villa
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${roomThatWillByChange.roomTypeList[0].type == 'shared room'}">
                                <select name="type" id="type" class="select_field" >
                                    <option selected="selected" value="shared room">
                                        Shared room
                                    </option>                            
                                    <option value="private room" >
                                        Private room
                                    </option>
                                    <option value="house" >
                                        House
                                    </option>
                                    <option value="villa" >
                                        Villa
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${roomThatWillByChange.roomTypeList[0].type == 'house'}">
                                <select name="type" id="type" class="select_field" >
                                    <option selected="selected" value="house">
                                        House
                                    </option>                            
                                    <option value="private room" >
                                        Private room
                                    </option>
                                    <option value="shared room" >
                                        Shared room
                                    </option>
                                    <option value="villa" >
                                        Villa
                                    </option>
                                </select>
                            </c:if>

                            <c:if test="${roomThatWillByChange.roomTypeList[0].type == 'villa'}">
                                <select name="type" id="type" class="select_field" >
                                    <option selected="selected" value="villa">
                                        Villa
                                    </option>                            
                                    <option value="private room" >
                                        Private room
                                    </option>
                                    <option value="shared room" >
                                        Shared room
                                    </option>
                                    <option value="house" >
                                        House
                                    </option>
                                </select>
                            </c:if>

                        </label>
                    </td>
                </tr> 
            </table>
            <div class="wrapper">
                <input type="submit" name="room_type" value="Type" class="button" style="width: 20%;">
                <c:forEach items="${errorMap.type}" var="obj">
                    <p class="fielderror">${obj}</p>
                </c:forEach>
            </div> 
        </form>
    </div>
</div>