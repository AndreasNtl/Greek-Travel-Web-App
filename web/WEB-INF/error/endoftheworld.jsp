<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>
    <link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>


        <div id="content">
            <div id="main" style="width: 100%">
                <div >
                    <img src="${pageContext.request.contextPath}/images/atomic.jpg" alt="atomic"  style="width: 100%; height: 60%"/>
                </div>

                <div>
                    <ul class="listing">
                        <li> This is the end of the world the atomic bomb hit your pc</li>
                    </ul>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
    </div>
</body>