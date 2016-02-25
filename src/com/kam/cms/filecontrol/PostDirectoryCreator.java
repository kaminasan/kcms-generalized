/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.filecontrol;

import java.io.File;

/**
 *
 * @author Blacksteath
 */
public class PostDirectoryCreator implements DirectoryCreator {
     final static public String POSTDIRECTORY = "Images\\Posts\\";
    
    @Override
    public String createDirectory(String path , boolean deleteIfExists) {
       boolean delete = deleteIfExists;
       String returnPath = null;
       String fullPath = DirectoryCreator.BASEPATH + POSTDIRECTORY + "\\" + path;
       File fullPathFile = new File(fullPath);
       boolean exists = fullPathFile.exists();
       if(deleteIfExists && exists){
           
           DirectoryDeleter.deleteFolder(fullPathFile);
           exists = fullPathFile.mkdir();
           
       }
       else if(!exists){
           exists = fullPathFile.mkdir();
       }
            
       if(exists){
       returnPath = fullPath + "\\";
       }
        
        return returnPath;
    }

    
    }

  

