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
                
                <div class="postRow row">
                <div class="col-lg-6">
                    <img src="Images/post1.JPG" class="img-responsive">
                </div>
                <div class="col-lg-6 ">${postSummary} This is a sample post summary to show<br>
                What exactly our post could look like when we put in a small summary next to it. 
                This is a sample post summary to show<br>
                What exactly our post could look like when we put in a small summary next to it. 
                This is a sample post summary to show<br>
                What exactly our post could look like when we put in a small summary next to it. 
                This is a sample post summary to show<br>
                What exactly our post could look like when we put in a small summary next to it. 
                </div>
                
            </div>
                <div class="postRow row">
                   <div class="col-lg-8"> <p>Here is the actual description of what happened on the day <br>${post.postContent}
                     Here is the actual description of what happened on the day <br>
                      Here is the actual description of what happened on the day <br>
                       Here is the actual description of what happened on the day <br>
                        Here is the actual description of what happened on the day <br>
                         Here is the actual description of what happened on the day <br>
                         </p>
                </div>
                     <div class="col-lg-4">
                         Categories: ${post.postCategories}
                         CATEGORY 1 / CATEGORY 2 / CATEGORY 3
                     </div>
                </div>
         <%@include file="/WEB-INF/fragments/footer.jsp" %>
     
         </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
     
    </body>
</html>
