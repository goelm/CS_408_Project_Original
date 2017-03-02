package com.mango.cs_408_project;

import android.app.Activity;
import android.content.ComponentName;
import android.support.test.espresso.core.deps.guava.util.concurrent.ThreadFactoryBuilder;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Elvin Uthuppan on 2/11/2017.
 */

public class FacebookLoginTests {
    @Rule
    public ActivityTestRule<FacebookLogin> addCourseReviewIntentsTestRule =
            new ActivityTestRule<FacebookLogin>(FacebookLogin.class);

    @Test
    public void asksForLoginFields() throws Exception{
        onView(withId(R.id.login_button)).perform(click());
        Thread.sleep(1000);

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Thread.sleep(2000); //Using Dummy account
        device.findObject(new UiSelector().className("android.widget.EditText")).setText("etanyacatori@gmail.com"); //Email field
        device.findObject(new UiSelector().resourceId("u_0_2")).setText("cs408project"); //Password Field
        device.findObject(new UiSelector().resourceId("u_0_6")).click(); //Clicks login button
        Thread.sleep(3000);
        device.findObject(new UiSelector().resourceId("u_0_1")).click(); //Clicks ok to confirm
        Thread.sleep(4000);
        onView(withId(R.id.instructor_review_button)).check(matches(isDisplayed())); //Checks to see if instructor button is there
    }

    @Test
    public void logsOut() throws Exception{
        onView(withId(R.id.login_button)).perform(click());
        Thread.sleep(1000);

        UiDevice device = UiDevice.getInstance(getInstrumentation());
        Thread.sleep(2000); //Using Dummy account
        device.findObject(new UiSelector().className("android.widget.EditText")).setText("etanyacatori@gmail.com"); //Email field
        device.findObject(new UiSelector().resourceId("u_0_2")).setText("cs408project"); //Password Field
        device.findObject(new UiSelector().resourceId("u_0_6")).click(); //Clicks login button
        Thread.sleep(3000);
        device.findObject(new UiSelector().resourceId("u_0_1")).click(); //Clicks ok to confirm
        Thread.sleep(4000);
        onView(withId(R.id.instructor_review_button)).check(matches(isDisplayed())); //Checks to see if instructor button is there
    }
}
