package com.mango.cs_408_project;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kendall Z Bowles
 */

public class MainMenu {

    @Rule
    public IntentsTestRule<SelectReview> MainTestRule =
            new IntentsTestRule<SelectReview>(SelectReview.class);


    @Test
    public void instructorReview() throws Exception {
        Thread.sleep(300);
        onView(withId(R.id.instructor_review_button)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.imageProf)).check(matches(isDisplayed()));
        Thread.sleep(700);
    }

    @Test
    public void courseReview() throws Exception {
        Thread.sleep(300);
        onView(withId(R.id.course_review_button)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.add_course_courseName)).check(matches(isDisplayed()));
        Thread.sleep(700);
    }

    @Test
    public void search() throws Exception {
        Thread.sleep(300);
        onView(withId(R.id.search_select_button)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.searchQueryField)).check(matches(isDisplayed()));
        Thread.sleep(700);
    }
}
