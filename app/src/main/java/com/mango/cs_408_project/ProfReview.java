package com.mango.cs_408_project;

/**
 * Created by manasigoel on 2/26/17.
 */

public class ProfReview {
    public String profName;
    public boolean prof;
    public float rating;
    public int seekU;
    public int seekV;
    public boolean helpSession;
    public boolean extraCredit;
    public int toughness;
    public boolean electronics;
    public String profComment;

    public void setProfName(String courseName) {
        this.profName = courseName;
    }

    public void setProf(Boolean prof) {
        this.prof = prof;
    }

    public void setSeekU(int seekU) {
        this.seekU = seekU;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setHelpSession(boolean helpSession) {
        this.helpSession = helpSession;
    }

    public void setSeekV(int seekV) {
        this.seekV = seekV;
    }

    public void setExtraCredit(boolean extraCredit) {
        this.extraCredit = extraCredit;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public void setElectronics(boolean electronics) {
        this.electronics = electronics;
    }


    public void setProfComment(String profComment) {
        this.profComment = profComment;
    }
}
