package com.mango.cs_408_project;

import java.util.HashMap;
import java.util.Map;

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
    public String semester; //make sure this is added
    public String profComment;
    public String course; //make sure this is added
    public String userId;

    //Likes Fields
    public Map<String, Boolean> likes = new HashMap<>();
    public int likesCount = 0;
    public String key;
    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setCourseName(String courseName) {
        this.course = courseName;
    }

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

    public void setSemesterTaken(String semester) {
        this.semester = semester;
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
