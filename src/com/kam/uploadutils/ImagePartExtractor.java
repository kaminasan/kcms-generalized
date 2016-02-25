/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.uploadutils;


import com.kam.cms.ImageUtils.ImageUtils;
import com.kam.cms.filecontrol.DirectoryCreator;
import com.kam.cms.filecontrol.DirectoryCreatorFactory;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

/**
 *
 * @author Blacksteath
 */
public class ImagePartExtractor implements PartExtractor{
   

    @Override
    public String saveTitleImageToDisk(Part partToSave, String path) { //sets titleImage
        final int BUFFER_SIZE = 1024*8;   //8kb buffer
        String submittedFileName = partToSave.getSubmittedFileName();
        String filetype = submittedFileName.substring(submittedFileName.indexOf(".")).toLowerCase();
        DirectoryCreator dc = DirectoryCreatorFactory.getDirectoryCreator("post");
        String createdPath = dc.createDirectory(path +"\\mainTitleImage", true);
        if(createdPath == null) return createdPath;
        
        String newFilePath = createdPath +"titleimage"+ filetype;
        File newFile = new File(newFilePath);
        BufferedInputStream in = null;
        FileOutputStream out = null;
        
      try{
          in = new BufferedInputStream(partToSave.getInputStream(), BUFFER_SIZE);
          out= new FileOutputStream(newFile, false);
         BufferedImage img = ImageUtils.getBufferedImageFromInputStream(in);
         img = ImageUtils.resizeBufferedImage(img, 800, 600);
         ImageUtils.writeToOutputStream(img, filetype.substring(1), out);
         
      }
      catch(Exception e){
          
      }
      finally{
          try{
          if(in != null){
              
              in.close();
          }
        if(out != null) {
          
            out.close();
        }
      }
          catch(IOException ex){
              ex.printStackTrace();
          }
      }
       
       return newFilePath;
       
        
    }
    
     public String savePartToDisk(Part partToSave, String path, boolean overWrite) {  //keeps the submittedName
         String createdPath = null;
        final int BUFFER_SIZE = 1024*8;   //8kb buffer
        String submittedFileName = partToSave.getSubmittedFileName();
        System.out.println("The extra image part has a submitted name of:" + submittedFileName);
        String submittedFileNoExt = submittedFileName.substring(0, submittedFileName.indexOf("."));
        String filetype = submittedFileName.substring(submittedFileName.indexOf(".")).toLowerCase();
        DirectoryCreator dc = DirectoryCreatorFactory.getDirectoryCreator("post");
        if(overWrite){  //
             createdPath = dc.createDirectory(path,true);
        }
        else{
             createdPath = dc.createDirectory(path, false);
        }
        String newFilePath = createdPath + submittedFileNoExt + filetype;
        File newFile = new File(newFilePath);
        BufferedInputStream in = null;
    
        FileOutputStream out = null;
        
      try{
      
        in =  new BufferedInputStream(partToSave.getInputStream(), BUFFER_SIZE);
        out= new FileOutputStream(newFile, false);
       BufferedImage img = ImageUtils.getBufferedImageFromInputStream(in);
         img = ImageUtils.resizeBufferedImage(img, 800, 600);
         ImageUtils.writeToOutputStream(img, filetype.substring(1), out);
      
      }
      catch(Exception e){
          
      }
      finally{
          try{
          if(in != null) in.close();
        if(out != null) out.close();
      }
          catch(IOException ex){
              ex.printStackTrace();
          }
      }
       
       return newFilePath;
       
        
    }
    
    
    
    
    public static List<Part> getImagePartsByParamName(Collection<Part> partCollection, String parameterName){ //we need to get all the parts which contain images under one certain param name
        List<Part> partsList = new ArrayList<>();
        for(Part part: partCollection){     
            if(part.getName().equals(parameterName) && !(part.getSubmittedFileName().equals("")) && part.getSubmittedFileName() != null){ //we will for sure have ONE part, but it may be empty. ONLY add if it has a filename
            partsList.add(part);
            }
            
        }
        return partsList;
    }

    
    
    }
    

