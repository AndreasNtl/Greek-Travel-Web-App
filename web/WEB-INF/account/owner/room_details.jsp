<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
    <link href="${pageContext.request.contextPath}/css/viewProfile.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />

</head>

<body>
    <div id="wrap">   

        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>
        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>
        <div id="content">

            <%--<jsp:include page="/WEB-INF/fragments/topcategorieslink.jsp"/>--%>
            <div id="main">
                <jsp:include page="/WEB-INF/fragments/account/room_details.jsp"/> 
                <jsp:include page="/WEB-INF/fragments/single_item/messages.jsp"/> 
            </div>

        </div>   
        <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>      
    </div>
</body>
