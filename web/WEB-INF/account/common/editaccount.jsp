<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="search">
    <div class="tab">
        <h2>Edit Account</h2>
    </div>
    <div class="container">
        <form action="${pageContext.request.contextPath}/user/submitchanges" method="POST">           

            <table class="search_form" style="width:100%; border:none;">

                <tr>
                    <td width="10%" class="label">Name</td>
                    <td colspan="3"><label>
                            <input type="text" name="firstname" id="firstname" class="text longfield" value="${user.firstName}"/>
                        </label>
                        <c:forEach items="${errorMap.firstname}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>


                <tr>
                    <td width="10%" class="label">Surname</td>
                    <td colspan="3"><label>
                            <input type="text" name="surname" id="surname" class="text longfield" value="${user.surname}"/>
                        </label>
                        <c:forEach items="${errorMap.surname}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td width="10%" class="label">E-mail</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="email" id="email" class="text longfield" value="${user.email}"/>
                        </label>
                        <c:forEach items="${errorMap.email}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>


                <tr>
                    <td width="10%" class="label">Phone Number</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="phonenumber" id="phonenumber" class="text longfield" value="${user.phoneNumber}"/>
                        </label
                        <c:forEach items="${errorMap.phonenumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td width="10%" class="label">Photo</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="photo" id="photo" class="text longfield" value="${photo_url}"/>
                        </label
                        <c:forEach items="${errorMap.photo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

            </table>

            <br/>
            <br/>
            <br/>
            <hr/>
            <br/>
            <br/>
            <br/>
            <p>If you want to change your password, fill the following fields:</p>

            <table class="search_form" style="width:100%; border:none;">
                <tr>
                    <td width="10%" class="label">Old password</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="oldpassword" id="password" class="text longfield"  />
                        </label>
                        <c:forEach items="${errorMap.oldpassword}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td width="10%" class="label">Password</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="newpassword" id="password" class="text longfield" />
                        </label>
                        <c:forEach items="${errorMap.password}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>


                <tr>
                    <td width="10%" class="label">Repeat Password</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="repeat_password" id="repeat_password" class="text longfield">
                        </label>
                        <c:forEach items="${errorMap.repeat_password}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label">
                        <label>
                            <!--<input type="image" src="$ {pageContext.request.contextPath}/images/searchbtn.gif" alt="register" name="button2" id="button2" value="Submit" />-->
                            <input class="commitbutton" type="submit" alt="submit_button_edit" name="submit_button_edit" id="submit_button_edit" value="                 Commit                   " />
                        </label>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
