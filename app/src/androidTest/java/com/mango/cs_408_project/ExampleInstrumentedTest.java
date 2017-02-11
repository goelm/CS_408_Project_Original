package com.mango.cs_408_project;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
    public void buttonDisplayed() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void buttonDisplayed2() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }


    @Test
    public void buttonChecked() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).check(matches(isChecked()));
        Thread.sleep(1000);
    }

    @Test
    public void buttonChecked2() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(isChecked()));
        Thread.sleep(1000);
    }

    @Test
    public void buttonChecked3() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void buttonChecked4() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }
//
//    @Test
//    public void buttonClicked2() throws Exception {
//        onView(withId(R.id.add_course_hardAccess)).perform(click());
//        onView(withId(R.id.add_course_hardAccess)).check(matches(isSelected()));
//        Thread.sleep(1000);
//    }

    @Test
    public void buttonStartsUnselected() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }

    @Test
    public void buttonStartsUnselected2() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }

    @Test
    public void buttonSwitches() throws Exception {
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }

    @Test
    public void buttonSwitches2() throws Exception {
        onView(withId(R.id.add_course_ezAccess)).perform(click());
        onView(withId(R.id.add_course_hardAccess)).perform(click());
        onView(withId(R.id.add_course_ezAccess)).check(matches(not(isSelected())));
        Thread.sleep(1000);
    }




}
