/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blacksteath
 */
public class ImageUtilTester {
    public static void main(String[] args) {
        File testFile = new File("D:\\TestImageResize\\1398168244167.png");
        new ImageUtilTester().testUsingFile(testFile, 800, 600);
    }
    
    public void testUsingFile(File useFile, int newWidth, int newHeight){
        BufferedInputStream is = null;
         BufferedOutputStream os = null;
        try{
             is = new BufferedInputStream(new FileInputStream(useFile));
             BufferedImage img = ImageUtils.getBufferedImageFromInputStream(is);
             System.out.println("Height:" + img.getHeight());
             System.out.println("Width:" + img.getWidth());
             System.out.println("Resizing to:" + newWidth + "x" + newHeight);
             img = ImageUtils.resizeBufferedImage(img, newWidth, newHeight);
             System.out.println("New Dimensions:" + img.getWidth() + "x" + img.getHeight() );
             os = new BufferedOutputStream(new FileOutputStream(useFile));
             ImageUtils.writeToOutputStream(img, "png", os);
        }
        catch(FileNotFoundException fne){
            fne.printStackTrace();
        }
        
        finally{
            if(is != null && os != null) try {
                
                is.close();
                os.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ImageUtilTester.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
   
    }
}
