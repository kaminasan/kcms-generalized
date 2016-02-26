/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.filecontrol;

import java.io.File;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class DirectoryCreatorTester {
    public static void main(String[] args) {
      
        DirectoryCreator post_creator = DirectoryCreatorFactory.getDirectoryCreator(DirectoryCreatorFactory.POSTDIR_CREATOR);
       String created = post_creator.createDirectory("33",true);
    }
}
