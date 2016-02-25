/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.filecontrol;

import com.kam.cms.beans.PostBean;
import java.io.File;
import java.util.Arrays;

import java.util.List;

/**
 *
 * @author Blacksteath
 */
public class ImageLister {
    final static String BASEPATH = "D:\\BakeTradeUploads\\Images\\Posts\\";
    
    public static  String[] getImageNames(String postPath){ //insert the post Id
        
        String[] namelist = null;
        File imageDir = new File(BASEPATH+postPath);
        if(imageDir.isDirectory()){
            namelist = imageDir.list();
            
        }
        
      
        return namelist;
    } 
    
  
    
     public static void setImagesForPostList(List<PostBean> postList){
        String[] imageList;
        
        for(PostBean post : postList){
            imageList = ImageLister.getImageNames(post.getPostId().toString()+"\\mainTitleImage");
            if(imageList != null){
                post.setPostImages(imageList);
            }
        }
    }
}
