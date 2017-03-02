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
import android.widget.ProgressBar;
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


/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class ProfDisplay extends AppCompatActivity {
    private float rating = 0;
    private float counter = 0;
    String user_input;
    Button prof_addReview;
    TextView value_lectures_text;
    TextView understandable_text;
    TextView num_reviews_text;
    TextView extra_credit_text;
    ProgressBar value_lectures_bar;
    ProgressBar understandable_bar;
    ProgressBar extra_credit_bar;
    float value_lectures;
    float understandable;
    int extra_credit;
    Button view_reviews;


    private final ArrayList<ProfReview> prof_reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_info);
        user_input = getIntent().getStringExtra("user_input");

        display_prof_review(user_input.toUpperCase());

        prof_addReview = (Button) findViewById(R.id.professor_info_addReview);

        prof_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfDisplay.this, AddInstructorReview.class);
                i.putExtra("name", user_input);
                ProfDisplay.this.startActivity(i);
            }
        });

        view_reviews = (Button) findViewById(R.id.view_reviews_button);

        view_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfDisplay.this, ProfReviewsDisplay.class);
                Log.d("manasi prof display", user_input);
                i.putExtra("user_input", user_input);
                ProfDisplay.this.startActivity(i);
            }
        });
    }


    public void display_prof_review(String prof_name) {


        TextView profName = (TextView) findViewById(R.id.textProfName); //Set the Name of the Course here
        profName.setText(prof_name);

        final RatingBar stars = (RatingBar) findViewById(R.id.instructor_rating);

        final DatabaseReference ref = profInfo.child(prof_name);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    ProfReview instructor = child.getValue(ProfReview.class); // <-- do . at end here to specify which child
                    prof_reviews.add(instructor);
                    counter++;
                    rating += instructor.rating;//for stars
                    value_lectures += instructor.seekV;
                    understandable +=instructor.seekU;
                    if(instructor.extraCredit == true) {
                        extra_credit++;
                    }
                }
                //Set statistics here
                stars.setRating(rating/prof_reviews.size());

                num_reviews_text = (TextView) findViewById(R.id.num_reviews_text);
                num_reviews_text.setText(Integer.toString((int)counter)+" reviews");

                value_lectures_text = (TextView) findViewById(R.id.value_lecture_heading);
                understandable_text = (TextView) findViewById(R.id.understandable_text);
                extra_credit_text = (TextView) findViewById(R.id.extra_credit_text);

                value_lectures_text.setText(value_lectures_text.getText()+" (" + Integer.toString((int)(value_lectures/counter)) + "%)");
                understandable_text.setText(understandable_text.getText()+" (" + Integer.toString((int)(understandable/counter)) + "%)");
                extra_credit_text.setText(extra_credit_text.getText()+" (" + Integer.toString((int)((extra_credit/counter)*100)) + "%)");

                value_lectures_bar = (ProgressBar) findViewById(R.id.value_lecture_bar);
                understandable_bar = (ProgressBar) findViewById(R.id.understandable_bar);
                extra_credit_bar = (ProgressBar) findViewById(R.id.extra_credit_bar);

                value_lectures_bar.setMax(100);
                understandable_bar.setMax(100);
                extra_credit_bar.setMax(100);

                value_lectures_bar.setProgress((int)(value_lectures/counter));
                understandable_bar.setProgress((int)(understandable/counter));
                extra_credit_bar.setProgress((int)((extra_credit/counter)*100));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}