package com.mango.cs_408_project;


import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by elvin on 2/8/17.
 */

public class CourseReview extends AppCompatActivity{

    // Initialize Facebook Login button
    /*mCallbackManager = CallbackManager.Factory.create();
    LoginButton loginButton = (LoginButton) findViewById(R.id.button_facebook_login);
    loginButton.setReadPermissions("email", "public_profile");*/

    // This is for the server class
    final server s = new server();

    /* Instructor info variables */
    String name; // the person's name
    boolean prof; // true  for instructor, false for TA
    int rating; //Ranges from 0 to 5
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
                help_session = true;
            }
        });

        //Extra Credit Question
        rb_yes1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no1 = (RadioButton) findViewById(R.id.noButton1);
                rb_no1.setChecked(false);
                extra_credit = true;
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
                electronics = true;
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
                rating = rating_bar.getNumStars();
                String review = String.valueOf(prof);
                review += "," + Integer.toString(rating);
                review += "," + String.valueOf(help_session);
                review += "," + String.valueOf(extra_credit);
                review += "," + Integer.toString(toughness);
                review += "," + String.valueOf(electronics);
                s.write_instructor_review(first.getText().toString()+last.getText().toString(), review);
            }

        });

    }

}
