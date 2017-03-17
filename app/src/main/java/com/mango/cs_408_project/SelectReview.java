package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by manasigoel on 2/10/17.
 */

public class SelectReview extends AppCompatActivity {
    FacebookLogin f = new FacebookLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Edits the action bar on top
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select an Option");
        //actionBar.openOptionsMenu();
        setContentView(R.layout.select_review_type);


        Button instructor = (Button) findViewById(R.id.instructor_review_button);
        Button course = (Button) findViewById(R.id.course_review_button);
        //Button logOut = (Button) findViewById(R.id.logout_button);

        Button search_button = (Button) findViewById(R.id.search_select_button);

        instructor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i=new Intent(SelectReview.this, AddInstructorReview.class);
                SelectReview.this.startActivity(i);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent j=new Intent(SelectReview.this, AddCourseReview.class);
                SelectReview.this.startActivity(j);
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent k=new Intent(SelectReview.this, Search.class);
                SelectReview.this.startActivity(k);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editCourseReviews:
                Toast.makeText(SelectReview.this, "My Course Reviews", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SelectReview.this, AccountCourses.class);
                SelectReview.this.startActivity(i);
                return true;
            case R.id.editProfReviews:
                Toast.makeText(SelectReview.this, "My Instructor Reviews", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(SelectReview.this, AccountProfs.class);
                SelectReview.this.startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
