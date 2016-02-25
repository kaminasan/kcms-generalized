/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Blacksteath
 */
public class RecipeBean extends PostBean {
    private List<RecipeStepBean> recipeSteps = new ArrayList<>();
    private String[] recipeIngredients = null;
     public RecipeBean(){
        
    }

    public RecipeBean(String postTitle, String postContent, String postSummary, Integer posterId) {
        super(postTitle, postContent, postSummary, posterId);
    }
    
   
    public RecipeBean(Integer postId, String postTitle,  String postSummary, Integer posterId, String posterName) {
       super(postId, postTitle, null , postSummary, posterId, posterName);
        
        
    }
    public RecipeBean(Integer postId, String postTitle,  String postSummary, Integer posterId, String posterName, List<RecipeStepBean> recipeSteps) {
       super(postId, postTitle, null , postSummary, posterId, posterName);
        this.setRecipeSteps(recipeSteps);
        
    }

    public RecipeBean(Integer postId, String postTitle, String postContent, String postSummary, Integer posterId, String posterName) {
        super(postId, postTitle, postContent, postSummary, posterId, posterName);
     
    }
    
    public RecipeBean(PostBean bean){
       super(bean.getPostId(), bean.getPostTitle(), bean.getPostContent(), bean.getPostSummary(), bean.getPosterId(), bean.getPosterName());
    }
    
    public List<RecipeStepBean> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<RecipeStepBean> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public String[] getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String[] recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
    
}
