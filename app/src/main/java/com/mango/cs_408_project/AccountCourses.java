package com.mango.cs_408_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by elvinutheman on 3/16/17.
 */

public class AccountCourses extends AppCompatActivity{
    private ListView mListView;
    private static CustomAdapter adapter;
    Button course_addReview;

    private final ArrayList<CourseReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    FacebookLogin f = new FacebookLogin();
    String uid = f.userID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Your Course Reviews");

        setContentView(R.layout.account_course_reviews);

        display_course_reviews();

        courseInfo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                CourseReview course = dataSnapshot.getValue(CourseReview.class);
                course.setLikesCount(course.likesCount);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                CourseReview course = dataSnapshot.getValue(CourseReview.class);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void display_course_reviews() {

        course_addReview = (Button) findViewById(R.id.add_review_course_reviews);

        mListView = (ListView) findViewById(R.id.accountCourseListView); //Checks to see if the strong from database goes in
        final DatabaseReference ref = courseInfo;


        //Allows the scrollview to be disabled when scrolling through the list View
        mListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    //System.out.println(child.getKey());
                    Iterable<DataSnapshot> nextChildSet = child.getChildren();

                    for (DataSnapshot nextChild: nextChildSet) {
                        CourseReview course = nextChild.getValue(CourseReview.class); // <-- do . at end here to specify which child
                        if (course.getUserId() != null) {
                            if (course.userId.equals(uid)) {
                                reviews.add(course);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new CustomAdapter(reviews, getApplicationContext());
        mListView.setAdapter(adapter);
    }
}
