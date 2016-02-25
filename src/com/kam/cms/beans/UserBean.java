/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.beans;

import java.io.Serializable;

/**
 *
 * @author Blacksteath
 */
public class UserBean implements Serializable {
    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPass;
    private Integer userLevel;
    private PostBean[] userPosts;
    public UserBean(){
    
    }

    public UserBean(String userName, String firstName, String lastName, String userEmail, String userPass, Integer userLevel) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userLevel = userLevel;
    }

    public UserBean(Integer userId, String userName, String firstName, String lastName, String userEmail, String userPass, Integer userLevel) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userLevel = userLevel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public PostBean[] getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(PostBean[] userPosts) {
        this.userPosts = userPosts;
    }

    @Override
    public String toString() {
        return "UserBean{" + "userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", userEmail=" + userEmail + ", userPass=" + userPass + ", userLevel=" + userLevel + ", userPosts=" + userPosts + '}';
    }
    
    
    
}
