package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

//import static com.mango.cs_408_project.CourseDisplay.has_user_input;
import static com.mango.cs_408_project.Search.user_input;


/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class ProfDisplay extends AppCompatActivity {
    private ListView prof_mListView;
    private float rating = 0;
    private float counter = 0;
    private static ProfCustomAdapter prof_adapter;
    public static boolean has_user_input = true;


    Button prof_addReview;


    private final ArrayList<ProfReview> prof_reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_info);

        display_prof_review(user_input.toUpperCase());

        prof_addReview = (Button) findViewById(R.id.professor_info_addReview);


        prof_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfDisplay.this, AddInstructorReview.class);
                ProfDisplay.this.startActivity(i);
            }
        });

    }






    public void display_prof_review(String prof_name) {


        TextView profName = (TextView) findViewById(R.id.textProfName); //Set the Name of the Course here
        profName.setText(prof_name);

        final RatingBar stars = (RatingBar) findViewById(R.id.instructor_rating);

        prof_mListView = (ListView) findViewById(R.id.prof_listView); //Checks to see if the strong from database goes in
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
                    rating += instructor.rating;//for stars
                }
                //Set statistics here
                stars.setRating(rating/prof_reviews.size());
                prof_adapter.notifyDataSetChanged();

                has_user_input = true;

                if (prof_reviews.size() == 0) {
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
        });
        prof_adapter = new ProfCustomAdapter(prof_reviews, getApplicationContext());
        prof_mListView.setAdapter(prof_adapter);

    }
}