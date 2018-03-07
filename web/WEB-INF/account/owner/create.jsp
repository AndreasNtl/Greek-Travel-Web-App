<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <head>
        <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDs8huntVctgfr9D2GmEOCH_3iVXI0s8Ys&callback=initMap"
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
            <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/pikaday.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/js/date.js" type = "text/javascript"></script>
    </body>
</html>
