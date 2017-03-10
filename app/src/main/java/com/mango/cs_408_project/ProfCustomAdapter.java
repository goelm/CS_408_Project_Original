package com.mango.cs_408_project;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.facebook.AccessToken;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by manasigoel on 2/26/17.
 */

public class ProfCustomAdapter extends ArrayAdapter<ProfReview> implements View.OnClickListener{

    private ArrayList<ProfReview> dataSet;
    Context mContext;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference profInfo = database.getReference("message/reviews/instructor");

    //private FacebookLogin user = new FacebookLogin();
    String uid = userID();

    // View lookup cache
    private static class ViewHolder {
       // TextView courseName;
       // TextView semesterTaken;
        RatingBar stars;
        TextView profComment;
        Button likes;
        TextView numOfLikes;
    }

    public ProfCustomAdapter(ArrayList<ProfReview> data, Context context) {
        super(context, R.layout.prof_review_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ProfReview dataModel =(ProfReview) object;

        switch (v.getId())
        {
            case R.id.likes_button2:
                onLikeClicked(profInfo , dataModel);
                break;
        }

    }

    public String userID() {
        AccessToken user = AccessToken.getCurrentAccessToken();
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            return user.getUserId();
        } else {
            // No user is signed in
            System.out.println("No user signed in");
            return null;
        }
    }

    private void onLikeClicked(final DatabaseReference postRef, final ProfReview course) {
        postRef.child(course.profName).child(course.getKey()).runTransaction(new Transaction.Handler() {

            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                //CourseReview course = mutableData.getValue(CourseReview.class);
                if (course == null) {
                    return Transaction.success(mutableData);
                }
                if (course.likes.containsKey(uid)) {
                    // Unstar the post and remove self from stars
                    course.likesCount = course.likesCount - 1;
                    course.likes.remove(uid);
                } else {
                    // Star the post and add self to stars
                    course.likesCount = course.likesCount + 1;
                    course.likes.put(uid, true);
                }

                // Set value and report transaction success
                //  mutableData.setValue(course.likesCount);
                postRef.child(course.profName).child(course.getKey()).setValue(course);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                //Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ProfReview dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ProfCustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ProfCustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.prof_review_item, parent, false);
            viewHolder.stars = (RatingBar) convertView.findViewById(R.id.prof_reviewStars);
            viewHolder.profComment = (TextView) convertView.findViewById(R.id.profComment2);
            //viewHolder.semesterTaken = (TextView) convertView.findViewById(R.id.prof_semester_taken);
            //viewHolder.courseName = (TextView) convertView.findViewById(R.id.instructor_course_name);
            viewHolder.likes = (Button) convertView.findViewById(R.id.likes_button2);
            viewHolder.numOfLikes = (TextView) convertView.findViewById(R.id.badge_notification2);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ProfCustomAdapter.ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        //viewHolder.instructorName.setText(dataModel.instructorName);
        // TODO: maybe we could add a course section for each prof review like instructorName?
        Log.d("DataModel rating: ", String.valueOf(dataModel.rating));
        viewHolder.stars.setRating(dataModel.rating);
        viewHolder.profComment.setText(dataModel.profComment);
        //viewHolder.courseName.setText(dataModel.course);
       // viewHolder.semesterTaken.setText(dataModel.semester);
        viewHolder.likes.setOnClickListener(this);
        viewHolder.likes.setTag(position);
        viewHolder.numOfLikes.setText(String.valueOf(dataModel.likesCount));
        // Return the completed view to render on screen
        return convertView;
    }

}
