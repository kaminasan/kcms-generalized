/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.filecontrol.ImageLister;
import com.kam.cms.validators.ParamValidator;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
@WebServlet(name="PostController", urlPatterns = {"/post/*"})
public class PostController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        PostDAO dao = new PostDAO();
        PostBean post = null;
        String postId = request.getParameter("post");
       
        RequestDispatcher view = sc.getRequestDispatcher("/WEB-INF/post.jsp");
        if(!ParamValidator.isValidPostId(postId) ){    //If we have an empty param
          request.setAttribute("errorMessage", "Error: That Post Does NOT exist. Please try Again");
          view = sc.getRequestDispatcher("/WEB-INF/error/error.jsp");
               view.forward(request, response);
    }
        else{                                      //we have a param, let's check the database
            post = dao.getSpecificPost(postId);
            if(post != null){                  //if the database is NOT empty, this will contain a value.
                 
                post.setPostImages(ImageLister.getImageNames(postId+"\\mainTitleImage"));        //get the images for it.
                post.setCategoryList(dao.getSpecificPostCategoriesList(postId));
                request.setAttribute("post", post);
          
            view.forward(request, response);
            }
            else {                                             
               request.setAttribute("errorMessage", "Error: That Post Does NOT exist. Please try Again");
               view = sc.getRequestDispatcher("/WEB-INF/error/error.jsp");
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
