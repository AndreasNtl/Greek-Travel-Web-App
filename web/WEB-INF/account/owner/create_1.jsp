<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
       <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDlU4thxK_Whhxd8QQrv-ihGJUJuG4wIfA&callback=initMap"
            type="text/javascript">
    </script>



<script src="${pageContext.request.contextPath}/js/map.js" type="text/javascript"></script>

</head>

  <body onLoad="initialize();">
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

        <div id="content">
            <div id="home_main" style="width: 100%;" >                    
                <jsp:include page="/WEB-INF/fragments/account/create_room.jsp"/>                                        
            </div>

            <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
        </div>
</body>

