<%-- 
    Document   : register
    Created on : May 12, 2016, 3:34:37 PM
    Author     : KaminaSan <www.kaminasan.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="/WEB-INF/headsection.jsp" %>
        <title>Insert Title Here</title>
    </head>
    <body>
        
         <%@include file="/WEB-INF/fragments/navsection.jsp" %>
            <div class="container-fluid"> 
        <%--Content Goes HERE! --%>
        <%--Content Ends Here! --%>
            <div class="row title-row">
            <h1 class="text-center">Register</h1>
            </div>
            
            <div class="row" id="registrationSection">
                <div class="col-lg-6"> <%-- This will be the 8 part long row for the reg form --%>
                    <div class="col-lg-12">
                        <form id="registrationForm" method="GET" action="/register">
                            <div class="form-group">
                                <label for="firstNameInput">First Name</label>
                                <input class="form-control" type="text" placeholder="First Name" name="userFirstName" id="firstNameInput">
                  
                            </div>
                            <div class="formlastNameInput">
                                 <label for="lastNameInput">Last Name</label>
                                <input class="form-control" type="text" placeholder="Last Name" name="userLastName" id="lastNameInput">
                  
                            </div>
                            <div class="form-group">
                                <label for="UserNameInput">Username:</label>
                                <input class="form-control" type="text" placeholder="Username" name="userName" id="userName">
                  
                            </div>
                            <div class="form-group">
                                <label for="EmailInput">Email Address:</label>
                                <input class="form-control" type="email" placeholder="Email Address" name="userEmail" id="userEmail">
                            </div>
                             <div class="form-group">
                                <label for="userPass">Password</label>
                                <input class="form-control" type="password" name="userPass" placeholder="password" id="userPass">
                                <label for="userPassConfirm">Confirm Password</label>
                                <input class="form-control" type="password" name="userPassConfirm" placeholder="confirm password" id="userPassConfirm">
                            </div>
                            <button type="submit" >Submit Registration Form</button>
                        </form>
                        
                    </div>
                    
                </div>
                <div class="col-lg-4"></div> 
            </div>
            
         <%@include file="/WEB-INF/fragments/footer.jsp" %>
          <%@include file="/WEB-INF/fragments/loginmodal.jsp"%>
         </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.min.js"></script>
         <script src="js/Controllers.js"></script>
     
    </body>
</html>