<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="search">
    <div class="tab">
        <h2>Send a new message</h2>
    </div>
    <div class="container">
        <!--<form id="message_form" action="${pageContext.request.contextPath}/user/message" method="GET">-->
        <table class="search_form" style="width:100%; border:none;">
            <tr>
                <td width="10%" class="label">Title</td>
                <td colspan="3">
                    <label>
                        <input type="text" name="Title" id="title" class="text longfield" value="${Title}" />
                    </label>
<!--            <c:forEach items="${errorMap.Title}" var="obj">
                <p class="fielderror">${obj}</p>
            </c:forEach>-->
            </td>
            </tr>
            <tr>
                <td width="10%" class="label">Message</td>
                <td colspan ="3"> <textarea name="Message" cols="40" rows="5"></textarea> </td>
<!--            <c:forEach items="${errorMap.photo}" var="obj">
                <p class="fielderror">${obj}</p>
            </c:forEach>-->

            </tr>
           
            <tr>
                <td class="label">&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2" class="label">
                    <label>
                        <!--<input type="image" src="$ {pageContext.request.contextPath}/images/searchbtn.gif" alt="register" name="button2" id="button2" value="Submit" />-->
                        <input class="commitbutton" type="submit" alt="register" name="button2" id="button2" value="                 Submit                    " />
                    </label>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>