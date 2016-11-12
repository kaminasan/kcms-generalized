/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.SQL;

import com.kam.cms.SQL.DAO.PostDAO;
import com.kam.cms.beans.PostBean;
import java.util.List;

/**
 *
 * @author KaminaSan <www.kaminasan.com>
 */
public class SQLTester {
       
       public static void main(String[] args) {
        SQLTester tester = new SQLTester();
        tester.testgetAllPosts();
    }
    public boolean testgetSpecificPost(String postNumString){
       boolean worked = false;
        PostDAO dao = new PostDAO();
        PostBean testBean = dao.getSpecificPost("1");
        System.out.println(testBean.toString());
       return worked;
    }
    
    
     public boolean testgetAllPosts(){
       boolean worked = false;
        PostDAO dao = new PostDAO();
        List<PostBean> pbl;
        pbl = dao.getPostBeanList();
        for(PostBean post: pbl){
            System.out.println(post.toString());
        }
       return worked;
    }
    
    }
     
     
     
     

