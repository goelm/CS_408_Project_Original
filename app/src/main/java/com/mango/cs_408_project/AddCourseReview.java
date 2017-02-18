package com.mango.cs_408_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by elvin on 2/8/17.
 */

public class AddCourseReview extends AppCompatActivity{

    // This is for the Server class
    final Server s = new Server();

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

    Button submit_button;


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

        final TextView textU = (TextView) findViewById(R.id.textUnderstand);
        final TextView textV = (TextView) findViewById(R.id.textValue);

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

        seekV.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                value = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                textV.setText(String.valueOf(value)+"%");
            }
        });

        seekU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                understand = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                textU.setText(String.valueOf(understand)+"%");
            }
        });




        /* When submitting the review */
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView course = (TextView) findViewById(R.id.add_course_courseName);
                TextView instructor = (TextView) findViewById(R.id.add_course_instructor);
                TextView ta = (TextView) findViewById(R.id.add_course_TA);
                TextView description = (TextView) findViewById(R.id.add_course_description);
                RatingBar rating_bar = (RatingBar) findViewById(R.id.course_rating);
                rating = rating_bar.getRating();
                String review = String.valueOf(instructor.getText());
                review += String.valueOf(ta.getText());
                review += String.valueOf(description.getText());
                review += "," + String.valueOf(rating);
                review += "," + String.valueOf(help_session);
                review += "," + String.valueOf(extra_credit);
                review += "," + Integer.toString(toughness);
                review += "," + String.valueOf(electronics);
                review += "," + String.valueOf(textbook);
                review += "," + String.valueOf(value);
                review += "," + String.valueOf(understand);
                s.write_course_review(String.valueOf(course.getText()), review);

                /* Go back to select a review */
                Intent i=new Intent(AddCourseReview.this, SelectReview.class);
                AddCourseReview.this.startActivity(i);
            }

        });

    }

}
