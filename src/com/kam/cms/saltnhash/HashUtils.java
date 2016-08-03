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
public class HashUtils {
    private final static HashGenerator hashGen = HashGenerator.getInstance();
    
    public static String getHashedPass(String plainTextPass){
        return hashGen.getHash(plainTextPass);
    }
}
