/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kam.cms.controllers.login;

import com.kam.cms.SQL.DAO.UserDAO;
import com.kam.cms.beans.UserBean;
import com.kam.cms.validators.ParamValidator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
@WebServlet(name="ModalLoginController", urlPatterns={"/modal-login"})
public class ModalLoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ServletContext ctx = request.getServletContext(); 
        RequestDispatcher view = null;
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         UserDAO dao = null;
         UserBean user = null;
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");
        PrintWriter writer = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        response.setStatus(401);
        System.out.println("UserName: " + userName + " UserPass: " + userPass);
        if(ParamValidator.isEmpty(userName) || ParamValidator.isEmpty(userPass)){
            writer.print("Error, missing parameters. Please Fill them in");
            return ;
        }
        else{
           dao = new UserDAO();
          user = dao.getSpecificUser(userName, userPass);
          
           if(user == null){
               writer.print("Error! User Name or Password Incorrect.");
               return;
           }
           
           request.getSession().setAttribute("user", user);
           response.setStatus(200);
           response.setContentType("text/plain");
           response.getWriter().print("Logged in user: " + user.toString());
        
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
