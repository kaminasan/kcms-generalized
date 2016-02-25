<%-- 
    Document   : aboutmesegment
    Created on : Oct 11, 2015, 3:06:45 PM
    Author     : Blacksteath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<article class="col span_8">
	
                    <header id="postHeader" class="row"><h1>Bake Trade</h1></header>
                    <section id="postContent" class="row">
                        
                        <header id="postName" class="row">About Bake Trade</header>
                        <br>
                        <img id="aboutPicture" class="" src="/BakeTradeImages/SiteImages/profileshot.jpg" alt="Profile Picture of Owner">
                        <p>Hello,
                            <b>
                            <c:if test="${not empty user.userName}">${user.userName}</c:if>
                            <c:if test="${empty user.userName}">Guest</c:if>
                            </b>
                            <br>
                            <br> Bake Trade was established in 2015, starting out as a blog,  as a way to share various recipes across the world, and between cultures. 
                            <br>
                            <br>
                                While still in the infant stages of development, we at Bake Trade believe that the process is just as important as the finished Product. 
                                <br>
                                <br>
                                We hope you enjoy your time here, and make sure to Bake Trade away!
                            <br>
                            &copy;2015 baketrade.com</p>
                        
                    </section>
                    
                    
		</article>