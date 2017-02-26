package com.mango.cs_408_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CourseDisplay extends AppCompatActivity {
    private ListView mListView;
    private float rating = 0;
    private float counter = 0;


    final List<CourseReview> reviews = new ArrayList<CourseReview>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        display_course_review("HIST 371");

    }

    public void display_course_review(String course_name) {

        TextView courseName = (TextView) findViewById(R.id.textCourseName); //Set the Name of the Course here
        courseName.setText(course_name);

        RatingBar stars = (RatingBar) findViewById(R.id.course_rating);

        mListView = (ListView) findViewById(R.id.listView); //Checks to see if the strong from database goes in
        DatabaseReference ref = courseInfo.child(course_name);

        final ArrayAdapter<CourseReview> arrayAdapter = new ArrayAdapter<CourseReview>(this, android.R.layout.simple_list_item_1, reviews);

        mListView.setAdapter(arrayAdapter);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child: children) {
                            CourseReview course = child.getValue(CourseReview.class); // <-- do . at end here to specify which child
                            reviews.add(course);
                            counter++;
                            rating += course.rating;
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        stars.setRating((rating / counter));
    }
}