package com.mango.cs_408_project;


import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by elvin on 2/8/17.
 */

public class AddCourseReview extends AppCompatActivity{

    // Initialize Facebook Login button
    /*mCallbackManager = CallbackManager.Factory.create();
    LoginButton loginButton = (LoginButton) findViewById(R.id.button_facebook_login);
    loginButton.setReadPermissions("email", "public_profile");*/


    RadioButton rb_ezAccess;
    RadioButton rb_hardAccess;
    RadioButton rb_yes1;
    RadioButton rb_yes2;
    RadioButton rb_yes3;
    RadioButton rb_no1;
    RadioButton rb_no2;
    RadioButton rb_no3;
    RadioButton rb_difEz;
    RadioButton rb_difMild;
    RadioButton rb_difNorm;
    RadioButton rb_difTough;
    RadioButton rb_difCrazy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);

        rb_ezAccess = (RadioButton) findViewById(R.id.add_course_ezAccess);
        rb_hardAccess = (RadioButton) findViewById(R.id.add_course_hardAccess);
        rb_yes1 = (RadioButton) findViewById(R.id.add_course_grade_yes);
        rb_yes2 = (RadioButton) findViewById(R.id.add_course_misc_yes);
        rb_yes3 = (RadioButton) findViewById(R.id.add_course_book_yes);
        rb_no1 = (RadioButton) findViewById(R.id.add_course_grade_no);
        rb_no2 = (RadioButton) findViewById(R.id.add_course_misc_no);
        rb_no3 = (RadioButton) findViewById(R.id.add_course_book_no);
        rb_difEz = (RadioButton) findViewById(R.id.add_course_toughness_easy);
        rb_difMild = (RadioButton) findViewById(R.id.add_course_toughness_mild);
        rb_difNorm = (RadioButton) findViewById(R.id.add_course_toughness_typical);
        rb_difTough = (RadioButton) findViewById(R.id.add_course_toughness_tough);
        rb_difCrazy = (RadioButton) findViewById(R.id.add_course_toughness_unreasonable);



        /*
        RADIOBUTTON SWITCHING
         */


        //Accessibility question
        rb_hardAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_ezAccess = (RadioButton) findViewById(R.id.add_course_ezAccess);
                rb_ezAccess.setChecked(false);
            }
        });

        rb_ezAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_hardAccess = (RadioButton) findViewById(R.id.add_course_hardAccess);
                rb_hardAccess.setChecked(false);
            }
        });

        //Extra Credit Question
        rb_yes1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no1 = (RadioButton) findViewById(R.id.add_course_grade_no);
                rb_no1.setChecked(false);
            }
        });

        rb_no1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes1 = (RadioButton) findViewById(R.id.add_course_grade_yes);
                rb_yes1.setChecked(false);
            }
        });

        //Laptops or Phones Question
        rb_yes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no2 = (RadioButton) findViewById(R.id.add_course_misc_no);
                rb_no2.setChecked(false);
            }
        });

        rb_no2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes2 = (RadioButton) findViewById(R.id.add_course_misc_yes);
                rb_yes2.setChecked(false);
            }
        });

        //BOOK REQUIREMENT
        rb_yes3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no2 = (RadioButton) findViewById(R.id.add_course_book_no);
                rb_no2.setChecked(false);
            }
        });

        rb_no3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes2 = (RadioButton) findViewById(R.id.add_course_book_yes);
                rb_yes2.setChecked(false);
            }
        });

        //Grading Toughness Question
        rb_difEz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);

            }
        });

        rb_difMild.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);

            }
        });

        rb_difNorm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difTough.setChecked(false);
                rb_difCrazy.setChecked(false);

            }
        });

        rb_difTough.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difCrazy.setChecked(false);

            }
        });

        rb_difCrazy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rb_difEz.setChecked(false);
                rb_difMild.setChecked(false);
                rb_difNorm.setChecked(false);
                rb_difTough.setChecked(false);
            }

        });


        /******** End of Radio Buttons *******
         *************************************/

    }

}
