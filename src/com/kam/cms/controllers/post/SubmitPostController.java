/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.cms.beans.UserBean;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class SubmitPostController extends HttpServlet {    
   
   
     //Activated after the submit button is clicked.
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = null;
        String backPage = request.getParameter("backPage"); //We will add this dynamically from the addpost  link
        UserBean user= (UserBean)request.getSession().getAttribute("user");
        boolean validPoster = ((user != null) && user.getUserLevel() >= 2 );
        if(!validPoster){
            view = ctx.getRequestDispatcher("/login");
            request.setAttribute("backPage", backPage);
            request.setAttribute("errorMessage", "You do not have permission to post. Please Login or Register");
            view.forward(request, response);
        }
        else{
             request.setAttribute("action", "add");
            view = ctx.getRequestDispatcher("/WEB-INF/submit/postsubmit.jsp");
            view.forward(request, response);
        }
        
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
