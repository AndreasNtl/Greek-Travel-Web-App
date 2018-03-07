<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page language="java" %>

<link href="${pageContext.request.contextPath}/css/viewProfile.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />
     
<head>
    <jsp:include page="/WEB-INF/fragments/common/head.jsp"/>

</head>

<body>
    <div id="wrap">            
        <jsp:include page="/WEB-INF/fragments/common/topbar.jsp"/>

        <jsp:include page="/WEB-INF/fragments/common/header.jsp"/>

        <div id="content">
            <div id="main">               
                <jsp:include page="/WEB-INF/account/rating/rating_details.jsp"/>     
           </div> 
                
            <!--<div id="sidebar">-->
                <%--<jsp:include page="/WEB-INF/fragments/common/sidebar.jsp"/>--%>                                              
            <!--</div>-->
        <jsp:include page="/WEB-INF/fragments/common/footer.jsp"/>
    </div>
</body>
