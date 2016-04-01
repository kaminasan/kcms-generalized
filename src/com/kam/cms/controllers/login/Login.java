/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.login;





import com.kam.cms.SQL.DAO.UserDAO;
import com.kam.cms.beans.UserBean;
import java.io.IOException;
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
@WebServlet(name="LoginController", urlPatterns={"/login"})
public class Login extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/");
        String backPage = (String) request.getAttribute("backPage");
        if(backPage == null || backPage.isEmpty() ){
            backPage = request.getHeader("referer");
        }
         if( request.getSession().getAttribute("user") == null){
            request.setAttribute("errorMessage", "You must login or register first! Please click the login button");
            request.setAttribute("backPage", backPage);
            view = request.getRequestDispatcher(backPage);
            view.forward(request, response);
         }
           
        
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/userlogin");
        UserDAO dao = new UserDAO();
        String userPass = request.getParameter("userName");
        String userName = request.getParameter("userPass");
        String backPage = (String) request.getAttribute("backPage");
        if(backPage == null || backPage.isEmpty() ){
            backPage = request.getHeader("referer");
            System.out.println("Bacpage is: " + backPage);
        }
        System.out.println("Now attempting to call getSpecificUser with username: " + userName + "and password: " + userPass);
        boolean userExists = (userPass.equals("test") && userName.equals("test"));
        
        System.out.println("UserExists: " +userExists);
        if(!userExists){
            view = request.getRequestDispatcher(backPage);
            request.setAttribute("ErrorMessage", "Error, User does not exist.");
            request.setAttribute("backPage", backPage);
            view.forward(request, response);
        }
        else{
         
        //UserBean returnUser = dao.getSpecificUser(userName,userPass);
            UserBean returnUser = new UserBean("kaminasan", "test", "testLast", "blah`cm.com", "blll", 3);
        request.getSession().setAttribute("user", returnUser);
        System.out.println("User successfully logged in. Sending back to: " + backPage);
        response.sendRedirect(backPage);
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
