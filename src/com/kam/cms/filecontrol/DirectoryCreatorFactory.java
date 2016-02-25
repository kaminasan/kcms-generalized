/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.filecontrol;

/**
 *
 * @author Blacksteath
 */
public class DirectoryCreatorFactory {
   public static final String POSTDIR_CREATOR= "post";
    public static DirectoryCreator getDirectoryCreator(String type){
        DirectoryCreator creator = null;
        if(type.equalsIgnoreCase("post")){
            creator = new PostDirectoryCreator();
        }
        return creator;
    }
    
    
}
