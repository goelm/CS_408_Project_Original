package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by manasigoel on 2/10/17.
 */

public class SafeLoginActivity extends AppCompatActivity {
    FacebookLogin f = new FacebookLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_review_type);

        Button instructor = (Button) findViewById(R.id.instructor_review_button);
        Button course = (Button) findViewById(R.id.course_review_button);
        Button logOut = (Button) findViewById(R.id.logout_button);

        Button search_button = (Button) findViewById(R.id.search_select_button);


        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                logout(v);
            }
        });

        instructor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(SafeLoginActivity.this, AddInstructorReview.class);
                SafeLoginActivity.this.startActivity(i);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent j=new Intent(SafeLoginActivity.this, AddCourseReview.class);
                SafeLoginActivity.this.startActivity(j);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent k=new Intent(SafeLoginActivity.this, Search.class);
                SafeLoginActivity.this.startActivity(k);
            }
        });

    }

    private void goLoginScreen() {
        //Test to see if the user is logged out
        f.signedIn = false;
        //LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, FacebookLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    public void logout(View view) {
        f.signedIn = false;
        //Test to see if the user
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}
