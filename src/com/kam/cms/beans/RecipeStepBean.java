/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kam.cms.beans;

/**
 *
 * @author Blacksteath
 */
public class RecipeStepBean {
    private int stepId;
    private int parentid;
    private int stepNumber;
    private String stepSummary;
public RecipeStepBean(){
    
}
    public RecipeStepBean(int stepId, int parentid, int stepNumber, String stepSummary) {
        this.stepId = stepId;
        this.parentid = parentid;
        this.stepNumber = stepNumber;
        this.stepSummary = stepSummary;
    }

    public RecipeStepBean(int parentid, int stepNumber, String stepSummary) {
        this.parentid = parentid;
        this.stepNumber = stepNumber;
        this.stepSummary = stepSummary;
    }

    public String getStepSummary() {
        return stepSummary;
    }

    public void setStepSummary(String stepSummary) {
        this.stepSummary = stepSummary;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public String toString() {
        return "RecipeStepBean{" + "stepId=" + stepId + ", parentid=" + parentid + ", stepNumber=" + stepNumber + ", stepSummary=" + stepSummary + '}';
    }
    
    
}
