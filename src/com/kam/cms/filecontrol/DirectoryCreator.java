/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.filecontrol;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public interface DirectoryCreator {
    public String BASEPATH = "D:\\BakeTradeUploads\\"; //This give us the basepath of D:/BakeTradeUploads/
  
     public String createDirectory(String path , boolean delete);
}
