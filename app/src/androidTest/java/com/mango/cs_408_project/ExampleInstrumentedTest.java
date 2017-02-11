package com.mango.cs_408_project;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<AddCourseReview> addCourseReviewTest =
            new ActivityTestRule<AddCourseReview>(AddCourseReview.class);


    @Test
    public void buttonStartsUnselected() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }

    @Test
    public void buttonSwitches() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }


}
