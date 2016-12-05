<%-- 
    Document   : registration
    Created on : Nov 16, 2016, 11:17:21 PM
    Author     : KaminaSan <www.kaminasan.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <%@include file="/WEB-INF/fragments/htmlhead.jsp" %>
    </head>
    <body>
        <%@include file="/WEB-INF/fragments/socialintents.jsp" %>

        <%@include file="/WEB-INF/fragments/navbarsection.jsp" %>

        <div id="mainContainer" class="container-fluid"><!-- main container -->
            <%@include file="/WEB-INF/fragments/logosection.jsp" %> 
            
          
            <div id="formContainer" class="container">
            <div class="row" id="registrationSection">
                <div class="col-lg-6">
                    <h2 class="page-header">Become a member of Bake Trade!</h2>
                      <ul class="list-group" id="registrationList">
                        
                        <li class="list-group-item">Add your own recipes to the site for everyone to view!</li>
                        <li class="list-group-item">Receive and give likes to other members recipes!</li>
                        <li class="list-group-item"> Share your saved recipes with everyone you want!</li>
                        <li class="list-group-item">Have your recipe featured under the Featured Recipes category!</li>
                    </ul>
                    <%--<img class="img-responsive img-rounded" src = "${initParam["siteImages"]}/registrationDecoration.jpg" alt="registration decoration">  --%>
                  
                </div>
                <div class="col-lg-6"> <%-- This will be the 8 part long row for the reg form --%>
                    <div class="col-lg-12">
                        <h2 class="page-header">Sign Up!</h2>
                        <form id="registrationForm" class="" method="POST" action="/register">
                            <div class="form-group">
                                <label for="firstNameInput" class="control-label">First Name</label>
                                <input class="form-control" type="text" placeholder="First Name" name="userFirstName" id="firstNameInput" required>
                  
                            </div>
                            <div class="formlastNameInput" >
                                 <label for="lastNameInput" class="control-label">Last Name</label>
                                 <input class="form-control" type="text" placeholder="Last Name" name="userLastName" id="lastNameInput" required>
                  
                            </div>
                            <div class="form-group">
                                <label for="UserNameInput" class="control-label">Username:</label>
                                <input class="form-control" type="text" placeholder="Username" name="userName" id="userName" required>
                  
                            </div>
                            <div class="form-group">
                                <label for="EmailInput" class="control-label">Email Address:</label>
                                <input class="form-control" type="email" placeholder="Email Address" name="userEmail" id="userEmail" required="">
                            </div>
                           
                             <div class="form-group">
                                <label for="userPass" class="control-label">Password</label>
                                <input class="form-control" type="password" name="userPass" placeholder="Password" id="userPass" required>
                                <label for="userPassConfirm" class="control-label">Confirm Password</label>
                                <input class="form-control" type="password" name="userPassConfirm" placeholder="Confirm Password" id="userPassConfirm" required>
                            </div>
                             <div class="form-group ">
                                <label for="userCountry" class="control-label">Country</label>
                                <%@include file="/WEB-INF/fragments/countrylist.jsp" %> 
                            </div>
                            <button type="submit" class="btn-danger btn" >Sign Me Up!</button>
                        </form>
                        
                    </div>
                    
                </div>
               
            </div>
 </div>
                    <%@include file="/WEB-INF/fragments/footer.jsp" %>
        </div> <!-- Container -->
        
        <%@include file="/WEB-INF/fragments/bodyscripts.jsp" %>
    </body>
</html>

