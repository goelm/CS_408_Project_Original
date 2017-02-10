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


    public void write_instructor_review(String name, String review){
        myRef.child("reviews").child("instructor").child(name).setValue(review);
    }
}
