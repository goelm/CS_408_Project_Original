package com.mango.cs_408_project;


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

public class CourseReview extends AppCompatActivity{

    // Initialize Facebook Login button
    /*mCallbackManager = CallbackManager.Factory.create();
    LoginButton loginButton = (LoginButton) findViewById(R.id.button_facebook_login);
    loginButton.setReadPermissions("email", "public_profile");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        RadioButton rb_instructor = (RadioButton) findViewById(R.id.instructor);
        RadioButton rb_ta = (RadioButton) findViewById(R.id.teachingassitant);

        rb_instructor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_ta = (RadioButton) findViewById(R.id.teachingassitant);
                rb_ta.setChecked(false);
            }
        });

        rb_ta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_instructor = (RadioButton) findViewById(R.id.instructor);
                rb_instructor.setChecked(false);
            }
        });
    }

}
