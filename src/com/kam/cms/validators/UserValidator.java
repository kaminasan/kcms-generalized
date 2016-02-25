/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.validators;

import com.kam.cms.beans.UserBean;

/**
 *
 * @author Blacksteath
 */
public class UserValidator {
    public static boolean validatedPoster(UserBean userBean){
            boolean validUser = true; 
            if(userBean == null) validUser = false;
            else{
                try{
                    validUser = userBean.getUserLevel() >= 2;
                }
                catch(NullPointerException npe){
                    validUser = false;
                }
            }
             return validUser;
    }
       
}
