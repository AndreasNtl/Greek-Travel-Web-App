<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="useractions">
    <div id="headings">

        <h2><img src="${pageContext.request.contextPath}/images/Register-512.png" alt="" width="25" height="22" /> 
            <a href="${pageContext.request.contextPath}/pages?page=register">Register new account</a></h2>
    </div>
    <div id="login">
        <p><strong>Already registered ?</strong> Login below</p>
        <div id="loginform">
            <form action="${pageContext.request.contextPath}/user/login" method="POST">
                <div class="formblock">
                    <label>Username</label>
                    <input name="nickname" type="text" class="textfields" />
                </div>
                <div class="formblock">
                    <label>Password</label>
                    <input name="password" type="password" class="textfields"/>
                </div>
                <!--<div class="formblock">-->
                    <input type="submit" name="login" id="button" value="Login" style="margin-top: 15px;"/>
                <!--</div>-->
                <!--<div class="clear">&nbsp;</div>-->
<!--                <p>
                    <input name="" type="checkbox" value="" />
                    Remember me on this computer | Forgot password ? </p>-->
            </form>
        </div>
    </div>
</div>