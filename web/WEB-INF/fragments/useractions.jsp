<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="useractions">
    <div id="headings">
        <h2><img src="${pageContext.request.contextPath}/images/Register-512.png" alt="" width="25" height="22" /> 
            <a href="${pageContext.request.contextPath}/user/profile">Welcome ${sessionuser.nickname}</a>
        </h2>
    </div>
    <!--<div id="login">-->
    <div id="loginform">
        <form action="${pageContext.request.contextPath}/user/logout" method="POST">
            <div class="formblock">
                <text>Click below to logout: </text>
                <input type="submit" name="logout" id="button" value="Logout"/>
            </div>
            <!--<div class="clear">&nbsp;</div>-->
            <!--                <p>
                                <input name="" type="checkbox" value="" />
                                Remember me on this computer | Forgot password ? </p>-->
        </form>
    </div>
    <!--</div>-->
</div>