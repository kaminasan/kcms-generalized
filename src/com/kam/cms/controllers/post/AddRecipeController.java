/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.beans.RecipeBean;
import com.kam.cms.beans.UserBean;

import com.kam.cms.filecontrol.DirectoryCreatorFactory;
import com.kam.cms.validators.PostBeanValidator;
import com.kam.cms.validators.UserValidator;
import com.kam.uploadutils.ImagePartExtractor;
import java.io.IOException;
import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Blacksteath
 */
@WebServlet(name = "AddRecipeController", urlPatterns = {"/addrecipe"})
@MultipartConfig(maxRequestSize = 1024 * 1024 * 15)
public class AddRecipeController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/submit/recipe");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //we are losing the encoding on the incoming request: TO DO: Create a filter that does this automatically.
        RequestDispatcher view = request.getRequestDispatcher("/login");
        String recipeTitle = request.getParameter("recipeTitle");
        String recipeSummary = request.getParameter("recipeSummary");
        String recipeIngredients = request.getParameter("recipeIngredients");

        String[] recipeSteps = request.getParameter("recipeStepList[]").split("\n");
        System.out.println("The Recipe Step List is as follows: " + Arrays.toString(recipeSteps));
        String[] recipeCategories = request.getParameterValues("postCategory[]");
        Collection<Part> partCollection = request.getParts(); //get ALL the parts
        List<Part> imagePartList = ImagePartExtractor.getImagePartsByParamName(partCollection, "extraImages[]"); //the file wil have multiple images with the same partname
        Part mainTitleImage = request.getPart("mainTitleImage");
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        /**
         * Check for valid User Use UserValidator
         */
        if (!UserValidator.validatedPoster(user)) {
            request.setAttribute("errorMessage", "Error: Invalid User/Permissions. Please fully Login/Register.");
            view.forward(request, response);
        } else {
            PostBean post = new RecipeBean(recipeTitle, recipeIngredients, recipeSummary, user.getUserId());

            if (PostBeanValidator.validateRecipe(post)) {           //check for a valid post if valid, insert
                PostDAO dao = new PostDAO();
                Integer postId = dao.insertPost(post);
                if (postId < 0) {         //error, Id is less than 0, problem inserting
                    request.setAttribute("errorMessage", "Error, Could not add post!");
                    view.forward(request, response);

                } else {
                    boolean insertedStepsSuccess = dao.insertRecipeSteps(recipeSteps, postId.toString());
                    boolean insertedPostCategories = dao.insertPostCategories(postId.toString(), recipeCategories); //add in post categories
                    if (insertedStepsSuccess && insertedPostCategories) {
                        //Create Post Folder
                        ImagePartExtractor ex = new ImagePartExtractor();
                        String directoryCreated = DirectoryCreatorFactory.getDirectoryCreator("post").createDirectory(postId.toString(), true);
                        if (directoryCreated != null) {
                            System.out.println("This post's directory is: " + directoryCreated);
                        } else {
                            request.setAttribute("errorMessage", "Error, Could not create Post Directory.");
                            view = request.getRequestDispatcher("/WEB-INF/error/error.jsp");
                            view.forward(request, response);
                            dao.deletePost(postId.toString());
                            return;
                        }

                        String returnedPath = ex.saveTitleImageToDisk(mainTitleImage, postId.toString());
                        if (returnedPath == null) {        //Error adding in titleImage Forward to error and delete post.
                            request.setAttribute("errorMessage", "Error, Could not add title image");
                            view = request.getRequestDispatcher("/WEB-INF/error/error.jsp");
                            view.forward(request, response);
                            dao.deletePost(postId.toString());
                            return;
                        }
                        System.out.println("Our Extra Images array is coming back as: " + imagePartList);
                        for (Part extraImage : imagePartList) {
                            ex.savePartToDisk(extraImage, postId.toString() + "\\extraImages", false);
                        }
                        System.out.println("Success!");
                        view = request.getRequestDispatcher("/post?post=" + postId);
                        view.forward(request, response);
                    } else {
                        dao.deletePost(postId.toString());
                        request.setAttribute("errorMessage", "Error: could not insert post Steps.");
                        view = request.getRequestDispatcher("/WEB-INF/error/error.jsp");
                        view.forward(request, response);
                    }
                }

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
