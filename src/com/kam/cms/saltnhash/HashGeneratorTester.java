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
public class HashGeneratorTester {
    public static void main(String[] args) {
       HashGenerator hashGen1 = HashGenerator.getInstance();
       HashGenerator hashGen2 = HashGenerator.getInstance();
       System.out.println("Hash Gen 1 and 2 are the same object:" + hashGen1.equals(hashGen2));
       System.out.println("Tester Finished");
        
        
    }
}
