package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class AccountProfs extends AppCompatActivity {
    private ListView prof_mListView;
    private static ProfCustomAdapter prof_adapter;

    Button prof_addReview;

    private final ArrayList<ProfReview> prof_reviews = new ArrayList<ProfReview>();
    private ArrayList<ProfReview> new_display = new ArrayList<ProfReview>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    FacebookLogin f = new FacebookLogin();
    String uid = f.userID();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Professor Reviews");

        setContentView(R.layout.account_prof_reviews);

        display_prof_review();
        //Detects like button
        profInfo.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ProfReview prof = dataSnapshot.getValue(ProfReview.class);
                prof.setLikesCount(prof.likesCount);
                prof_adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                prof_adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void display_prof_review() {

        prof_mListView = (ListView) findViewById(R.id.accountProfListView); //Checks to see if the strong from database goes in
        final DatabaseReference ref = profInfo;


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

                    Iterable<DataSnapshot> nextChildSet = child.getChildren();

                    for (DataSnapshot nextChild: nextChildSet) {
                        ProfReview instructor = nextChild.getValue(ProfReview.class); // <-- do . at end here to specify which child
                        if (instructor.getUserId() != null) {
                            if (instructor.userId.equals(uid)) {
                                prof_reviews.add(instructor);
                            }
                        }
                    }
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
