<%-- 
    Document   : advanced_search
    Created on : Aug 31, 2017, 6:38:48 PM
    Author     : andreas
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="search">
    <div class="tab">
        <h2>Search Property To Rent</h2>
    </div>
    <div class="container">
        <form id="form1" action="${pageContext.request.contextPath}/user/advanced_search" method="POST">
            <table class="search_form" style="width:100%; border:none;">


                <tr>
                    <td class="label">Date From</td>
                    <td>
                        <input placeholder ="Click to select date from"  type="text" name="dateFrom" id="start" class="text" value="${dateFrom}" />
                        <c:forEach items="${errorMap.dateFrom}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Date To</td>
                    <td>
                        <input placeholder ="Click to select date to"  type="text" name="dateTo" id="end" class="text" value="${dateTo}"/>
                        <c:forEach items="${errorMap.dateTo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                <!--<tr>-->
                    <!--<td  class="label">Address</td>-->
                    <!--<td >-->
                        <input  placeholder ="Athens Address 1" type="hidden" name="address" id="address" class="text" value="${address}" />
                        <c:forEach items="${errorMap.address}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    <!--</td>-->
                <!--</tr>-->
                <tr>
                    <td class="label">Type</td>
                    <td width="34%">
                        <label>
                            <select name="type" class="select_field"  >
                                <option value="" >Not interested</option>
                                <option value="private room" ${type  == "private room" ? 'selected' : ''}>Private room</option>
                                <option value="shared room" ${type  == "shared room" ? 'selected' : ''}>Shared room</option>
                                <option value="house" ${type  == "house" ? 'selected' : ''}>House</option>
                                <option value="villa" ${type  == "villa" ? 'selected' : ''}>Villa</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Refridgerator</td>
                    <td>
                        <label>
                            <select name="refridgerator" id="refridgerator" class="select_field" >
                                <option value="true" ${ refridgerator == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ refridgerator == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ refridgerator != "true" && refridgerator != "false"  ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Wifi</td>
                    <td>
                        <label>
                            <select name="wifi" id="wifi" class="select_field" >
                                <option value="true" ${ wifi == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ wifi == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${wifi != "true" && wifi != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Parking</td>
                    <td>
                        <label>
                            <select name="parking" id="parking" class="select_field" >
                                <option value="true" ${ parking == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ parking == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ parking != "true" && parking != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">Elevator</td>
                    <td>
                        <label>
                            <select name="elevator" id="elevator" class="select_field">
                                <option value="true" ${ elevator == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ elevator == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ elevator != "true" && elevator != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Kitchen</td>
                    <td>
                        <label>
                            <select name="kitchen" id="kitchen" class="select_field">
                                <option value="true" ${ kitchen == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ kitchen == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ kitchen != "true" && kitchen != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Aircondition</td>
                    <td><label>
                            <select name="aircondition" id="aircondition" class="select_field">
                                <option value="true" ${ aircondition == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ aircondition == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ aircondition != "true" && aircondition != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label></td>
                    <td class="label">Heating</td>
                    <td><label>
                            <select name="heating" id="heating" class="select_field" >
                                <option value="true" ${ heating == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ heating == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ heating != "true" && heating != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">TV</td>
                    <td>
                        <label>
                            <select name="tv" id="tv" class="select_field">
                                <option value="true" ${ tv == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ tv == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ tv != "true" && tv != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Living Room</td>
                    <td>
                        <label>
                            <select name="livingRoom" id="livingRoom" class="select_field" >
                                <option value="true" ${ livingRoom == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ livingRoom == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ livingRoom != "true" && livingRoom != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="label">Smoking</td>
                    <td><label>
                            <select name="smoking" id="smoking" class="select_field">
                                <option value="true" ${ smoking == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ smoking == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ smoking != "true" && smoking != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Pets</td>
                    <td>
                        <label>
                            <select name="pets" id="pets" class="select_field">
                                <option value="true" ${ pets == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ pets == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ pets != "true" && pets != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td class="label">Events</td>
                    <td>
                        <label>
                            <select name="events" id="events" class="select_field">
                                <option value="true" ${ events == "true" ? 'selected' : ''}>Yes</option>
                                <option value="false" ${ events == "false" ? 'selected' : ''}>No</option>
                                <option value="" ${ events != "true" && events != "false" ? 'selected' : ''}>Not interested</option>
                            </select>
                        </label>
                    </td>
                    <td class="label">Floor</td>
                    <td>
                        <label>
                            <select name="floor" id="floor" class="select_field">
                                <option value="1" ${ floor == "1" ? 'selected' : ''}>1</option>
                                <option value="2" ${ floor == "2" ? 'selected' : ''}>2</option>
                                <option value="3" ${ floor == "3" ? 'selected' : ''}>3</option>
                                <option value="4" ${ floor == "4" ? 'selected' : ''}>4</option>
                                <option value="5" ${ floor == "5" ? 'selected' : ''}>5</option>
                                <option value="" ${ floor != "1" && floor != "2" && floor != "3" && floor != "4" && floor != "5" ? 'selected' : ''}>Not interested</option>
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
                    <td class="label">District</td>
                    <td>
                        <input placeholder ="ie. Hlisia "  type="text" name="distrinct" id="distrinct" class="text" value="${distrinct}"/>
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
                    <td class="label">Cost Per Day</td>
                    <td>
                        <input placeholder ="50" type="text" name="costPerDay" id="costPerDay" class="text" value="${costPerDay}" />
                        <c:forEach items="${errorMap.costPerDay}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">People Number</td>
                    <td>
                        <input  placeholder ="2" type="text" name="peopleNum" id="peopleNum" class="text" value="${peopleNum}" />
                        <c:forEach items="${errorMap.peopleNum}" var="obj">
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
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label">
                        <label>
                            <input type="image" src="${pageContext.request.contextPath}/images/searchbtn.gif" alt="search" name="button2" id="button2" value="Submit" />
                        </label>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>