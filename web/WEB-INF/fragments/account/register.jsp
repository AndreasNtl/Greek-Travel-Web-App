<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="search">
    <div class="tab">
        <h2>Register a new account</h2>
    </div>
    <div class="container">
        <form id="register_form" action="${pageContext.request.contextPath}/user/register" method="POST">

            <table class="search_form" style="width:100%; border:none;">
                
                <tr>
                    <td width="10%" class="label">Nickname</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="nickname" id="nickname" class="text longfield" value="${nickname}" />
                        </label>
                        <c:forEach items="${errorMap.nickname}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                
                <tr>
                    <td width="10%" class="label">Password</td>
                    <td colspan="3">
                        <label>
                            <input type="password" name="password" id="password" class="text longfield" value="" />
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
                            <input type="text" name="repeat_password" id="repeat_password" class="text longfield" value="" />
                        </label>
                        <c:forEach items="${errorMap.repeat_password}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td width="10%" class="label">E-mail</td>
                    <td colspan="3">
                        <label>
                            <input type="text" name="email" id="email" class="text longfield" value="${email}" />
                        </label>
                        <c:forEach items="${errorMap.email}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                
                <tr>
                    <td width="10%" class="label">Name</td>
                    <td colspan="3"><label>
                            <input type="text" name="firstname" id="firstname" class="text longfield" value="${firstname}" />
                        </label>
                        <c:forEach items="${errorMap.firstname}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                
                <tr>
                    <td width="10%" class="label">Surname</td>
                    <td colspan="3"><label>
                            <input type="text" name="surname" id="surname" class="text longfield" value="${surname}"/>
                        </label>
                        <c:forEach items="${errorMap.surname}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                
                <tr>
                    <td width="10%" class="label">Phone Number</td>
                    <td colspan="3"><label>
                            <input type="text" name="phonenumber" id="phonenumber" class="text longfield" value="${phonenumber}" />
                        </label>
                        <c:forEach items="${errorMap.phonenumber}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td width="10%" class="label">Role</td>
                    <td colspan="3">
                        <label>                            
                             <input type="checkbox" name="isowner" value="Owner">Do you want to sell rooms?<br>
                        </label>
                    </td>
                </tr>
                
                <tr>
                    <td width="10%" class="label">Photo</td>
                    <td colspan="3"><label>
                            <input type="text" name="photo" id="photo" class="text longfield" value="${photo}" />
                        </label>
                        <c:forEach items="${errorMap.photo}" var="obj">
                            <p class="fielderror">${obj}</p>
                        </c:forEach>
                    </td>
                </tr>
                
<!--                <tr>
                    <td width="10%" class="label">Edit Photo</td>
                    <td colspan="3">
                        <label>  
                             <input type="file" id="main-input">
                                    <h4 id="fake-btn">
                            <span style="margin-top:10px; margin-bottom:10px; "> Choose File</span>
                            </h4>
                        </label>
                    </td>
                </tr>   -->
               
                <tr>
                    <td class="label">&nbsp;</td>
                </tr>

                <tr>
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label">
                        <label>
                            <input class="commitbutton" type="submit" alt="register" name="button2" id="button2" value="                 Submit                    " />
                        </label>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>