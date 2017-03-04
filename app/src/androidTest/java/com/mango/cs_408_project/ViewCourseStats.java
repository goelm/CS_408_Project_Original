package com.mango.cs_408_project;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

/**
 * Created by manasigoel on 3/3/17.
 */

public class ViewCourseStats {

    @Rule
    public ActivityTestRule<CourseDisplay> courseDisplayActivityTestRule =
            new ActivityTestRule<CourseDisplay>(CourseDisplay.class){
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, MainActivity.class);
                    result.putExtra("user_input", "TEST 101");
                    return result;
                }
            };

    @Test
    public void statsDisplayed() throws Exception {

        Thread.sleep(2000);
        onView(withId(R.id.textCourseName)).check(matches(withText("TEST 101")));
        onView(withId(R.id.num_reviews_course_text)).check(matches(withText("1 reviews")));
        onView(withId(R.id.value_lecture_course_text)).check(matches(withText("Lectures are valuable: (0%)")));
        onView(withId(R.id.understandable_course_text)).check(matches(withText("Professor is understandable: (0%)")));
        onView(withId(R.id.extra_credit_course_text)).check(matches(withText("There is extra credit: (100%)")));
        onView(withId(R.id.help_sessions_course_text)).check(matches(withText("There are help sessions: (100%)")));
        onView(withId(R.id.electronics_course_text)).check(matches(withText("Electronics are allowed: (100%)")));
        onView(withId(R.id.textbook_course_text)).check(matches(withText("Textbook is required: (100%)")));
        onView(withId(R.id.toughness_course_text)).check(matches(withText("Toughness: (5.0/5)")));

    }

    @Test
    public void barsDisplayed() throws Exception {

        Thread.sleep(2000);
        onView(withId(R.id.value_lecture_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.understandable_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.extra_credit_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.help_sessions_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.electronics_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.textbook_course_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.toughness_course_bar)).check(matches(isDisplayed()));

    }

}
