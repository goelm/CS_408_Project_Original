package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by manasigoel on 3/2/17.
 */

public class CourseReviewsDisplay extends AppCompatActivity{

    private ListView mListView;
    private static CustomAdapter adapter;

    String user_input;

    Button course_addReview;

    private final ArrayList<CourseReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_review_list);

        user_input = getIntent().getStringExtra("user_input");

        display_course_review(user_input.toUpperCase());

    }


    public void display_course_review(String course_name) {

        course_addReview = (Button) findViewById(R.id.add_review_course_reviews);

        TextView courseName = (TextView) findViewById(R.id.course_name_reviews); //Set the Name of the Course here
        courseName.setText("Reviews for " + course_name);

        mListView = (ListView) findViewById(R.id.listView); //Checks to see if the strong from database goes in
        final DatabaseReference ref = courseInfo.child(course_name);

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


        course_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseReviewsDisplay.this, AddCourseReview.class);
                i.putExtra("user_input", user_input);
                CourseReviewsDisplay.this.startActivity(i);
            }
        });


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    CourseReview course = child.getValue(CourseReview.class); // <-- do . at end here to specify which child
                    reviews.add(course);
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
