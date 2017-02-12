package com.mango.cs_408_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by elvin on 2/8/17.
 */

public class AddInstructorReview extends AppCompatActivity{

    // This is for the Server class
    final Server s = new Server();

    /* Instructor info variables */
    String name; // the person's name
    boolean prof; // true  for instructor, false for TA
    float rating; //Ranges from 0 to 5
    boolean help_session; //false for none and true if it exists
    boolean extra_credit;
    int toughness; //1 for easy, 2 for mild, 3 for typical, 4 for tough, 5 for unreasonable
    boolean electronics; //true for allowed and false for not allowed

    /* Button variables */
    RadioButton rb_instructor;
    RadioButton rb_ta;
    RadioButton rb_ezAccess;
    RadioButton rb_hardAccess;
    RadioButton rb_yes1;
    RadioButton rb_yes2;
    RadioButton rb_no1;
    RadioButton rb_no2;
    RadioButton rb_difEz;
    RadioButton rb_difMild;
    RadioButton rb_difNorm;
    RadioButton rb_difTough;
    RadioButton rb_difCrazy;
    Button submit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        rb_instructor = (RadioButton) findViewById(R.id.instructor);
        rb_ta = (RadioButton) findViewById(R.id.teachingassistant);
        rb_ezAccess = (RadioButton) findViewById(R.id.ezAccess);
        rb_hardAccess = (RadioButton) findViewById(R.id.hardAccess);
        rb_yes1 = (RadioButton) findViewById(R.id.yesButton1);
        rb_yes2 = (RadioButton) findViewById(R.id.yesButton2);
        rb_no1 = (RadioButton) findViewById(R.id.noButton1);
        rb_no2 = (RadioButton) findViewById(R.id.noButton2);
        rb_difEz = (RadioButton) findViewById(R.id.diffButton1);
        rb_difMild = (RadioButton) findViewById(R.id.diffButton2);
        rb_difNorm = (RadioButton) findViewById(R.id.diffButton3);
        rb_difTough = (RadioButton) findViewById(R.id.diffButton4);
        rb_difCrazy = (RadioButton) findViewById(R.id.diffButton5);
        submit_button = (Button) findViewById(R.id.submitBut);

        /*
        RADIOBUTTON SWITCHING
         */

        //TA or Instructor
        rb_instructor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_ta = (RadioButton) findViewById(R.id.teachingassistant);
                rb_ta.setChecked(false);
                prof = true;
            }
        });

        rb_ta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_instructor = (RadioButton) findViewById(R.id.instructor);
                rb_instructor.setChecked(false);
                prof = false;
            }
        });

        //Accessibility question
        rb_hardAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_ezAccess = (RadioButton) findViewById(R.id.ezAccess);
                rb_ezAccess.setChecked(false);
                help_session = false;
            }
        });

        rb_ezAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_hardAccess = (RadioButton) findViewById(R.id.hardAccess);
                rb_hardAccess.setChecked(false);
                help_session = false;
            }
        });

        //Extra Credit Question
        rb_yes1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no1 = (RadioButton) findViewById(R.id.noButton1);
                rb_no1.setChecked(false);
                extra_credit = false;
            }
        });

        rb_no1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes1= (RadioButton) findViewById(R.id.yesButton1);
                rb_yes1.setChecked(false);
                extra_credit = false;
            }
        });

        //Laptops or Phones Question
        rb_yes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no2 = (RadioButton) findViewById(R.id.noButton2);
                rb_no2.setChecked(false);
                electronics = false;
            }
        });

        rb_no2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes2= (RadioButton) findViewById(R.id.yesButton2);
                rb_yes2.setChecked(false);
                electronics = false;
            }
        });

        //Grading Toughness Question
        rb_difEz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);
                toughness = 1;

            }
        });

        rb_difMild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);
                toughness = 2;

            }
        });

        rb_difNorm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);
                toughness = 3;

            }
        });

        rb_difTough.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difCrazy.setChecked(false);
                toughness = 4;

            }
        });

        rb_difCrazy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
                toughness = 5;
            }
        });
        /******** End of Radio Buttons *******
         *************************************/


        /* When submitting the review */
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView first = (TextView) findViewById(R.id.first_name);
                TextView last = (TextView) findViewById(R.id.last_name);
                RatingBar rating_bar = (RatingBar) findViewById(R.id.instructor_rating);
                rating = rating_bar.getRating();
                String review = String.valueOf(prof);
                review += "," + String.valueOf(rating);
                review += "," + String.valueOf(help_session);
                review += "," + String.valueOf(extra_credit);
                review += "," + Integer.toString(toughness);
                review += "," + String.valueOf(electronics);
                s.write_instructor_review(first.getText().toString()+last.getText().toString(), review);

                /* Go back to select a review */
                Intent i=new Intent(AddInstructorReview.this, SelectReview.class);
                AddInstructorReview.this.startActivity(i);
            }

        });

    }

}
