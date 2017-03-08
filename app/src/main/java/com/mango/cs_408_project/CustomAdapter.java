package com.mango.cs_408_project;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CustomAdapter extends ArrayAdapter<CourseReview> implements View.OnClickListener{

    private ArrayList<CourseReview> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView instructorName;
        TextView semesterTaken;
        RatingBar stars;
        TextView courseComment;
        ImageView info;

//        ImageView downVoteButton;
//        ImageView upVoteButton;
//        TextView upVote;
//        TextView downVote;

    }

    public CustomAdapter(ArrayList<CourseReview> data, Context context) {
        super(context, R.layout.review_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        CourseReview dataModel =(CourseReview)object;

        switch (v.getId())
        {
            case R.id.info_item:
                Snackbar.make(v, "Course Description: " +dataModel.courseDescr, Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for that position
        CourseReview dataModel = getItem(position);
        // Check if an existing view is being reused, else inflate the view
        ViewHolder viewHolder; // view lookup cache in tag



        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.review_item, null);
            viewHolder.instructorName = (TextView) convertView.findViewById(R.id.instructor_name);
            viewHolder.semesterTaken = (TextView) convertView.findViewById(R.id.semester_takenInput);
            viewHolder.stars = (RatingBar) convertView.findViewById(R.id.reviewStars);
            viewHolder.courseComment = (TextView) convertView.findViewById(R.id.courseComment2);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.info_item);

//            viewHolder.upVoteButton = (ImageView) convertView.findViewById(R.id.upVoteButton);
//            viewHolder.downVoteButton= (ImageView) convertView.findViewById(R.id.downVoteButton);
//            viewHolder.upVote = (TextView) convertView.findViewById(R.id.upVoteDisplay);
//            viewHolder.downVote = (TextView) convertView.findViewById(R.id.downVoteDisplay);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.instructorName.setText(dataModel.instructorName);
        viewHolder.semesterTaken.setText(dataModel.semester);
        viewHolder.stars.setRating(dataModel.rating);
        viewHolder.courseComment.setText(dataModel.courseComment);
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);

//        viewHolder.upVoteButton.setOnClickListener(this);
//        viewHolder.downVoteButton.setOnClickListener(this);
//        viewHolder.upVoteButton.setTag(position);
//        viewHolder.downVoteButton.setTag(position);
//        viewHolder.upVote.setText(dataModel.upVote);
//        viewHolder.downVote.setText(dataModel.downVote);

        // Return the completed view to render on screen
        return convertView;
    }
}

