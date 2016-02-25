/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.beans.UserBean;
import com.kam.cms.filecontrol.ImageLister;
import com.kam.cms.validators.PostBeanValidator;
import com.kam.uploadutils.ImagePartExtractor;
import com.kam.uploadutils.PartExtractor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Blacksteath
 */
@MultipartConfig
public class AddPostController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = null;
        if(request.getSession().getAttribute("user") == null){
            System.out.println("We are sending the user to /login since no name was stored in session");
           
            view = request.getRequestDispatcher("/login");
            view.forward(request, response);
            
        }
        else{
            System.out.println("We are sending the user to /");
            view = request.getRequestDispatcher("/");
            view.forward(request, response);
        }
        
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = null;
         
        UserBean poster = (UserBean) request.getSession().getAttribute("user");
         if(poster == null){                                                           //We have a null user so we need one before we can Post, redirect to Login
             System.out.println("Error at addpost, no user. Redirecting to login");
             view= request.getRequestDispatcher("/login");
             request.setAttribute("errorMessage", "Please Login before Posting!");
             view.forward(request, response);
             System.out.println("We sent the response");
             
        }
         else{
         System.out.println("we made it past the redirect");
         Integer posterId = poster.getUserId();    //getPosterID from the request session
         PostBean newPost = new PostBean(request.getParameter("postTitle"), request.getParameter("postContent"), request.getParameter("postContentNoHtml"), request.getParameter("postSummary"),  posterId);
        boolean validPost = (PostBeanValidator.validatePost(newPost) && (request.getPart("titleImage").getSize() > 0));

        
        if(!validPost){
            request.setAttribute("errorMessage", "ERROR: You are missing a post parameter! Please fill out all parts of the post!");
            System.out.println("not a valid post!!!");
            view = ctx.getRequestDispatcher("/recent");
            view.forward(request, response);
        }
        else{
            
            PostDAO postDao = new PostDAO();
            Integer postId = postDao.insertPost(newPost);
            System.out.println("The post ID is coming back to the Add Post Controller as:" + postId);
            newPost.setPostId(postId);
            if(postId < 0 || postId == null){
     
                request.setAttribute("errorMessage", "ERROR:POST ADD FAILED");
                view = request.getRequestDispatcher("/WEB-INF/error/error.jsp");
                view.forward(request, response);
            }
            else{
                Part titleImage = request.getPart("titleImage");
                PartExtractor partEx = new ImagePartExtractor();
                partEx.saveTitleImageToDisk(titleImage, postId.toString());
                newPost.setPostImages(ImageLister.getImageNames(postId.toString()));
               newPost.setPosterName(poster.getUserName());
                request.setAttribute("post", newPost);
                
                view=request.getRequestDispatcher("/post?post="+postId.toString());
                view.forward(request, response);
            }
            
            
        }
        
        
    }
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
