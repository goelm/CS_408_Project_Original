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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CourseDisplay extends AppCompatActivity {

    String user_input;

    Button course_addReview;
    Button view_reviews;

    TextView value_lectures_text;
    TextView understandable_text;
    TextView num_reviews_text;
    TextView extra_credit_text;
    TextView help_sessions_text;
    TextView toughness_text;
    TextView electronics_text;
    TextView textbook_text;

    ProgressBar value_lectures_bar;
    ProgressBar understandable_bar;
    ProgressBar extra_credit_bar;
    ProgressBar help_sessions_bar;
    ProgressBar toughness_bar;
    ProgressBar electronics_bar;
    ProgressBar textbook_bar;

    float value_lectures = 0;
    float understandable = 0;
    int extra_credit = 0;
    int help_sessions = 0;
    int toughness = 0;
    int electronics = 0;
    private float rating = 0;
    private float counter = 0;
    int textbook = 0;

    private final ArrayList<CourseReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info);

        user_input = getIntent().getStringExtra("user_input");

        display_course_review(user_input.toUpperCase());
    }


    public void display_course_review(String course_name) {

        course_addReview = (Button) findViewById(R.id.course_info_addReview);

        TextView courseName = (TextView) findViewById(R.id.textCourseName); //Set the Name of the Course here
        courseName.setText(course_name);

        final RatingBar stars = (RatingBar) findViewById(R.id.course_rating); //Finds the stars to update

        final DatabaseReference ref = courseInfo.child(course_name);

        course_addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseDisplay.this, AddCourseReview.class);
                i.putExtra("user_input", user_input);
                CourseDisplay.this.startActivity(i);
            }
        });

        view_reviews = (Button) findViewById(R.id.view_course_reviews);
        view_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseDisplay.this, CourseReviewsDisplay.class);
                i.putExtra("user_input", user_input);
                CourseDisplay.this.startActivity(i);
            }
        });


        ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child: children) {
                            CourseReview course = child.getValue(CourseReview.class); // <-- do . at end here to specify which child
                            reviews.add(course);
                            counter++;
                            rating += course.rating;//for stars
                            value_lectures += course.seekV;
                            understandable +=course.seekU;
                            if(course.extraCredit == true) {
                                extra_credit++;
                            }
                            if(course.helpSession){
                                help_sessions++;
                            }
                            if(course.electronics){
                                electronics++;
                            }
                            if(course.textBook){
                                textbook++;
                            }
                            toughness += course.toughness;
                        }
                        //Set statistics here
                        stars.setRating(rating/reviews.size());

                        num_reviews_text = (TextView) findViewById(R.id.num_reviews_course_text);
                        num_reviews_text.setText(Integer.toString((int)counter)+" reviews");

                        value_lectures_text = (TextView) findViewById(R.id.value_lecture_course_text);
                        understandable_text = (TextView) findViewById(R.id.understandable_course_text);
                        extra_credit_text = (TextView) findViewById(R.id.extra_credit_course_text);
                        help_sessions_text = (TextView) findViewById(R.id.help_sessions_course_text);
                        toughness_text = (TextView) findViewById(R.id.toughness_course_text);
                        electronics_text = (TextView) findViewById(R.id.electronics_course_text);
                        textbook_text = (TextView) findViewById(R.id.textbook_course_text);

                        value_lectures_text.setText(value_lectures_text.getText()+" (" + Integer.toString((int)(value_lectures/counter)) + "%)");
                        understandable_text.setText(understandable_text.getText()+" (" + Integer.toString((int)(understandable/counter)) + "%)");
                        extra_credit_text.setText(extra_credit_text.getText()+" (" + Integer.toString((int)((extra_credit/counter)*100)) + "%)");
                        help_sessions_text.setText(help_sessions_text.getText()+" (" + Integer.toString((int)((help_sessions/counter)*100)) + "%)");
                        toughness_text.setText(toughness_text.getText()+" (" + Float.toString(toughness/counter) + "/5)");
                        electronics_text.setText(electronics_text.getText()+" (" + Integer.toString((int)((electronics/counter)*100)) + "%)");
                        textbook_text.setText(textbook_text.getText()+" (" + Integer.toString((int)((textbook/counter)*100)) + "%)");


                        value_lectures_bar = (ProgressBar) findViewById(R.id.value_lecture_course_bar);
                        understandable_bar = (ProgressBar) findViewById(R.id.understandable_course_bar);
                        extra_credit_bar = (ProgressBar) findViewById(R.id.extra_credit_course_bar);
                        help_sessions_bar = (ProgressBar) findViewById(R.id.help_sessions_course_bar);
                        toughness_bar = (ProgressBar) findViewById(R.id.toughness_course_bar);
                        electronics_bar = (ProgressBar) findViewById(R.id.electronics_course_bar);
                        textbook_bar = (ProgressBar) findViewById(R.id.textbook_course_bar);

                        value_lectures_bar.setMax(100);
                        understandable_bar.setMax(100);
                        extra_credit_bar.setMax(100);

                        value_lectures_bar.setProgress((int)(value_lectures/counter));
                        understandable_bar.setProgress((int)(understandable/counter));
                        extra_credit_bar.setProgress((int)((extra_credit/counter)*100));
                        help_sessions_bar.setProgress((int)((help_sessions/counter)*100));
                        electronics_bar.setProgress((int)((electronics/counter)*100));
                        toughness_bar.setProgress((int)((toughness/(counter*5))*100));
                        textbook_bar.setProgress((int)((textbook/counter)*100));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}