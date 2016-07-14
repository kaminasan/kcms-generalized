/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.saltnhash;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class HashGenerator {
    
   private static HashGenerator generatorInstance = new HashGenerator();
  private static final int LOADLEVEL = 12;
   
   private  String getHash(String stringToHash){
       
       String hashedString = BCrypt.hashpw(stringToHash, BCrypt.gensalt(LOADLEVEL));
       
       return hashedString; 
    }
   
   private String testMatch(String hashedPass, String plainTextPass){
       boolean matched= BCrypt.checkpw(plainTextPass, hashedPass);
       String result = (matched) ? "Password Matched": "No match";
       return result;   
   }
   
   public static HashGenerator getInstance(){
       if(generatorInstance == null ) {
           generatorInstance = new HashGenerator();
       }
       return generatorInstance;
   }
}
