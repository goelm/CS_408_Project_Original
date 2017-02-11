package com.mango.cs_408_project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by manasigoel on 2/10/17.
 */

/*
    This class will contain functions to help communicate with Firebase
    This does not include authentication through Firebase (with Facebook)
 */
public class server {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    int user_id = 123456;


    public void write_instructor_review(String instructor_name, String review){
        DatabaseReference inst = myRef.child("reviews").child("instructor").child(instructor_name).push();
        inst.setValue(Integer.toString(user_id)+ "," + review);
    }

    public void write_course_review(String course_name, String review){
        DatabaseReference inst = myRef.child("reviews").child("course").child(course_name).push();
        inst.setValue(Integer.toString(user_id)+ "," + review);
    }
}
