
<head>
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
</head>

<body>
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

        <div id="content">
            <div id="home_main">                    
                <jsp:include page="/WEB-INF/fragments/login.jsp"/>                                        
            </div>

            <!--<div id="home_sidebar">-->                    
                <%--<jsp:include page="/WEB-INF/fragments/common/hot.jsp"/>--%>                    
            <!--</div>-->

            <%--<jsp:include page="/WEB-INF/fragments/topcategorieslink.jsp"/>--%>

            <div id="main">
                <jsp:include page="/WEB-INF/fragments/featured.jsp"/>                                              
            </div>

            <!--<div id="sidebar">-->
                <%--<jsp:include page="/WEB-INF/fragments/sidebar.jsp"/>--%>                                              
            <!--</div>-->

        </div> 
        <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
    </div>
</body>

