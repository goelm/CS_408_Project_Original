package com.mango.cs_408_project;

/**
 * Created by manasigoel on 2/10/17.
 */
import android.hardware.camera2.params.Face;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServerUnitTests {
   // @Test

    /* INTEGRATION TESTS TO BE USED IN FUTURE
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("tests");

    int user_id = 123456;

    public void loginCheck() {
        FacebookLogin f = new FacebookLogin();
        assertEquals(true, f.signedIn);
    }
    public void logoutCheck() {
        FacebookLogin f = new FacebookLogin();
        assertEquals(false, f.signedIn);
    }

    @Test
    public void test_write_instructor_review() throws Exception{
        DatabaseReference inst = myRef.child("reviews").child("instructor").child("test_instructor").push();
        inst.setValue(Integer.toString(user_id)+ "," + "test_instructor_review");
        assertEquals(1,1);
    }

    @Test
    public void test_write_course_review() throws Exception{
        DatabaseReference inst = myRef.child("reviews").child("course").child("test_course").push();
        inst.setValue(Integer.toString(user_id)+ "," + "tests_course_review");
        assertEquals(1,1);
    } */
}
