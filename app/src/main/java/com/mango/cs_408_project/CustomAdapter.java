package com.mango.cs_408_project;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.review_item, parent, false);
            viewHolder.instructorName = (TextView) convertView.findViewById(R.id.instructor_name);
            viewHolder.semesterTaken = (TextView) convertView.findViewById(R.id.semester_taken);
            viewHolder.stars = (RatingBar) convertView.findViewById(R.id.reviewStars);
            viewHolder.courseComment = (TextView) convertView.findViewById(R.id.courseComment2);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.info_item);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.instructorName.setText(dataModel.instructorName);
        viewHolder.stars.setRating(dataModel.rating);
        viewHolder.courseComment.setText(dataModel.courseComment);
        //viewHolder.semesterTaken.setText(dataModel.semester);
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}