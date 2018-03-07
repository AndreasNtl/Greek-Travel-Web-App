<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="container">
    <form name="admin_view_user" action="${pageContext.request.contextPath}/admin/users/user/activate" method="POST">
        <input type="hidden" name="id" value="${user.id}" />

        <div class="row">
            <div class="col-lg-3 col-sm-6">

                <div class="card hovercard">
                    <div class="cardheader">

                    </div>
                    <div class="avatar">
                        <img alt="userphoto" src="${userphoto}">
                    </div>
                    <div class="info">
                        <div class="title">  
                            <ul>

                                <li class="li_details" >
                                    <ul>
                                        <li  class="nestetli">Nickname</li>
                                        <li class="nestetli">
                                            <c:out value="${user.nickname}"/>
                                        </li>

                                    </ul>   
                                </li >


                                <li class="li_details">
                                    <ul >
                                        <li class="nestetli">Name</li>
                                        <li class="nestetli">
                                            <c:out value="${user.firstName}"/>
                                        </li>
                                    </ul>
                                </li>
                                <li class="li_details">
                                    <ul >
                                        <li class="nestetli">Surname</li>
                                        <li class="nestetli">
                                            <c:out value="${user.surname}"/>
                                        </li>
                                    </ul>

                                </li>
                                <li class="li_details"> 
                                    <ul >
                                        <li class="nestetli">Email</li>
                                        <li class="nestetli">
                                            <c:out value="${user.email}"/>
                                        </li>
                                    </ul>
                                </li>
                                <ul >
                                    <li class="nestetli">Phone-Number</li>
                                    <li class="nestetli">
                                        <c:out value="${user.phoneNumber}"/>
                                    </li>
                                </ul>
                            </ul>
                            <br>    
                            <br>
                            <br>
                            <c:choose>
                                <c:when test="${active == '0'}">
                                    <div class="info">
                                        <h2>No pending request</h2>
                                    </div>
                                </c:when>    
                                <c:otherwise>


                                    <div class="info">
                                        <ul>
                                            <li>  <h2>A user request is pending for Owner role</h2></li>
                                            <li>
                                                <input class="commitbuttonActivate" type="submit" alt="buttonActivate" name="buttonActivate" id="buttonActivete" value="Accept" />
                                            </li>
                                        </ul>
                                    </div>

                                    <br />
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>

                </div>

            </div>

        </div>
    </form>

</div>