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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class Login extends HttpServlet {
   
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/userlogin");
        String referer = request.getHeader("referer");      //get the referer in case we don't have a place to go back to
        String backPage = (String)request.getAttribute("backPage");  //if there is a designated backplace go there
        System.out.println(referer);
        System.out.println(backPage);
        
        if(backPage == null){
            backPage = referer;
        }
       
        if(request.getSession().getAttribute("user") == null){
            request.setAttribute("backPage", backPage);
         view.forward(request, response);
    }
        else{
            request.setAttribute("errorMessage", "You shouldn't have done that.");
            view= request.getRequestDispatcher("/");
            view.forward(request, response);
        }
    } 

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/userlogin");
        UserDAO dao = new UserDAO();
        String backPage = request.getParameter("backPage");
        String userPass = request.getParameter("userPass");
        String userName = request.getParameter("userName");
        System.out.println("Now attempting to call getSpecificUser with username: " + userName + "and password: " + userPass);
        //Leaving out salt and hash related files on github for security purposes
        boolean userExists = dao.userInDatabase(userName, userPass);
        System.out.println("UserExists: " +userExists);
        
        if(!userExists){
            System.out.println("Attempting to forward user to " + "/userlogin");
            request.setAttribute("errorMessage", "Error, username or password INCORRECT");
            request.setAttribute("backPage",backPage);
            view.forward(request, response);
        }
        else{
         
        UserBean returnUser = dao.getSpecificUser(userName,userPass);
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
