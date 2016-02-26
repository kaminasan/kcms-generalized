/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.cms.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.beans.UserBean;
import com.kam.cms.filecontrol.DirectoryCreator;
import com.kam.cms.filecontrol.DirectoryDeleter;
import com.kam.cms.filecontrol.ImageLister;
import com.kam.cms.filecontrol.PostDirectoryCreator;
import com.kam.cms.validators.PostBeanValidator;
import com.kam.cms.validators.UserValidator;
import com.kam.uploadutils.ImagePartExtractor;
import com.kam.uploadutils.PartExtractor;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
@MultipartConfig
@WebServlet(name = "AddPostController", urlPatterns = {"/post/add"})
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
            //display the Submit FORM
        }
        
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = null; 
        PostDAO postDao = null;
       Part titleImage =null;
       List<Part> extraImagesList = null;
       Collection<Part> partCollection = null;
        UserBean poster = (UserBean) request.getSession().getAttribute("user");
        boolean validUser = UserValidator.validatedPoster(poster);
         if(!validUser){                                                           //We have a null user so we need one before we can Post, redirect to Login
             System.out.println("Error at addpost, no user. Redirecting to login");
             view= request.getRequestDispatcher("/login");
             request.setAttribute("errorMessage", "Please Login before Posting!");
             view.forward(request, response);
             return;
             
        }
         else{
      
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
            
            postDao = new PostDAO();
            Integer postId = postDao.insertPost(newPost); //insert the post
            System.out.println("The post ID is coming back to the Add Post Controller as:" + postId);
            newPost.setPostId(postId);
            if(postId < 0){ //we could not insert the post, send an error back
     
                request.setAttribute("errorMessage", "ERROR:POST ADD FAILED");
                view = request.getRequestDispatcher("/WEB-INF/error/error.jsp");
                view.forward(request, response);
             
            }
            else{ //we inserted the post, let's do Image Processing
                ImagePartExtractor partEx = new ImagePartExtractor();
                titleImage = request.getPart("titleImage");
                partCollection = request.getParts();
                extraImagesList = partEx.getPartsByParamName(partCollection, "ExtraImages[]"); //get the correct parts by searching by name
                boolean saved =  partEx.saveAllPostImages(titleImage, extraImagesList, postId.toString()); //we saved all the images correctly
               if(saved){ //we saved everything, let's redirect the user to the new post.
               
               newPost.setPostImages(ImageLister.getImageNames(postId.toString()));
                newPost.setPosterName(poster.getUserName());
                request.setAttribute("post", newPost);
                
                view=request.getRequestDispatcher("/post/"+postId.toString()+"/");
                view.forward(request, response);
                      }
               else{ //our saving failed somewhere, let's delete all things that were put in
                   boolean deletedFromDatabase = postDao.deletePost(postId.toString());
                   if(deletedFromDatabase){
                       DirectoryDeleter.deleteFolder(new File(DirectoryCreator.BASEPATH + PostDirectoryCreator.POSTDIRECTORY + postId.toString()));
                   }
               }
                
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
