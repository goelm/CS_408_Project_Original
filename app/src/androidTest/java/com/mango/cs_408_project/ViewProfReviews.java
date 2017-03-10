package com.mango.cs_408_project;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Elvin Uthuppan on 3/3/2017.
 */

public class ViewProfReviews {

    @Rule
    public ActivityTestRule<Search> viewProfReviewsTest =
            new ActivityTestRule<Search>(Search.class);

    /*
    @Test
    public void addCourseShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.professor_info_addReview_2)).perform(click()); //Clicks add review
        onView(withId(R.id.first_name)).check(matches(withText("buster")));
        onView(withId(R.id.last_name)).check(matches(withText("dunsmore")));
        //onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }


    @Test
    public void viewReviews() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }
    */
    @Test
    public void profLikeButtonClick() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
       // onView(withId(R.id.view_reviews_button))
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }
}
