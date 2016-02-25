/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.CategoryBean;
import com.kam.cms.beans.UserBean;
import com.kam.cms.validators.UserValidator;
import java.io.IOException;
import java.util.List;
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
public class SubmitRecipeController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = ctx.getRequestDispatcher("/login");
        UserBean user = (UserBean)request.getSession().getAttribute("user");
        List<CategoryBean> categoryList = new PostDAO().getCategoryList();
        if(user == null){
            request.setAttribute("errorMessage","Please Login or Register before Posting");
            view.forward(request, response);
        }
        else if(!UserValidator.validatedPoster(user)){
            view = ctx.getRequestDispatcher("/WEB-INF/error/error.jsp");
            request.setAttribute("errorMessage", "Error: You do not have permission to post. Have you validated your account?");
            view.forward(request, response);
        }
        else{
            view = ctx.getRequestDispatcher("/WEB-INF/submit/recipesubmit.jsp");
           request.setAttribute("categoryList", categoryList);
            view.forward(request, response);
        }
        
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
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
