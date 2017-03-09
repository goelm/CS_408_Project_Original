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

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static com.firebase.ui.auth.ui.email.RegisterEmailFragment.TAG;

/**
 * Created by Elvin Uthuppan on 2/26/2017.
 */

public class CustomAdapter extends ArrayAdapter<CourseReview> implements View.OnClickListener{

    private ArrayList<CourseReview> dataSet;
    Context mContext;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference courseInfo = database.getReference("message/reviews/course");

    FacebookLogin f = new FacebookLogin();
    String uid = f.userID();

   // String uid = user.getUid();

    // View lookup cache
    private static class ViewHolder {
        TextView instructorName;
        TextView semesterTaken;
        RatingBar stars;
        TextView courseComment;
        ImageView info;
        Button likes;
        TextView numOfLikes;

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
            case R.id.likes_button:
                System.out.println("hello");
                onLikeClicked(courseInfo , dataModel);
                break;

            case R.id.info_item:
                System.out.println("hello");
                Snackbar.make(v, "Course Description: " + dataModel.courseDescr, Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    /*
    class likesChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            CourseReview course = dataSnapshot.getValue(CourseReview.class);
            course.setKey(dataSnapshot.getKey());
            dataSet.add(0, course);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            CourseReview newreview = dataSnapshot.getValue(CourseReview.class);
            for (CourseReview c : dataSet) {
                if (c.getKey().equals(key)) {

                }
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
    */


    private void onLikeClicked(final DatabaseReference postRef, final CourseReview course) {
        postRef.child(course.courseName).child(course.getKey()).runTransaction(new Transaction.Handler() {

            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                //CourseReview course = mutableData.getValue(CourseReview.class);
                System.out.println("User id: " + uid);

                if (course == null) {
                    return Transaction.success(mutableData);
                }

                if (course.likes.containsKey(uid)) {
                    // Unstar the post and remove self from stars
                    System.out.println("take away");
                    course.likesCount = course.likesCount - 1;
                    course.likes.remove(uid);
                } else {
                    System.out.println("add");

                    // Star the post and add self to stars
                    course.likesCount = course.likesCount + 1;
                    course.likes.put(uid, true);

                }

                // Set value and report transaction success
              //  mutableData.setValue(course.likesCount);
                postRef.child(course.courseName).child(course.getKey()).setValue(course);
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
            viewHolder.semesterTaken = (TextView) convertView.findViewById(R.id.semester_taken);
            viewHolder.stars = (RatingBar) convertView.findViewById(R.id.reviewStars);
            viewHolder.courseComment = (TextView) convertView.findViewById(R.id.courseComment2);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.info_item);
            viewHolder.likes = (Button) convertView.findViewById(R.id.likes_button);
            viewHolder.numOfLikes = (TextView) convertView.findViewById(R.id.badge_notification);


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
        viewHolder.likes.setOnClickListener(this);
        viewHolder.likes.setTag(position);
        viewHolder.numOfLikes.setText(String.valueOf(dataModel.likesCount));

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

