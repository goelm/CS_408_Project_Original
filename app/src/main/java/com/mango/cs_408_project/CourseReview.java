package com.mango.cs_408_project;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        setContentView(R.layout.activity_main);
    }

}
