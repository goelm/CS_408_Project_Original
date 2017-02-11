package com.mango.cs_408_project;

/**
 * Created by manasigoel on 2/10/17.
 */
import android.hardware.camera2.params.Face;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServerUnitTests {
   // @Test
    public void loginCheck() {
        FacebookLogin f = new FacebookLogin();
        assertEquals(true, f.signedIn);
    }
    /*
    public void logoutCheck() {
        FacebookLogin f = new FacebookLogin();
        assertEquals(false, f.signedIn);
    }
    */
}
