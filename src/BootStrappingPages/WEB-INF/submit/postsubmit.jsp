<%-- 
    Document   : postsubmit
    Created on : Nov 15, 2015, 6:21:22 PM
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
            
            <%-- here is where your page content will go --%>
   <article id="addPostContainer" class="container">
       <header id="addPostHeader" class="page-header"><c:if test="${action =='add'}">Add a Post to Bake Trade</c:if><c:if test="${action == 'edit'}">Edit your post</c:if></header>
    <c:if test="${not empty user && user.userLevel >= 4}"> 
    <div id="addPostWrapper" class="row">
        <form id="addPostForm" action="<c:if test="${action =='add'}">/addpost</c:if><c:if test="${action == 'edit'}">/editpost</c:if>" method="POST" enctype="multipart/form-data" role="form">
        <div class="form-group">
           
           <input id="mainTitleImage" type="file" name="mainTitleImage" accept="image/*" required>
        </div>
            <div class="form-group">
            <label id="postTitleLabel" for="postTitleInput">Post Title:</label>
            <input type="text" class="form-control" maxlength="255"  id="postTitleInput" name="postTitle" value="${post.postTitle}" placeholder="Post title! NO HTML" required>
        </div>
        <br>
        <div class="form-group">
            <label id="postSummaryLabel" class="" for="postSummaryTextArea">Post Summary</label>
            <textarea id="postSummaryTextArea" class="form-control" name="postSummary" placeholder="Insert Your Post Summary Here NO HTML"  rows="4" cols="50" required>${post.postSummary}</textarea>
        </div>
        <br>
        <div class="form-group">
            <jsp:include page="/WEB-INF/fragments/richtexteditor.jsp"/> <%-- RTE CONTROLLER --%>
            <label for="postContentTextArea" class="">Post Content:</label>
         
            <textarea id="postContentTextArea" class ="hidden" name="postContent" placeholder="Insert your Post MAIN CONTENT HERE using EDITOR or HTML" required>${post.postContent}</textarea>
            <textarea id="postContentNoHtml" class="hidden" name ="postContentNoHtml" >${post.postContentNoHtml}</textarea>
             <div id="iFrameDiv" class=""> 
             <iframe  id="textEditor"></iframe>
            </div>
        </div>
    
        <div class="form-group">
            <c:choose>
                <c:when test="${action =='add'}"> 
                    <label>Upload an Image for your post!</label>
                    <br>
                    <input id="fileUpload" type="file" name="titleImage" accept="image/*" required>
                     <br>
                     <button id="submitButton">Submit Post</button>
                </c:when>
                     <c:when test="${action =='edit'}"> 
                          <button id="submitButton">Submit EDITED Post</button>
                   
                </c:when>
                     
                     
            </c:choose>
           

       
        </div>
    </form>
            </div>
    </c:if>
        
</article> 


                    <%@include file="/WEB-INF/fragments/footer.jsp" %>
        </div> <!-- Container -->
         
        <%@include file="/WEB-INF/fragments/bodyscripts.jsp" %>
        <script src="scripts/richtextscript.js"></script>
    </body>
</html>

