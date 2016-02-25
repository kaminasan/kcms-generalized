/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.uploadutils;

import javax.servlet.http.Part;

/**
 *
 * @author Blacksteath
 */
public interface PartExtractor {
    public String saveTitleImageToDisk(Part partToSave, String path);
}
