package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by manasigoel on 3/2/17.
 */

public class ProfReviewsDisplay extends  AppCompatActivity{

    private ListView prof_mListView;
    private float rating = 0;
    private float counter = 0;
    private static ProfCustomAdapter prof_adapter;
    String user_input;
    Button prof_addReview;

    TextView name_text;


    private final ArrayList<ProfReview> prof_reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_review_list);
        user_input = getIntent().getStringExtra("user_input");
        Log.d("manasi reviews display", user_input);

        name_text = (TextView) findViewById(R.id.reviews_name_text);
        name_text.setText("Reviews for " + user_input);

        display_prof_review(user_input.toUpperCase());

        prof_addReview = (Button) findViewById(R.id.professor_info_addReview_2);

        prof_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfReviewsDisplay.this, AddInstructorReview.class);
                i.putExtra("name", user_input);
                ProfReviewsDisplay.this.startActivity(i);
            }
        });

    }


    public void display_prof_review(String prof_name) {

        final RatingBar stars = (RatingBar) findViewById(R.id.instructor_rating);

        prof_mListView = (ListView) findViewById(R.id.prof_listView_reviews); //Checks to see if the strong from database goes in
        final DatabaseReference ref = profInfo.child(prof_name);


        //Allows the scrollview to be disabled when scrolling through the list View
        prof_mListView.setOnTouchListener(new ListView.OnTouchListener() {
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
                    ProfReview instructor = child.getValue(ProfReview.class); // <-- do . at end here to specify which child
                    prof_reviews.add(instructor);
                    counter++;
                }
                //Set statistics here
                prof_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        prof_adapter = new ProfCustomAdapter(prof_reviews, getApplicationContext());
        prof_mListView.setAdapter(prof_adapter);

    }

}
