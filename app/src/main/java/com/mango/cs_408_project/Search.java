package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mango.cs_408_project.CourseDisplay.has_user_input;

/**
 * Created by manasigoel on 2/11/17.
 */

public class Search extends AppCompatActivity {

    public static String user_input;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Button submit_button = (Button) findViewById(R.id.searchSubmit);
        message = (TextView) findViewById(R.id.success_fail_message);

        if (!has_user_input) {
            message.setText("" + user_input + " does not exist");
            has_user_input = true;
        }

        //final CourseDisplay cDisplay = new CourseDisplay();


        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView search_query = (TextView) findViewById(R.id.searchQueryField);

                user_input = search_query.getText().toString();

                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(search_query.getText());
                boolean b = m.find();

                 if(b)
                    message.setText("Please try again without special characters");
                else {

                    //float c = cDisplay.getCounter();
                    //Log.d("test", "" + c);
                    message.setText("Good search query!");

                    Intent i = new Intent(Search.this, CourseDisplay.class);
                    Search.this.startActivity(i);
                }

            }

        });
    }
}
