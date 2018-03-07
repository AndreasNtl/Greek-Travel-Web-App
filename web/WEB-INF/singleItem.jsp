<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <head>

        <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>

        <!--<link href="$ {pageContext.request.contextPath}/css/ajaxui.css" rel="stylesheet" type="text/css" />-->
    </head>
    <body>
        <div id="wrap">   

            <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>
            <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>
            <div id="content">

                <jsp:include page="/WEB-INF/fragments/topcategorieslink.jsp"/>
                <div id="main">
                    <jsp:include page="/WEB-INF/fragments/single_item/single_item_details.jsp"/>
                    <jsp:include page="/WEB-INF/fragments/single_item/midraw_detalis.jsp"/>
                    <jsp:include page="/WEB-INF/fragments/single_item/moredetails.jsp"/>
                    <jsp:include page="/WEB-INF/fragments/single_item/single_item_similary_item.jsp"/>
                </div>

                <!--<div id="sidebar">-->
                    <%--<jsp:include page="/WEB-INF/fragments/common/sidebar.jsp"/>--%>                                              
                <!--</div>-->


            </div>   
                <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>      
            </div>
    </body>
</html>
