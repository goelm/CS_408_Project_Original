package com.mango.cs_408_project;

/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CourseReview {
    public String courseName;
    public String instructorName;
    public String semester;
    public String courseDescr;
    public float rating;
    public int seekU;
    public int seekV;
    public boolean helpSession;
    public boolean extraCredit;
    public int toughness;
    public boolean electronics;
    public boolean textBook;
    public String courseComment;
    public int upVote;
    public int downVote;


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setCourseDescr(String courseDescr) {
        this.courseDescr = courseDescr;
    }

    public void setSemesterTaken(String semester) {
        this.semester = semester;
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

    public void setTextBook(boolean textBook) {
        this.textBook = textBook;
    }

    public void setCourseComment(String courseComment) {
        this.courseComment = courseComment;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }
}
