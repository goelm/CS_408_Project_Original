package com.mango.cs_408_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by elvin on 2/8/17.
 */

public class AddInstructorReview extends AppCompatActivity{

    // This is for the Server class
    final Server s = new Server();

    /* Instructor info variables */
    String name; // the person's name
    boolean prof; // true  for instructor, false for TA
    boolean help_session; //false for none and true if it exists
    boolean extra_credit;
    int toughness; //1 for easy, 2 for mild, 3 for typical, 4 for tough, 5 for unreasonable
    boolean electronics; //true for allowed and false for not allowed

    int value; //ranges from 0 to 100
    int understand; //ranges from 0 to 100

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

    SeekBar seekV;
    SeekBar seekU;
    RatingBar rating_bar;

    TextView textU;
    TextView textV;
    TextView ratingBarText;

    EditText profComment;

    int textUProgress = 0;
    int textVProgress = 0;
    float ratingProgress = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        TextView first = (TextView) findViewById(R.id.first_name);
        TextView last = (TextView) findViewById(R.id.last_name);

        if(getIntent().hasExtra("name")){
            String full_name = getIntent().getStringExtra("name");
            String [] names = full_name.split(" ");
            first.setText(names[0]);
            last.setText(names[1]);
        }

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

        seekV = (SeekBar) findViewById(R.id.seekValue);
        seekU = (SeekBar) findViewById(R.id.seekUnderstand);


        rating_bar = (RatingBar) findViewById(R.id.instructor_rating);

        textU = (TextView) findViewById(R.id.textUnderstand);
        textV = (TextView) findViewById(R.id.textValue);
        ratingBarText = (TextView) findViewById(R.id.add_info_RatingBarValue);

        profComment = (EditText) findViewById(R.id.profComment);
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
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingProgress = rating;
                ratingBarText.setText("" + ratingProgress);
            }
        });

        /* When submitting the review */
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView first = (TextView) findViewById(R.id.first_name);
                TextView last = (TextView) findViewById(R.id.last_name);

                Pattern p = Pattern.compile("[^a-zA-Z]", Pattern.CASE_INSENSITIVE);
                Matcher firstMatcher = p.matcher(first.getText());
                Matcher lastMatcher = p.matcher(last.getText());

                boolean firstB = firstMatcher.find();
                boolean lastB = lastMatcher.find();

                TextView message = (TextView) findViewById(R.id.add_info_submitText);

                if (first.getText().toString().trim().length() == 0 ||
                        last.getText().toString().trim().length() == 0 ||
                        (!rb_instructor.isChecked() && !rb_ta.isChecked()) ||
                        (!rb_ezAccess.isChecked() && !rb_hardAccess.isChecked()) ||
                        (!rb_yes1.isChecked() && !rb_no1.isChecked()) ||
                        (!rb_yes2.isChecked() && !rb_no2.isChecked()) ||
                        (!rb_difEz.isChecked() && !rb_difMild.isChecked() && !rb_difNorm.isChecked()
                        && !rb_difTough.isChecked() && !rb_difCrazy.isChecked())) {
                    message.setText("Please fill in every field");
                }

                else if (firstB) {
                    message.setText("Invalid first name");
                }

                else if (lastB) {
                    message.setText("Invalid last name");
                }

                else {

                    ProfReview review = new ProfReview();
                    review.setProfName(first.getText().toString().toUpperCase() + " " + last.getText().toString().toUpperCase());
                    review.setProf(prof);
                    review.setRating(ratingProgress);
                    review.setSeekV(textVProgress); //Value of lecture
                    review.setSeekU(textUProgress); //Understandable trait
                    review.setHelpSession(help_session);
                    review.setExtraCredit(extra_credit);
                    review.setToughness(toughness);
                    review.setElectronics(electronics);
                    review.setProfComment(String.valueOf(profComment.getText()));
                    s.write_instructor_review(review.profName, review);

                /* Go back to select a review */
                    Intent i = new Intent(AddInstructorReview.this, SelectReview.class);
                    AddInstructorReview.this.startActivity(i);
                }
            }

        });

    }

}
