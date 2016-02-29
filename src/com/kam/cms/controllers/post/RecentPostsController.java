/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.cms.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
@WebServlet(name="RecentPostsController", urlPatterns = {"/post/recent"})
public class RecentPostsController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      
         List<PostBean> postList = null;
         
         RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/recent-posts.jsp");
         PostDAO  dao = new PostDAO();
        
             postList = dao.getRecentPostBeanList(0,5);
           request.setAttribute("postList", postList);
        view.forward(request, response);    
        
    }
        
         
         
        
    

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This Servlet/Controller lists the most 5 recent posts on the website. ";
    }// </editor-fold>
 
   
}
