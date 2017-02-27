package com.mango.cs_408_project;

import android.content.Intent;
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

import static com.mango.cs_408_project.CourseDisplay.has_user_input;
import static com.mango.cs_408_project.Search.user_input;

/**
 * Created by manasigoel on 2/27/17.
 */

public class ProfDisplay extends AppCompatActivity {
    private ListView mListView;
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
        display_instructor_review(user_input);
    }

    public void display_instructor_review(String instructor_name) {

        //TextView courseName = (TextView) findViewById(R.id.textInstructorName); //Set the Name of the Course here
        //courseName.setText(instructor_name);

        RatingBar stars = (RatingBar) findViewById(R.id.instructor_rating);

        mListView = (ListView) findViewById(R.id.listView); //Checks to see if the strong from database goes in
        DatabaseReference ref = profInfo.child(instructor_name);

        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child: children) {
                            ProfReview review = child.getValue(ProfReview.class); // <-- do . at end here to specify which child
                            reviews.add(review);
                            counter++;
                            rating += review.rating;
                        }
                        adapter.notifyDataSetChanged();

                        has_user_input = true;
                        if (reviews.size() == 0) {
                            has_user_input = false;
                        }

                        if (!has_user_input) {
                            Intent i = new Intent(ProfDisplay.this, Search.class);
                            ProfDisplay.this.startActivity(i);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
        adapter = new ProfCustomAdapter(reviews, getApplicationContext());
        mListView.setAdapter(adapter);

        stars.setRating((rating / counter));
    }
}
