package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by manasigoel on 3/2/17.
 */

public class ProfReviewsDisplay extends  AppCompatActivity{

    private ListView prof_mListView;
    private static ProfCustomAdapter prof_adapter;
    String user_input;
    Button prof_addReview;

    TextView name_text;


    private final ArrayList<ProfReview> prof_reviews = new ArrayList<ProfReview>();
    private ArrayList<ProfReview> new_display = new ArrayList<ProfReview>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_review_list);
        user_input = getIntent().getStringExtra("user_input");

        name_text = (TextView) findViewById(R.id.reviews_name_text);
        name_text.setText("Reviews for\n" + user_input);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    display_prof_review(user_input.toUpperCase());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //display_prof_review(user_input.toUpperCase());


        prof_addReview = (Button) findViewById(R.id.professor_info_addReview_2);

        // SORTING
        Spinner dropdown = (Spinner)findViewById(R.id.sort_menu);
        String[] items = new String[]{"Oldest to newest", "Newest to oldest", "Rating (high to low)", "Rating (low to high)", "Helpfulness"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if(prof_reviews.size() !=0) {
                    //NOTE: prof_reviews should ALWAYS be ordered oldest to newest
                    // Oldest to newest
                    if(position == 0) {
                        new_display = (ArrayList<ProfReview>) prof_reviews.clone();
                    }
                    if(position == 1) {
                        //Newest to oldest
                        new_display = (ArrayList<ProfReview>) prof_reviews.clone();
                        Collections.reverse(new_display);
                    }
                    if(position==2) {
                        // Rating - high to low
                        new_display = (ArrayList<ProfReview>) prof_reviews.clone();
                        Collections.sort(new_display, new Comparator<ProfReview>() {
                            @Override public int compare(ProfReview p1, ProfReview p2) {
                                return (int)(p2.rating*2) - (int)(p1.rating*2); // Ascending
                            }

                        });
                    }
                    if(position ==3) {
                        // Rating - low to high
                        new_display = (ArrayList<ProfReview>) prof_reviews.clone();
                        Collections.sort(new_display, new Comparator<ProfReview>() {
                            @Override public int compare(ProfReview p1, ProfReview p2) {
                                return (int)(p1.rating*2) - (int)(p2.rating*2); // Ascending
                            }

                        });
                    }
                    if(position ==4) {
                            // Whatever you want to happen when the thrid item gets selected
                            // Helpfulness
                    }
                    //DISPLAY REVIEWS AGAIN (list is prof_reviews)
                    prof_mListView = (ListView) findViewById(R.id.prof_listView_reviews);
                    prof_adapter.notifyDataSetChanged();
                    prof_adapter = new ProfCustomAdapter(new_display, getApplicationContext());
                    prof_mListView.setAdapter(prof_adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Auto-generated method stub
            }
        });

        // ADD A REVIEW
        prof_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfReviewsDisplay.this, AddInstructorReview.class);
                i.putExtra("name", user_input);
                ProfReviewsDisplay.this.startActivity(i);
            }
        });

    }


    public void display_prof_review(String prof_name) throws InterruptedException {

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
