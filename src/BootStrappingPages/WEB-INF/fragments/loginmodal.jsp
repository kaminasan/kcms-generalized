<%-- 
    Document   : loginmodal
    Created on : Mar 11, 2016, 3:22:55 PM
    Author     : KaminaSan <www.kaminasan.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="loginModal" role="dialog">
    
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">Login</h2>
                <button class="close" data-dismiss="modal">X</button>
            </div>
            <div class="modal-body">
                <form>
                <div class="form-group">
                Username:<input class="form-control" type="text" required>
                Password:<input class="form-control" type="password" required>
                <button class="btn btn-primary " type="submit" data-dismiss="modal">Submit</button>
            </div>
                </form>
        </div>
            <div class="modal-footer">
                <button class="btn btn-danger" data-dismiss="modal">CLOSE</button>
            </div>
            
    </div>
</div>