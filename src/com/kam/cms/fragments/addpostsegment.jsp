<%-- 
    Document   : addpost
    Created on : Oct 17, 2015, 2:58:34 PM
    Author     : Blacksteath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article id="addPostContainer" class="container col span_9">
    <header id="addPostHeader" class="row">Add a Post to Bake Trade</header>
    <br>
    <form id="addPostForm" action="/addpost" method="POST" enctype="multipart/form-data" class="span_9">
        <div class="row"> <label id="postTitleLabel">Post Title:</label><input type="text" maxlength="255"  name="postTitle" value="" placeholder="Post title! NO HTML" required>
        </div>
        <br>
        <div class="row">
            <label id="postSummaryLabel" for="postSummaryTextArea">Post Summary</label>
            <br>
            <textarea id="postSummaryTextArea" name="postSummary" placeholder="Insert Your Post Summary Here NO HTML" rows="4" cols="50" required></textarea>
        </div>
        <br>
        <div class="row">
            <jsp:include page="/WEB-INF/fragments/richtexteditor.jsp"/> <%-- RTE CONTROLLER --%>
            <label>Post Content:</label>
         
            <textarea id="postContentTextArea" name="postContent" placeholder="Insert your Post MAIN CONTENT HERE using EDITOR or HTML" required></textarea>
            <textarea id="postContentNoHtml" name ="postContentNoHtml" ></textarea>
             <br>
            
        </div>
             <iframe  id="textEditor"></iframe>
      

        <br>

        <label>Upload a title Image for your post!</label>
        <br>
        <input id="fileUpload" type="file" name="titleImage" accept="image/*" />
        <br>
       
        <button id="submitButton">Submit Post</button>
    </form>

</article>
