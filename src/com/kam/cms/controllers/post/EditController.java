/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;

import com.kam.cms.beans.RecipeBean;
import com.kam.cms.beans.RecipeStepBean;
import com.kam.cms.beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Blacksteath
 */
public class EditController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String postId = request.getParameter("postId");
        String postType = request.getParameter("postType");
        UserBean user = (UserBean) session.getAttribute("user");
         PostDAO dao = new PostDAO();
         PostBean testBean = dao.getSpecificPost(postId);
      
        if (user == null || testBean == null) {
            request.getRequestDispatcher("/login").forward(request, response);
            return;
        }
         PostBean postToEdit = new RecipeBean(testBean);
        if (!(user.getUserId().equals(postToEdit.getPosterId()) || user.getUserLevel() >= 3)) { //check to see the user's Id matches or is greater than 3
            System.out.println("User Level: " + user.getUserLevel() + " Access denied");
            request.getRequestDispatcher("/WEB-INF/error/error404.jsp").forward(request, response);

            return;
        }

        if (postType.equalsIgnoreCase("recipe")) {
            RecipeBean recipeBean = (RecipeBean) postToEdit;
            String[] ingredientsList = recipeBean.getPostContent().split("\n"); //We have saved the ingredients in postContent
            recipeBean.setRecipeIngredients(ingredientsList);
            recipeBean.setCategoryList(dao.getSpecificPostCategoriesList(postId)); //get the categories this post belongs to
            List<RecipeStepBean> stepBeanList = dao.getRecipeStepBeanList(postId);
            recipeBean.setRecipeSteps(stepBeanList);
            
            for (int i = 0; i<stepBeanList.size() -1 ; i++) {
                RecipeStepBean stepBean =   stepBeanList.get(i);
                String newBeanSummary = stepBean.getStepSummary() + "\n";
                stepBean.setStepSummary(newBeanSummary);
            }
            request.setAttribute("post", recipeBean);
            request.setAttribute("categoryList", dao.getCategoryList());

            request.getRequestDispatcher("/WEB-INF/editrecipe.jsp").forward(request, response);
        } else {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("We got an AJAX call!");
        //set the response to a success
        request.setCharacterEncoding("UTF-8"); //when dealing with editing, we need to make sure we are setting our stuff as UTF-8
        PrintWriter respWriter = response.getWriter();
        PostDAO dao = new PostDAO();
        HttpSession session = request.getSession();
        String postId = request.getParameter("postId");
        System.out.println("PostId is coming as:" + postId);
        if (postId == null) {
            respWriter.print("Sorry, that post was deleted, or does not exist.");
            response.setStatus(404);
            respWriter.close();
            return;
        }
        
        String editType = request.getParameter("editType");

        PostBean postToEdit = new RecipeBean(dao.getSpecificPost(postId));
        UserBean user = (UserBean) session.getAttribute("user");
        if (user == null) {
            response.setStatus(400);
            respWriter.print("ERROR: YOU ARE NOT LOGGED IN!");
            respWriter.close();
            
        } else if (!(user.getUserId().equals(postToEdit.getPosterId()) || user.getUserLevel() >= 3)) { //check to see the user's Id matches or is greater than 3
        response.setStatus(400);
            respWriter.print("ERROR: YOU DO NOT HAVE PERMISSION TO EDIT!");
            respWriter.close();
        } else {
             boolean updated = false;
            switch (editType) {

                case "recipeTitle":
                    System.out.println("We got a call from " + editType + " on BT Server");
                    String newRecipeTitle = request.getParameter(editType);
                     updated= dao.updatePostTitle(postId, newRecipeTitle);
                      if(updated)respWriter.print("Updated " + editType + " on BT Server");
               
                    respWriter.close();
                    break;

                case "recipeIngredients":
                    String newIngredientsList = request.getParameter(editType);
                    System.out.println(newIngredientsList);
                    
                    System.out.println("We got a call from " + editType + " on BT Server");
                     updated = dao.updatePostContent(postId, newIngredientsList);
                    if(updated) respWriter.print("Updated " + editType + " on BT Server");
                    respWriter.close();
                    break;

                case "postCategory[]":
                    String[] recipeCategories = request.getParameterValues("postCategory[]");
                    System.out.println(Arrays.toString(recipeCategories));
                    System.out.println("We got a call from " + editType + " on BT Server");
                    respWriter.print("Updated " + editType + " on BT Server");
                    respWriter.close();
                    break;

                case "recipeSummary":
                    String newRecipeSummary = request.getParameter(editType);
                    System.out.println(newRecipeSummary);
                    System.out.println("We got a call from " + editType + " on BT Server");
                    updated = dao.updatePostSummary(postId, newRecipeSummary);
                    if(updated)respWriter.print("Updated " + editType + " on BT Server");
                    respWriter.close();
                    break;

                case "recipeStepList[]":
                    String[] recipeSteps = request.getParameter("recipeStepList[]").split("\n");
                    System.out.println("New Steps: " + Arrays.toString(recipeSteps));
                    System.out.println("We got a call from " + editType + " on BT Server");
                    updated = dao.updateRecipeSteps(recipeSteps, postId);
                    if(updated)  respWriter.print("Updated " + editType + " on BT Server");
                    respWriter.close();
                    break;

                default:
                    System.out.println("We got a call from " + editType + " on BT Server");
                    response.setStatus(400);
                    respWriter.print("Sorry, your request was denied due to a malformed something");
                    break;

            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
