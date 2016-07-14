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
public class SaltGenerator {
    private static SaltGenerator generator = null;
    
    
   public static SaltGenerator getGenerator(){
        if(generator == null){
            generator = new SaltGenerator();
        }
        return generator;
    }
   
   
}
