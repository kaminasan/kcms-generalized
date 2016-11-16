/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.registration;

import com.kam.cms.beans.UserBean;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
@WebServlet(name="RegistrationController", urlPatterns={"/register"})
public class RegistrationController extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session= request.getSession();
        UserBean user = (UserBean)session.getAttribute("user");
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/registration/registration.jsp");
       if(user != null){
           view = request.getRequestDispatcher("/");
           view.forward(request, response);
       }
       else{
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
