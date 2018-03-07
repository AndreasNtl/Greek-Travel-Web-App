<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
    </head>

    <body>
        <div id="wrap">            
            <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

            <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

            <div id="content">
                <div id="home_main">                    
                    <jsp:include page="/WEB-INF/fragments/advanced_search.jsp"/>                                        
                </div>

<!--                <div id="sidebar">
                    <%--<jsp:include page="/WEB-INF/fragments/common/sidebar.jsp"/>--%>                                              
                </div> -->

            </div> 
            <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
        </div>
        <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pikaday.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/date.js" type = "text/javascript"></script>
    </body>
</html>
