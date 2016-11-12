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
       String firstPassword =  "test";
       String secondPassword = "test";
      
       String firstHash = hashGen1.getHash(firstPassword);
       String secondHash = hashGen2.getHash(secondPassword);
       System.out.println("First Hash: " + firstHash + "     " + "Second Hash: " + secondHash);
       System.out.println("First and Second Hash match? : " + firstHash.equals(secondHash));
       
       boolean firstPasswordMatchesHash = hashGen1.testPassMatch(firstPassword, secondHash);
       boolean secondPasswordMatchesHash = hashGen2.testPassMatch(secondPassword, firstHash);
       System.out.println("The First Password matches the first Hash: " + firstPasswordMatchesHash);
       System.out.println("The Second Password matches the second Hash: " + secondPasswordMatchesHash );
       System.out.println("Tester Finished");
        
        
    }
}
