<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div id="search">
    <div class="tab">
        <h2>Search Property To Rent</h2>
    </div>
    <div class="container">
        <form id="form1" action="${pageContext.request.contextPath}/user/search" method="POST">
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
                    <!--<td class="label">Address</td>-->
                    <!--<td colspan="3">-->
                        <!--<label>-->
                            <input  placeholder ="Athens Address 1" type="hidden" name="address" id="address" class="text longfield" value="${address}" />
                            <!--<br>-->
                        <!--</label>-->
                        <c:forEach items="${errorMap.address}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    <!--</td>-->
                <!--</tr>-->
                <tr>
                    <td class="label">Country</td>
                    <td>
                        <input placeholder ="Greece"  type="text" name="country" id="country" class="text" value="${country}"  />
                        <c:forEach items="${errorMap.country}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">City</td>
                    <td>
                        <input placeholder ="Athens"  type="text" name="city" id="city" class="text" value="${city}"/>
                        <c:forEach items="${errorMap.city}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="label">District</td>
                    <td>
                        <input placeholder ="Hlisia "  type="text" name="distrinct" id="distrinct" class="text" value="${distrinct}"/>
                        <c:forEach items="${errorMap.distrinct}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                    <td class="label">Type</td>
                    <td width="50%">
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
                        <input  placeholder ="2" type="text" name="peopleNum" id="peopleNum" class="text" value="${peopleNum}"/>
                        <c:forEach items="${errorMap.peopleNum}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label">
                        <label>
                            <br>
                            <input type="image" src="${pageContext.request.contextPath}/images/searchbtn.gif" alt="search" name="button2" id="button2" value="Submit" />
                        </label>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>