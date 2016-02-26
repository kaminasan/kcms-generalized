/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.beans;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class CategoryBean {
    private String categoryName;
    private int categoryId;
    
    public CategoryBean(){
        
    }
    
    public CategoryBean(int categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    
    @Override
    public String toString(){
      return   "Category:" + this.getCategoryName() + " CategoryId: " + this.getCategoryId();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
