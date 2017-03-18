package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private ArrayList<CourseReview> new_display = new ArrayList<CourseReview>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Course Reviews");

        setContentView(R.layout.course_review_list);

        user_input = getIntent().getStringExtra("user_input");

        display_course_review(user_input.toUpperCase());

        courseInfo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    CourseReview course = child.getValue(CourseReview.class); // <-- do . at end here to specify which child
                    reviews.add(course);
                }
                adapter.notifyDataSetChanged();
                */
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                CourseReview course = dataSnapshot.getValue(CourseReview.class);
                course.setLikesCount(course.likesCount);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Spinner dropdown = (Spinner)findViewById(R.id.sort_menu_course);
        String[] items = new String[]{"Oldest to newest", "Newest to oldest", "Rating (high to low)", "Rating (low to high)", "Helpfulness (high to low)", "Helpfulness (low to high)"};
        ArrayAdapter<String> adapter_sort = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter_sort);
        adapter_sort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter_sort);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(reviews.size() !=0) {
                    //NOTE: reviews should ALWAYS be ordered oldest to newest
                    // Oldest to newest
                    if(position == 0) {
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                    }
                    if(position == 1) {
                        //Newest to oldest
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                        Collections.reverse(new_display);
                    }
                    if(position == 2) {
                        // Rating - high to low
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                        Collections.sort(new_display, new Comparator<CourseReview>() {
                            @Override public int compare(CourseReview c1, CourseReview c2) {
                                return (int)(c2.rating*2) - (int)(c1.rating*2); // Ascending
                            }

                        });
                    }
                    if(position == 3) {
                        // Rating - low to high
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                        Collections.sort(new_display, new Comparator<CourseReview>() {
                            @Override public int compare(CourseReview c1, CourseReview c2) {
                                return (int)(c1.rating*2) - (int)(c2.rating*2); // Ascending
                            }

                        });
                    }
                    if(position == 4) {
                        // Helpfulness - high to low
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                        Collections.sort(new_display, new Comparator<CourseReview>() {
                            @Override public int compare(CourseReview c1, CourseReview c2) {
                                return c2.likesCount - c1.likesCount; // Ascending
                            }

                        });
                    }
                    if(position == 5) {
                        // Helpfulness - low to high
                        new_display = (ArrayList<CourseReview>) reviews.clone();
                        Collections.sort(new_display, new Comparator<CourseReview>() {
                            @Override public int compare(CourseReview c1, CourseReview c2) {
                                return c1.likesCount - c2.likesCount; // Ascending
                            }

                        });
                    }
                    //DISPLAY REVIEWS AGAIN (list is reviews)
                    mListView = (ListView) findViewById(R.id.listView);
                    adapter.notifyDataSetChanged();
                    adapter = new CustomAdapter(new_display, getApplicationContext());
                    mListView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

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

        adapter = new CustomAdapter(reviews, CourseReviewsDisplay.this);
        mListView.setAdapter(adapter);
    }

}
