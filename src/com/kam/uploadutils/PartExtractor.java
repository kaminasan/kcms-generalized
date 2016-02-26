/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.uploadutils;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.Part;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public interface PartExtractor {
    public String savePartToDisk(Part partToSave, String postIdWithDirectoryToSave, boolean overWrite);
    public  List<Part> getPartsByParamName(Collection<Part> partCollection, String parameterName);
}
