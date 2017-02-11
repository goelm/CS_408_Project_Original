package com.mango.cs_408_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manasigoel on 2/11/17.
 */

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

/*        Button submit_button = (Button) findViewById(R.id.searchSubmit);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView search_query = (TextView) findViewById(R.id.searchQueryField);

                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(search_query.getText());
                boolean b = m.find();

                TextView message = (TextView) findViewById(R.id.success_fail_message);

                if(b)
                    message.setText("Please try again without special characters");
                else
                    message.setText("Good searh query!");

            }

        });*/
    }
}
