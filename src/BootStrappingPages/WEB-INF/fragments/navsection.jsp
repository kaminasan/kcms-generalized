
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav id="navBar" class="navbar navbar-inverse navbar-fixed-top" role="navigation"> <!-- Define our NavBar -->
            <div id="navContainer" class="container">
                <div id="navHeader" class="navbar-header">
                    <button type="button" id="dropdown" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="/" id="homeLink" class="navbar-brand">Your Title Here</a>
                </div>
                <div id="collapsibleContent" class="navbar-collapse collapse"><!-- If we don't put collapse here, it unfolds immediately on resize if we don't have navbar-collapse, the data-target cannot pick it up -->
                    <ul id ="linksList" class="nav navbar-nav navbar-right">
                        <li><a href="#" ><span class="glyphicon  glyphicon-blackboard"></span> About</a></li>
                        <li><a href="<c:url value="/contact"></c:url>"><span class="glyphicon glyphicon-envelope"></span> Contact</a></li>
                        <%--Insert Your Various Links Here --%>
                            <li><c:choose>
                                <c:when test="${not empty user}"><a href="<c:url value="/logout"></c:url>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></c:when>
                                <c:otherwise><a href="<c:url value="/login"></c:url>"> <span class="glyphicon glyphicon-log-in"></span> Login</a></c:otherwise>
                                </c:choose></li>
                    </ul>
                </div>
            </div>

        </nav>