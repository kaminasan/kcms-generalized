/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.filecontrol.ImageLister;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Blacksteath
 */
public class RecentPostsController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         int postsPerPage = 5;
         List<PostBean> postList = null;
         String page = request.getPathInfo();
          System.out.println("Requested Path: " + page);
         RequestDispatcher view = request.getRequestDispatcher("/recentposts");
         PostDAO  dao = new PostDAO();
         if(page == null){
             postList = dao.getRecentPostBeanList(0,5);
               ImageLister.setImagesForPostList(postList);
               view.forward(request, response);
              
         }
         else{
         System.out.println("THIS IS NOT WORKING!");
        
        request.setAttribute("postList", postList);
        view.forward(request, response);    
         }
        
         
         
        
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
