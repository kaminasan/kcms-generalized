<%-- 
    Document   : error404
    Created on : Oct 8, 2015, 12:06:00 AM
    Author     : Blacksteath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 NOT FOUND</title>
    </head>
    <body>
        <h1>SORRY, the page you were looking for was NOT FOUND!</h1>
        <img src="/Images/error.jpg" alt="hagrid"/>
        <h1><a href="<c:url value="/"/>">Go back home!</a></h1>
       
    </body>
</html>
