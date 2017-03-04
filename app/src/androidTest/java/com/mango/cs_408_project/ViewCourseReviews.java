package com.mango.cs_408_project;

import android.content.ComponentName;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Elvin Uthuppan on 3/3/2017.
 */

public class ViewCourseReviews {

    @Rule
    public ActivityTestRule<Search> viewCourseReviewsTest =
            new ActivityTestRule<Search>(Search.class);

    @Test
    public void addCourseShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("hist 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_course_reviews)).perform(click());
        onView(withId(R.id.add_review_course_reviews)).perform(click()); //Clicks add review

        onView(withId(R.id.add_course_courseName)).check(matches(withText("HIST 371")));

        //onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }


    @Test
    public void viewReviews() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("hist 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_course_reviews)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }
}
