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
public class DirectoryDeleter {
    public static boolean deleteFolder(File folderToDelete){
        if(!folderToDelete.exists()) return false;  
        File[] files;
        files = folderToDelete.listFiles();    //get all files in this directory 
        
        for(File fileToDelete: files){
            
        if(fileToDelete.isFile()){
            fileToDelete.delete();   //delete all files
        }
        else{
            
            DirectoryDeleter.deleteFolder(fileToDelete);   //if file is directory, recursively delete it
            
        }
    }   
        folderToDelete.delete();   //when all clear, delete this folder
        return true;
    }
    
  
}
