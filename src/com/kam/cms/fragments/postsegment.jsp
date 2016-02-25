<%-- 
    Document   : postsegment
    Created on : Oct 9, 2015, 11:39:40 PM
    Author     : Blacksteath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="col span_8  ">
	
                    <header id="postHeader" class="row"><h1>Check this out!</h1></header>
                    <section id="postContent" class="row">
                        
                        <header id="postName" class="row">${post.postTitle}</header>
                        <br>
                        ${post.postSummary}
                        <img class="picture_content" src="/BakeTradeImages/Posts/${post.postId}/${post.postImages[0]}" alt="Picture of the Bentou">
                        
                            <br>
                         
                         ${post.postContent}
                        <br>
                        <b>Written By: ${post.posterName} </b>
                    </section>
                    
                    
</article>