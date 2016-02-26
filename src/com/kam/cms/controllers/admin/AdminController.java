/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.admin;

import com.kam.cms.SQL.DAO.UserDAO;
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
 * @author Blacksteath
 */
public class AdminController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        UserDAO myDAO = new UserDAO();
        ServletContext ctx= request.getServletContext();
        String requestURI = request.getRequestURI();
        String pathInfo= request.getPathInfo();
        System.out.println(requestURI + ": RequestURI"); //this should print out everything after the context up until parameters
        System.out.println(pathInfo + ": Path Info"); //this should only catch the extra params
        UserBean currUser = (UserBean)request.getSession().getAttribute("user");
        RequestDispatcher view;
       if(currUser == null){
           request.setAttribute("errorMessage", "Not LOGGED IN");
           System.out.println(ctx.getContextPath());
           view = ctx.getRequestDispatcher("/login");
           view.forward(request, response);
       }
       
       else if(currUser.getUserLevel() >= 4 && currUser.getUserLevel().equals(myDAO.getSpecificUser(currUser.getUserName(), currUser.getUserPass()).getUserLevel())){
           view = ctx.getRequestDispatcher("/admindashboard");
           view.forward(request, response);
        }
       else{
           response.sendRedirect("http://www.google.com");
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
