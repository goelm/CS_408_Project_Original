package com.mango.cs_408_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manasigoel on 2/26/17.
 */

public class ProfCustomAdapter {
/*
    private ArrayList<CourseReview> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView instructorName;
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

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CourseReview dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        CustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new CustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.review_item, parent, false);
            viewHolder.instructorName = (TextView) convertView.findViewById(R.id.instructor_name);
            viewHolder.stars = (RatingBar) convertView.findViewById(R.id.reviewStars);
            viewHolder.courseComment = (TextView) convertView.findViewById(R.id.courseComment2);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.helpfulness_button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapter.ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.instructorName.setText(dataModel.instructorName);
        viewHolder.stars.setRating(dataModel.rating);
        viewHolder.courseComment.setText(dataModel.courseComment);
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
        */
}
