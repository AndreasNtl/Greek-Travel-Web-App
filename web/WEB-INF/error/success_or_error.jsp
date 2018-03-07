<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<head>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>Success Notification Boxes</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" media="all" href="style.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>

    <div id="w">
        <div id="content1">
            <!-- Icons source http://dribbble.com/shots/913555-Flat-Web-Elements -->

            <div class="notify successbox">
                <h1>Success!</h1>
                <span class="alerticon"><img src="http://s22.postimg.org/i5iji9hv1/check.png" alt="checkmark" /></span>
                    <c:if test="${not empty submitRate}">
                    <br>
                    <p>You have succesfully rated your booking!</p>
                    <p>The Lannisters send their regards!</p>
                    <br/>
                </c:if> 
                <c:if test="${not empty createRoom}">
                    <p>You have succesfully created a Room !</p>
                    <p>Check "My Listings" to view your room's details !</p>
                </c:if> 
                <c:if test="${not empty photo}">
                    <p>You have succesfully deleted a Photo !</p>
                    <p>The Lannisters send their regards!</p>
                </c:if> 
                <c:if test="${not empty message}">
                    <p>You have succesfully deleted a Message !</p>
                    <p>The Lannisters send their regards!</p>
                </c:if> 
            </div>

            <!--      <div class="notify errorbox">
                    <h1>Warning!</h1>
                    <span class="alerticon"><img src="http://s22.postimg.org/ulf9c0b71/error.png" alt="error" /></span>
                    <p>You did not set the proper return e-mail address. Please fill out the fields and then submit the form.</p>
      </div>
            -->

        </div><!-- @end #content -->
    </div><!-- @end #w -->

</body>
</html>