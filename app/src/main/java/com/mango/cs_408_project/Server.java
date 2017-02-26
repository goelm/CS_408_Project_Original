package com.mango.cs_408_project;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Object;

/**
 * Created by manasigoel on 2/10/17.
 */
/*
    This class will contain functions to help communicate with Firebase
    This does not include authentication through Firebase (with Facebook)
 */
public class Server extends AppCompatActivity {
    private TextView testDisplay;
    private ListView mListView;
    final List<courseReview> reviews = new ArrayList<courseReview>();
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    int user_id = 123456;

    public Server(){

    }

    public void write_instructor_review(String instructor_name, String review){
        DatabaseReference inst = myRef.child("reviews").child("instructor").child(instructor_name).push();
        inst.setValue(Integer.toString(user_id)+ "," + review);
    }

    public void write_course_review(String course_name, courseReview review){
        DatabaseReference inst = myRef.child("reviews").child("course").child(course_name).push();
        inst.setValue(review);//Integer.toString(user_id)+ "," + review);
    }
}



