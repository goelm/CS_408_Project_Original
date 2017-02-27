package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.common.escape.Escaper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mango.cs_408_project.Search.user_input;


/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CourseDisplay extends AppCompatActivity {
    private ListView mListView;
    private float rating = 0;
    private float counter = 0;
    private static CustomAdapter adapter;

    public static boolean has_user_input = true;


    private final ArrayList<CourseReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        display_course_review(user_input.toUpperCase());
    }


    public void display_course_review(String course_name) {


        TextView courseName = (TextView) findViewById(R.id.textCourseName); //Set the Name of the Course here
        courseName.setText(course_name);

        final RatingBar stars = (RatingBar) findViewById(R.id.course_rating);

        mListView = (ListView) findViewById(R.id.listView); //Checks to see if the strong from database goes in
        final DatabaseReference ref = courseInfo.child(course_name);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child: children) {
                            CourseReview course = child.getValue(CourseReview.class); // <-- do . at end here to specify which child
                            reviews.add(course);
                            counter++;
                            rating += course.rating;//for stars
                        }
                        //Set statistics here
                        stars.setRating(rating/reviews.size());
                        adapter.notifyDataSetChanged();

                        has_user_input = true;

                        if (reviews.size() == 0) {
                            has_user_input = false;
                        }

                        if (!has_user_input) {
                            Intent i = new Intent(CourseDisplay.this, ProfDisplay.class);
                            CourseDisplay.this.startActivity(i);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        adapter = new CustomAdapter(reviews, getApplicationContext());
        mListView.setAdapter(adapter);

    }
}