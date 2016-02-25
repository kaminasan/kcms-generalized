/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.ImageUtils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Blacksteath
 */
public class ImageUtils {
    
    public static BufferedImage getBufferedImageFromInputStream(InputStream input){
        BufferedImage myImg = null;
        
        try{
          myImg = ImageIO.read(input);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return myImg;
    }
    
    public static boolean writeToOutputStream(RenderedImage img, String format, OutputStream os){
        boolean success = false;
        try {
            ImageIO.write(img, format, os);
            success = true;
            
        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
       return success;
    }
    
    public static BufferedImage resizeBufferedImage(BufferedImage img, int desiredWidth, int desiredHeight){
        int width = img.getWidth();
        int height = img.getHeight();
        int newWidth = desiredWidth;
        int newHeight = desiredHeight;
        
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    
        
    }
}
