/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.SQL.DAO;

import java.util.List;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class PostDaoTester {

    public static void main(String[] args) {
        PostDAO dao = new PostDAO();

        List<Integer> testSearchList = dao.getSearchResultsList("cook");
        System.out.println(testSearchList);

    }
}
