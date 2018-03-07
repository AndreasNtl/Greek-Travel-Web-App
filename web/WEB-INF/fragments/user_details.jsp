<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h1>User profile</h1>

<div class="container">
    <form  action="${pageContext.request.contextPath}/user/edit" method="POST">
        <div class="row">
            <div class="col-lg-3 col-sm-6" style="background: #f8f8f8;">

                <div class="card hovercard">

                    <div class="cardheader">
                    </div>

                    <div class="avatar">
                        <img src="${photo_url}"  alt="userphoto">
                    </div>
                    <div class="info">
                        <div class="title">  
                            <ul>

                                <li class="li_details" >
                                    <ul>
                                        <li class="nestetli">Nickname</li>
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

                        </div>
                    </div>

                    <div>
                        <input class="commonButtonEdit" type="submit" alt="buttonEdit" name="buttonEdit" id="buttonEdit" value="Edit" />
                    </div>

                </div>

            </div>
        </div>
    </form>
</div>



<!--<div id="recommendations">
    <div class="tab" style="text-align: center;">
        <h2>User Profile</h2>
    </div>
    <div class="container">
        <div class="listing" style="float: none">
            <ul>

                <form  action="$ {pageContext.request.contextPath}/user/edit" method="POST">
                    <li>
                        <div class="listinfo" > 
                            <img src="$ {photo_url}" alt="Photo" class="listingimage" style=" height: 160px; width: 160px"/>width: 100px;
                            <h3>Room id $ {recommendations.key.id};
                            <h3>                        
                                $ {user.firstName}  $ {user.surname}
                            </h3>
                            </br>
                            <input type="hidden" name="nickname" value="$ {user.nickname}">
                            <span class="price">Nickname: $ {user.nickname}</span> 
                            <span class="price">E-mail: $ {user.email}</span> 
                            <span class="price">Phonenumber: $ {user.phoneNumber}</span> 

                            <input type="submit" alt="buttonEdit" name="buttonEdit" id="buttonEdit" value="Edit Profile" />
                        </div>
                        <div class="clear">&nbsp;</div>
                    </li>
                </form>

            </ul>
        </div>
    </div>
</div>-->
