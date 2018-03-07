<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="search">
    <div class="tab">
        <h2>Login</h2>
    </div>
    <div class="container">
        <form id="register_form" action="${pageContext.request.contextPath}/user/login" method="POST">
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
                <c:if test="${not empty errorMap.account}">
                    <tr>
                        <td width="10%" class="label">Suggestion</td>
                        <td colspan="3" name="account" id="account" >

                            <c:forEach items="${errorMap.account}" var="obj">
                                <p class="fielderror">${obj}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
            </table>
            <div class="wrapper">
                <input class="button" type="submit" alt="login" name="login" id="login" value="Login" />
            </div>
        </form>
    </div>
</div>