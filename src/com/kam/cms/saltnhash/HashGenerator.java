/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.saltnhash;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class HashGenerator {
    
   private static HashGenerator generatorInstance = new HashGenerator();
  
   
   private static String getHash(String stringToHash){
       
       
       return null; 
    }
   
   public static HashGenerator getInstance(){
       if(generatorInstance == null ) {
           generatorInstance = new HashGenerator();
       }
       return generatorInstance;
   }
}
