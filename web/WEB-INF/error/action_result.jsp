<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head> 
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
</head>

<body>
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

        <div id="content">
            <div id="home_main">                                  
                <jsp:include page="/WEB-INF/error/success_or_error.jsp"/>                                         
            </div>
            <!--<div id="sidebar">-->
                <%--<jsp:include page="/WEB-INF/fragments/common/sidebar.jsp"/>--%>                                              
            <!--</div>-->
            <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
        </div>
    </body>

