<%-- 
    Document   : rightsidesegment
    Created on : Oct 11, 2015, 4:18:17 PM
    Author     : Blacksteath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="rightside" class="col span_2 ">
	
          
                   <br>Date is: ${date}
                   <br>${welcome}
                   <c:if test="${not empty user.userName}"><br>Username: ${user.userName}</c:if>
                   <c:if test="${not empty errorMessage}">ErrorMessage: ${errorMessage}</c:if>
                   <br>
	
		</section>