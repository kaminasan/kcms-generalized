/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.beans.UserBean;
import com.kam.cms.filecontrol.DirectoryCreator;
import com.kam.cms.filecontrol.DirectoryDeleter;
import com.kam.cms.filecontrol.PostDirectoryCreator;
import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Blacksteath
 */
public class DeletePostController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = null;
        PostDAO dao = new PostDAO();
        String postId = request.getParameter("postId");
        PostBean post = dao.getSpecificPost(postId);
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        
        if (postId == null || post== null) {
            request.setAttribute("errorMessage", "There was no post with the ID of" + postId + ".");
            view = ctx.getRequestDispatcher("/WEB-INF/error/error.jsp");
            view.forward(request, response);
            
        } else if ((user == null)) {
            request.setAttribute("errorMessage", "You must be logged in to delete a post.");
            view = ctx.getRequestDispatcher("/login");
            view.forward(request, response);
           
        } 
        
        else {
            
            boolean validDeleter = post.getPosterId().equals(user.getUserId()) || user.getUserLevel() >= 3;
            if (validDeleter) {
                boolean deleted = dao.deletePost(post.getPostId().toString());
                if (deleted) {
                    System.out.println("Now deleting:" + DirectoryCreator.BASEPATH + PostDirectoryCreator.POSTDIRECTORY + post.getPostId());
                    DirectoryDeleter.deleteFolder(new File(DirectoryCreator.BASEPATH + PostDirectoryCreator.POSTDIRECTORY + post.getPostId()));
                    response.sendRedirect("/");
                } else {
                    view = ctx.getRequestDispatcher("/WEB-INF/error/error.jsp");
                    request.setAttribute("errorMessage", "ERROR: COULD NOT DELETE POST");
                    view.forward(request, response);
                }
            } 
            
            else {
                request.setAttribute("errorMessage", "You did something very naughty.");
                view = ctx.getRequestDispatcher("/WEB-INF/error/error.jsp");
                view.forward(request, response);
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
