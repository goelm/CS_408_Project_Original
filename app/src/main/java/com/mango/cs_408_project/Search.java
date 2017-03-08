package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by manasigoel on 2/11/17.
 */

public class Search extends AppCompatActivity {

    public static String user_input;
    TextView message;
    boolean foundcourse;
    boolean foundprof;
    private final ArrayList<CourseReview> reviews = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");
    Button new_instructor;
    Button new_course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Button submit_button = (Button) findViewById(R.id.searchSubmit);
        message = (TextView) findViewById(R.id.success_fail_message);
        message.setVisibility(View.GONE);
        new_instructor = (Button) findViewById(R.id.new_instructor_button);
        new_course = (Button) findViewById(R.id.new_course_button);
        new_instructor.setVisibility(View.GONE);
        new_course.setVisibility(View.GONE);


        /* Auto Complete Code in the search function */
        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        //Child the root before all the push() keys are found and add a ValueEventListener()
        courseInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
                    //Get the suggestion by childing the key of the string you want to get.
                    String className = suggestionSnapshot.getKey();
                    //Add the retrieved string to the list
                    autoComplete.add(className);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
                    //Get the suggestion by childing the key of the string you want to get.
                    String instructorName = suggestionSnapshot.getKey();
                    //Add the retrieved string to the list
                    autoComplete.add(instructorName);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        AutoCompleteTextView ACTV= (AutoCompleteTextView)findViewById(R.id.searchQueryField);
        ACTV.setAdapter(autoComplete);


        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView search_query = (TextView) findViewById(R.id.searchQueryField);
                user_input = search_query.getText().toString();

                if (user_input.length() == 0) {
                    message.setVisibility(View.VISIBLE);
                    message.setText("Search field can not be empty");
                } else {

                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(search_query.getText());
                    boolean b = m.find();

                    if (b) {
                        message.setVisibility(View.VISIBLE);
                        message.setText("Please try again without special characters");
                    }
                    else {

                        message.setVisibility(View.VISIBLE);
                        message.setText("Good search query!");

                        //Checking all the courses to see if anything matches
                        final DatabaseReference refcourse = courseInfo.child(user_input.toUpperCase());

                        refcourse.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    foundcourse = true;
                                    message.setText("exists");
                                }
                                else{
                                    foundcourse = false;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

                        // If none of the courses match, we can see if any of the instructors match
                        if(!foundcourse) {
                            final DatabaseReference refprof = profInfo.child(user_input.toUpperCase());

                            refprof.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        foundprof = true;
                                        message.setText("exists");
                                    } else {
                                        foundprof = false;
                                        message.setText("does not exist");

                                        //Neither of them matched so we can display buttons to add new reviews
                                        new_instructor.setVisibility(View.VISIBLE);
                                        new_course.setVisibility(View.VISIBLE);
                                        new_instructor.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                Intent i = new Intent(Search.this, AddInstructorReview.class);
                                                Search.this.startActivity(i);
                                            }
                                        });
                                        new_course.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                Intent i = new Intent(Search.this, AddCourseReview.class);
                                                Search.this.startActivity(i);
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                        if(foundcourse || foundprof){
                            foundSomething(foundcourse, foundprof);
                        }

                    }

                }
            }

        });
    }

    public void foundSomething(boolean course, boolean prof){

        // If a course matched then it will open course dispaly and send the user input to that intent
        if(course) {
            Intent i = new Intent(Search.this, CourseDisplay.class);
            i.putExtra("user_input", user_input);
            Search.this.startActivity(i);
        }

        // If a professor matched then it will open prof dispaly and send the user input to that intent
        if(prof){
            Intent i = new Intent(Search.this, ProfDisplay.class);
            i.putExtra("user_input", user_input);
            Search.this.startActivity(i);
        }
    }
}
