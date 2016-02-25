<%-- 
    Document   : headersection
    Created on : Oct 7, 2015, 11:03:34 PM
    Author     : Blacksteath
--%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<header role="banner" class="row background_ocean">
    
    <div id="titleContainer" class="row "><img src="/BakeTradeImages/SiteImages/frostinglogo.svg" id="headerLogo" alt="Bake Trade Header"></div>
</header>
	  <div id="toplinks" class="row  background_pink">
              <div id="navdiv" class="col span_8">  
        <ul id ="navlist" class="list_nav">
            <li><a href="/">Home</a></li>
            <li><a href="/about">About</a></li>
        </ul>
                  </div>    
         
              <div id="welcome" class="col span_4">
                  <ul class="list_nav"><li>Welcome,<c:choose>
                      <c:when test="${not empty user.userName}">${user.userName}!</c:when>                  
                      <c:otherwise>Guest!</c:otherwise>
                      </c:choose>
                      </li>
                      
                      <c:if test="${user.userLevel >= 4}">    <%--Allow access to the Admin page if userLevel Above or Equal to 4 --%>
                          <li><b><a href="${path}/administration">ADMIN</a></b></li>
                          </c:if>
                      
                      
                      <li>
                      <c:choose> <%-- Test If we need a login or logout --%>
                      <c:when test="${not empty user.userName}"><a href="/logout">Logout</a></c:when>                  
                      <c:otherwise><a href="/login">Login</a></c:otherwise>
                      </c:choose>
                     
                      </li>
                      </ul>
              </div>
    </div>