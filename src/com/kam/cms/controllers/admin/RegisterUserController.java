/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.controllers.admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.kam.cms.beans.UserBean;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="RegistrationControler", urlPatterns={"/register"})
public class RegisterUserController extends HttpServlet {
   
   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    UserBean user = (UserBean)request.getSession().getAttribute("user");
    ServletContext ctx = request.getServletContext();
    RequestDispatcher view = ctx.getRequestDispatcher("/");
    if(user != null){                    //If user is not null he is already registered, send him back home
        view.forward(request, response);
    }
    else{ 
       view = ctx.getRequestDispatcher("/WEB-INF/register.jsp");  //if user is null, send him to 
       view.forward(request, response);
    }
   
   
     
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        RequestDispatcher view = ctx.getRequestDispatcher("/WEB-INF/register.jsp");
        UserBean newUser = null;
        String firstName = request.getParameter("userFirstName");
        String lastName = request.getParameter("userLastName");
        String userName = request.getParameter("userName");
        String emailAddress = request.getParameter("userEmail");
        String userPass = request.getParameter("userPass");
        String userPassConfirm = request.getParameter("userPassConfirm");
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
