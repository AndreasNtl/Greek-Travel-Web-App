<head>
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
</head>

<body>
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

        <div id="content">
            <div id="home_main">                    
                <jsp:include page="/WEB-INF/fragments/account/login.jsp"/>                                        
            </div>
            <!--<div id="sidebar">-->                    
                <%--<jsp:include page="/WEB-INF/fragments/common/sidebar.jsp"/>--%>      
            <!--</div>-->
        </div> 
        <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
    </div>
</body>

