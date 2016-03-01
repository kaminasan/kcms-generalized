<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : contact
    Created on : Feb 18, 2016, 12:10:29 PM
    Author     : teacher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="/WEB-INF/headsection.jsp" %>
        <title>Contact Minamata High</title>
    </head>
    <body>
         <%@include file="/WEB-INF/fragments/navsection.jsp" %>
         <div class="container-fluid">
             <div class="row title-row" id="contactTitleRow">
                 <h1 class="text-center" >Contact Us</h1>         
             </div>
             
             <div class="row" id="contactSection">
                 <div class="col-lg-6" id="contactFormDiv">
                       <form>
                           <div class="form-group">
                               <label for="contactEmail">Contact Email: </label>
                               <input type="text" class="form-control" placeholder="Input Contact Email Here: " id="contactEmail">
                         </div>
                           <div class="form-group">
                               <label for="nameText">Contact Name:</label>
                               <input class="form-control" type="text" placeholder="Contact Name Here:" id="nameText">
                               </div>
                                <div class="form-group">
                                  <label for="messageText">Inquiry Message:</label>
                                    <textarea  class="form-control"  id="messageText" rows="10">Please type your inquiry here.</textarea>
                             
                                </div>
                           <button type="submit" class="btn btn-success pull-right"><b>Press Me to Send</b></button>
                           
                           </form>
                           </div>
                          
                               
                     
                   <div class="col-lg-6 "><p>We like to keep in touch with people all over the globe, as well as create and maintain relationships
                     here at Minamata High School. </p>
                 <p>Should you need to get in touch with an English speaker, just let us know in the form below. All we need is your contact info, and we will
                   respond as soon as we can.</p></div>
                 </div>
               
               
                 <%@include file="/WEB-INF/fragments/footer.jsp" %>
             </div>
             
             
             
             
             
         
         
        <%--Content Goes HERE! --%>
        <%--Content Ends Here! --%>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script>
            $(window).on("scroll", function(event){
                var body = document.body;
                var $navbar = $("#navBar");
                if(body.scrollTop > 50){
                    $navbar.css("background-color", "rgba(55, 175, 23, .9)");
                }
                
                else{
                    $navbar.css("background-color", "rgba(55, 175, 23, .2)");
                    
                }
                
               
                
            });
        </script>
      
    </body>
</html>
