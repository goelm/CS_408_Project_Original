package com.mango.cs_408_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Scroller;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elvin on 2/8/17.
 */

public class AddCourseReview extends AppCompatActivity{

    // This is for the Server class
    final Server s = new Server();
    String capital_name;

    /* Course info variables */
    float rating; //Ranges from 0 to 5
    boolean help_session; //false for none and true if it exists
    boolean extra_credit;
    int toughness; //1 for easy, 2 for mild, 3 for typical, 4 for tough, 5 for unreasonable
    boolean electronics; //true for allowed and false for not allowed
    boolean textbook; //true for needed and false for not needed

    int value; //ranges from 0 to 100
    int understand; //ranges from 0 to 100

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

    SeekBar seekV;
    SeekBar seekU;
    RatingBar add_course_rating_bar;

    TextView textU;
    TextView textV;
    TextView add_courseratingBarText;

    int textUProgress = 0;
    int textVProgress = 0;
    float add_course_ratingProgress = 0;

    Button submit_button;
    EditText courseComment;

    TextView course;

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

        seekV = (SeekBar) findViewById(R.id.seekValue);
        seekU = (SeekBar) findViewById(R.id.seekUnderstand);

        submit_button = (Button) findViewById(R.id.course_submitBut);

        textU = (TextView) findViewById(R.id.textUnderstand);
        textV = (TextView) findViewById(R.id.textValue);
        add_courseratingBarText = (TextView) findViewById(R.id.add_course_RatingBarValue);

        add_course_rating_bar = (RatingBar) findViewById(R.id.course_rating);
        courseComment = (EditText) findViewById(R.id.courseComment);


        course = (TextView) findViewById(R.id.add_course_courseName);

        if(getIntent().hasExtra("user_input")){
            course.setText(getIntent().getStringExtra("user_input").toUpperCase());
        }


        /*
        RADIOBUTTON SWITCHING
         */


        //Accessibility question
        rb_hardAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_ezAccess = (RadioButton) findViewById(R.id.add_course_ezAccess);
                rb_ezAccess.setChecked(false);
                help_session = false;
            }
        });

        rb_ezAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_hardAccess = (RadioButton) findViewById(R.id.add_course_hardAccess);
                rb_hardAccess.setChecked(false);
                help_session = true;
            }
        });

        //Extra Credit Question
        rb_yes1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no1 = (RadioButton) findViewById(R.id.add_course_grade_no);
                rb_no1.setChecked(false);
                extra_credit = true;
            }
        });

        rb_no1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes1 = (RadioButton) findViewById(R.id.add_course_grade_yes);
                rb_yes1.setChecked(false);
                extra_credit = false;
            }
        });

        //Laptops or Phones Question
        rb_yes2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no2 = (RadioButton) findViewById(R.id.add_course_misc_no);
                rb_no2.setChecked(false);
                electronics = true;
            }
        });

        rb_no2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes2 = (RadioButton) findViewById(R.id.add_course_misc_yes);
                rb_yes2.setChecked(false);
                electronics = false;
            }
        });

        //BOOK REQUIREMENT
        rb_yes3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_no2 = (RadioButton) findViewById(R.id.add_course_book_no);
                rb_no2.setChecked(false);
                textbook = true;
            }
        });

        rb_no3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rb_yes2 = (RadioButton) findViewById(R.id.add_course_book_yes);
                rb_yes2.setChecked(false);
                textbook = false;
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


        // Seekbar
        seekV.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                textVProgress = progress;
                textV.setText(""+ textVProgress + "%");
//                value = progress;
//                textV.setText(String.valueOf(value)+"%");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                textUProgress = progress;
                textU.setText(""+ textUProgress + "%");
//                understand = progress;
//                textU.setText(String.valueOf(understand)+"%");
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Ratingbar
        add_course_rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                add_course_ratingProgress = rating;
                add_courseratingBarText.setText("" + add_course_ratingProgress);
            }
        });

        /* When submitting the review */
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                TextView instructor = (TextView) findViewById(R.id.add_course_instructor);
                TextView semester = (TextView) findViewById(R.id.add_semesterField);
                TextView description = (TextView) findViewById(R.id.add_course_description);


                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher courseMatcher = p.matcher(course.getText());
                Matcher instructorMatcher = p.matcher(instructor.getText());
                Matcher semesterMatcher = p.matcher(semester.getText());

                boolean invalidCourseName = courseMatcher.find();
                boolean invalidInstructorName = instructorMatcher.find();
                boolean invalidSemester = semesterMatcher.find();

                TextView message = (TextView) findViewById(R.id.add_course_submitText);

                if (course.getText().toString().trim().length() == 0 ||
                        instructor.getText().toString().trim().length() == 0 ||
                        (!rb_ezAccess.isChecked() && !rb_hardAccess.isChecked()) ||
                        (!rb_yes1.isChecked() && !rb_no1.isChecked()) ||
                        (!rb_yes2.isChecked() && !rb_no2.isChecked()) ||
                        (!rb_yes3.isChecked() && !rb_no3.isChecked()) ||
                        (!rb_difEz.isChecked() && !rb_difMild.isChecked() &&
                        !rb_difNorm.isChecked() && !rb_difTough.isChecked() &&
                        !rb_difCrazy.isChecked()))  {
                    message.setText("Please fill in every field");
                }

                else if (invalidCourseName || invalidInstructorName || invalidSemester) {
                    message.setText("Invalid inputs");
                }

                else {
                    //All are added to an object for better organization
                    CourseReview review = new CourseReview();
                    capital_name = course.getText().toString().toUpperCase();
                    review.setCourseName(capital_name);
                    review.setInstructorName(String.valueOf(instructor.getText()));
                    //review.setSemesterTaken(String.valueOf(semester.getText()));
                    review.setCourseDescr(String.valueOf(description.getText()));
                    review.setRating(add_course_ratingProgress);
                    review.setSeekV(textVProgress);
                    review.setSeekU(textUProgress);
                    review.setHelpSession(help_session);
                    review.setExtraCredit(extra_credit);
                    review.setToughness(toughness);
                    review.setElectronics(electronics);
                    review.setTextBook(textbook);
                    review.setCourseComment(String.valueOf(courseComment.getText()));
                    review.setSemesterTaken(String.valueOf(semester.getText()));
                    s.write_course_review(capital_name, review);

                /* Go back to select a review */
                    Intent i = new Intent(AddCourseReview.this, SelectReview.class);
                    AddCourseReview.this.startActivity(i);
                }
            }

        });


        // Retrieving from the Database


    }

}
