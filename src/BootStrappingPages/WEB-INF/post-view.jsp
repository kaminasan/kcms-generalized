<%-- 
    Document   : post-view
    Created on : Feb 29, 2016, 4:28:36 PM
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
            <div class="container">
                <div class="row title-row">
                    <header><h1 class="text-center"> TEST POST TITLE ${post.postTitle}</h1></header>
                </div>
            
         <%@include file="/WEB-INF/fragments/footer.jsp" %>
     
         </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
     
    </body>
</html>
