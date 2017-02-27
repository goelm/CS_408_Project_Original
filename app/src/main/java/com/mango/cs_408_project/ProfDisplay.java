package com.mango.cs_408_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by manasigoel on 2/27/17.
 */

public class ProfDisplay extends AppCompatActivity {
    /*private ListView mListView;
    private float rating = 0;
    private float counter = 0;
    private static ProfCustomAdapter adapter;


    final ArrayList<ProfReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_info);
        display_instructor_review("KennyZheng");
    }

    public void display_instructor_review(String instructor_name) {

        TextView courseName = (TextView) findViewById(R.id.textCourseName); //Set the Name of the Course here
        courseName.setText(instructor_name);

        RatingBar stars = (RatingBar) findViewById(R.id.course_rating);

        mListView = (ListView) findViewById(R.id.listView); //Checks to see if the strong from database goes in
        DatabaseReference ref = profInfo.child(course_name);

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
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
        adapter = new CustomAdapter(reviews, getApplicationContext());
        mListView.setAdapter(adapter);

        stars.setRating((rating / counter));
    }*/
}
