/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.validators;

import com.kam.cms.beans.PostBean;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class PostBeanValidator {
    
    public static boolean validatePost(PostBean post){
        boolean validPost= true;
        if(post.getPostTitle() == null || 
           post.getPostTitle().equals("")||
           post.getPostSummary() == null ||
           post.getPostSummary().equals("")||
           post.getPostContent() == null||
           post.getPostContent().equals("")){
            validPost=false;
        }
       return validPost;
    }
    
    public static boolean validateRecipe(PostBean post){
        if(post == null){
            return false;
        }
        boolean validPost= true;
        if(post.getPostTitle() == null || 
           post.getPostTitle().equals("")||
           post.getPostSummary() == null ||
           post.getPostSummary().equals("")){
            validPost=false;
        }
       return validPost;
    }
}
