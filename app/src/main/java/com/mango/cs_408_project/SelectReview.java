package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by manasigoel on 2/10/17.
 */

public class SelectReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_review_type);

        Button instructor = (Button) findViewById(R.id.instructor_review);
        Button course = (Button) findViewById(R.id.course_review);

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

    }
}
