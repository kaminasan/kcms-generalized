/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.validators;

/**
 *
 * @author Blacksteath
 */
public  class ParamValidator {
    
    public static boolean isEmpty(String testObject){
        return testObject == null || testObject.equals("");
    }
    
    public static boolean isANumber(String testString){
        try{
            Integer.parseInt(testString);
        }
        catch(NumberFormatException exc){
            return false;
        }
        return true;
    }
    
    public static boolean isValidPostId(String testParam){
        return !isEmpty(testParam) && isANumber(testParam);
    }
}
