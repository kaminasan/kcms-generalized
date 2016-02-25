/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.controllers.post;

import com.kam.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import com.kam.cms.filecontrol.ImageLister;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Blacksteath
 */
public class RecentRecipesController extends HttpServlet {

    final public PostDAO postDao = new PostDAO();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PostDAO dao = new PostDAO();
        final int postsPerPage = 5;
        Integer totalPostCount = dao.getPostCount();
        Integer finalPage = totalPostCount / postsPerPage;
        if (totalPostCount % postsPerPage != 0) {
            finalPage++;
        }
        String page = request.getPathInfo();
        List<PostBean> postList = null;
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/recentrecipes.jsp");
        if (page == null) {
            response.sendRedirect("/recipes/recent/1");

        } else {
            String extraPath = page.substring(1); //get the info from the "/pagnumber" -> substring(1) = "pagenumber"
            String[] params = extraPath.split("/"); //if there are any other params, we split them up.

            int pageNumber = Integer.parseInt(params[0]); //We want to get the page number so we grab the first number in the array
            int offset = ((pageNumber - 1) * postsPerPage); //we don't want to start at page 0, since it looks strange to normal users, always do the page -1. Multiply current page by number of posts
            postList = dao.getRecentPostBeanList(offset, postsPerPage); //get the posts that correlate with this page, + however many posts per page
            int endPage = 2; //set the end page to 2, assuming we are on page 1.
            int startPage = 1;
            if (pageNumber > finalPage) { //make sure that our page number is within the correct range
                endPage = finalPage;
                System.out.println("Went over max page. Setting End page to:" + endPage);
                response.sendRedirect("/recipes/recent/" + endPage);
            } else {
                int remainingPostPages = finalPage - pageNumber; //subtract displayed posts and calculate how many pages left that we can display

                endPage = (remainingPostPages > 2) ? pageNumber + 2 : pageNumber + remainingPostPages; //if we have more than two pages left, we will display links up to current page + 2. If not, we add in all the rem.
                ImageLister.setImagesForPostList(postList); //set images

                if (pageNumber > 2) {
                    startPage = pageNumber - 2;
                }
                request.setAttribute("postList", postList);
                request.setAttribute("startPage", startPage);
                request.setAttribute("currentPage", pageNumber);
                request.setAttribute("endPage", endPage);
                view.forward(request, response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
