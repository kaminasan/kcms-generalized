<%-- 
    Document   : error
    Created on : Oct 9, 2015, 8:40:04 PM
    Author     : KaminaSan <www.kaminasan.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Internal Server ERROR 500</title>
    </head>
    <body>
        <h1>OH MAN </h1>
        <img src="/Images/youshouldnotbehere.jpg" alt="Error500"/>
        
        <h1>ERROR: ${errorMessage}</h1>
        <h4><a href="<c:url value="/"/>">Let's GO HOME</a></h4>
       
    </body>
</html>
