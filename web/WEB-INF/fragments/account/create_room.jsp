<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDlU4thxK_Whhxd8QQrv-ihGJUJuG4wIfA&callback=initMap"
type="text/javascript"></script>

<div id="search">
    <div class="tab">
        <h2>Add a new Room Listing</h2>
    </div>
    <div class="container">
        <hr/>
        <form id="form1" action="${pageContext.request.contextPath}/owner/newroom/create" method="POST">


            <table class="search_form" style="width:100%; border:none;">

                <div id="map_canvas" style="width:98%; height:55%; margin-left:10px"></div>
                <hr/>
                <tr>
                    <td class="label">Longitude</td>
                    <td>
                        <input  type="text" name="longitude" id="longitude" class="text" value="${longitude}" />
                        <c:forEach items="${errorMap.longitude}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Latitude</td>
                    <td>
                        <input  type="text" name="latitude" id="latitude" class="text" value="${latitude}" />
                        <c:forEach items="${errorMap.latitude}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td  class="label">Address</td>
                    <td >
                        <input  placeholder ="Athens Address 1"type="text" name="address" id="address" class="text" value="${address}" />
                        <c:forEach items="${errorMap.address}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td  class="label">Photo</td>
                    <td >
                        <input  type="text" name="photo" id="photo" class="text" value="${photo}" />
                        <c:forEach items="${errorMap.photo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label">Type</td>
                    <td width="34%">
                        <label>
                            <select name="type" id="type" class="select_field" >
                                <option selected="selected">Private room</option>
                                <option>Shared room</option>
                                <option>House</option>
                                <option>Villa</option>
                            </select>
                        </label></td>
                    <td class="label">Refridgerator</td>
                    <td><label>
                            <select name="refridgerator" id="refridgerator" class="select_field" >
                                <option selected="selected" value="true">Yes</option>
                                <option value="false">No</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Wifi</td>
                    <td>
                        <label>
                            <select name="wifi" id="wifi" class="select_field" >
                                <option selected="selected" value="true">Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Parking</td>
                    <td>
                        <label>
                            <select name="parking" id="parking" class="select_field" >
                                <option selected="selected" value="true">Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">Elevator</td>
                    <td>
                        <label>
                            <select name="elevator" id="elevator" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Kitchen</td>
                    <td>
                        <label>
                            <select name="kitchen" id="kitchen" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Aircondition</td>
                    <td><label>
                            <select name="aircondition" id="aircondition" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label></td>
                    <td class="label">Heating</td>
                    <td><label>
                            <select name="heating" id="heating" class="select_field" >
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">TV</td>
                    <td>
                        <label>
                            <select name="tv" id="tv" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Living Room</td>
                    <td>
                        <label>
                            <select name="livingRoom" id="livingRoom" class="select_field" >
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Smoking</td>
                    <td><label>
                            <select name="smoking" id="smoking" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Pets</td>
                    <td>
                        <label>
                            <select name="pets" id="pets" class="select_field">
                                <option selected="selected" value="true" >Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">Events</td>
                    <td>
                        <label>
                            <select name="events" id="events" class="select_field">
                                <option selected="selected" value="true">Yes</option>
                                <option value="false" >No</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Floor</td>
                    <td>
                        <label>
                            <select name="floor" id="floor" class="select_field">
                                <option selected="selected" >1</option>
                                <option >2</option>
                                <option >3</option>
                                <option >4</option>
                                <option >5</option>
                            </select>
                        </label>
                    </td>
                </tr> 

                <tr>
                    <td class="label">Country</td>
                    <td>
                        <input placeholder ="Greece"  type="text" name="country" id="country" class="text"  value="${country}"  />
                        <c:forEach items="${errorMap.country}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">City</td>
                    <td>
                        <input placeholder ="Athens"  type="text" name="city" id="city" class="text" value="${city}"  />
                        <c:forEach items="${errorMap.city}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label">Distrinct</td>
                    <td>
                        <input placeholder ="Hlisia "  type="text" name="distrinct" id="distrinct" class="text" value="${distrinct}"/>
                        <c:forEach items="${errorMap.distrinct}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Area</td>
                    <td>
                        <input  placeholder ="70-150 " type="text" name="area" id="area" class="text" value="${area}"/>
                        <c:forEach items="${errorMap.area}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">Available From</td>
                    <td>
                        <input placeholder ="Click to select available from"  type="text" name="dateFrom" id="start" class="text" value="${dateFrom}" />
                        <c:forEach items="${errorMap.dateFrom}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Available To</td>
                    <td>
                        <input placeholder ="Click to select available to"  type="text" name="dateTo" id="end" class="text" value="${dateTo}"/>
                        <c:forEach items="${errorMap.dateTo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
               
                <tr>
                    <td class="label">Cost Per Day</td>
                    <td>
                        <input placeholder ="50" type="text" name="costPerDay" id="costPerDay" class="text" value="${costPerDay}" />
                        <c:forEach items="${errorMap.costPerDay}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Cost Per Person</td>
                    <td>
                        <input  placeholder ="2" type="text" name="costPerPerson" id="costPerPerson" class="text" value="${costPerPerson}" />
                        <c:forEach items="${errorMap.costPerPerson}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>                

                <tr>
                    <td class="label">Bed Number</td>
                    <td>
                        <input  type="text" name="bedNumber" id="bedNumber" class="text"  value="${bedNumber}" />
                        <c:forEach items="${errorMap.bedNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Bedroom Number</td>
                    <td>
                        <input type="text" name="bedroomNumber" id="bedroomNumber" class="text" value="${bedroomNumber}" />
                        <c:forEach items="${errorMap.bedroomNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">WC Number</td>
                    <td>
                        <input  type="text" name="wcNumber" id="wcNumber" class="text" value="${wcNumber}" />
                        <c:forEach items="${errorMap.wcNumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Minimum Days</td>
                    <td>
                        <input type="text" name="minimumDays" id="minimumDays" class="text" value="${minimumDays}" />
                        <c:forEach items="${errorMap.minimumDays}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label">Max People</td>
                    <td>
                        <input  type="text" name="maxPeople" id="maxPeople" class="text" value="${maxPeople}" />
                        <c:forEach items="${errorMap.maxPeople}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Postcode</td>
                    <td>
                        <input  type="text" name="postcode" id="postcode" class="text" value="${postcode}" />
                        <c:forEach items="${errorMap.postcode}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>




                <tr>
                    <td class="label">Description</td>
                    <td>
                        <textarea name="description" id="description" rows="6" value="${description}" class="text"></textarea>
                        <c:forEach items="${errorMap.description}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td >
                    <td class="label">Access Comments</td>
                    <td>
                        <textarea name="comment" id="comment" rows="6" value="${comment}" class="text"></textarea>
                        <c:forEach items="${errorMap.comment}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">&nbsp;</td>
                </tr>
                <tr>
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label">
                        <label>
                            <input class="commitbutton" type="submit" alt="create" name="button2" id="button2" value="                 Create                    " />
                        </label>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>

