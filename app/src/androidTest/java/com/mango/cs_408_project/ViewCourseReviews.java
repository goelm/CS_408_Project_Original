package com.mango.cs_408_project;

import android.content.ComponentName;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by Elvin Uthuppan on 3/3/2017.
 */

public class ViewCourseReviews {

    @Rule
    public IntentsTestRule<Search> viewCourseReviewsTest =
            new IntentsTestRule<Search>(Search.class);


    @Test
    public void theAddProfShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("hist 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
    }


    @Test
    public void viewReviews() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("HIST 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_course_reviews)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void addReview() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("HIST 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        //onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.course_rating)).perform(swipeUp());
        Thread.sleep(1000);
        onView(withId(R.id.course_info_addReview)).perform(click());
        onView(withId(R.id.add_course_courseName)).check(matches(withText("HIST 371")));

        //onView(withId(R.id.add_course_courseName)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void reviewFoundGoBack() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("HIST 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        //onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.textCourseName)).perform(pressBack());
        Thread.sleep(500);
        onView(withId(R.id.searchQueryField)).check(matches(withText("HIST 371")));

        //onView(withId(R.id.add_course_courseName)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));

    }

    @Test
    public void courseLikeButtonClick() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("cs 180"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button))
                .perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void aaaCourseLikesChange() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("test 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.badge_notification)).check(matches(withText("0"))); //Start of test 101

        Thread.sleep(3000);
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.badge_notification)).check(matches(withText("1")));

        Thread.sleep(3000);
        onView(withId(R.id.listView)).check(matches(isDisplayed())); //Reset back to normal for next test
        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button))
                .perform(click());

        Thread.sleep(1000);

    }

    @Test
    public void courseSortOldToNew() throws Exception {

        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Oldest to newest")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Oldest to newest")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
    }

    @Test
    public void courseSortNewToOld() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Newest to oldest")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Newest to oldest")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void courseSortRatingHighToLow() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Rating (high to low)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Rating (high to low)")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void courseSortRatingLowToHigh() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Rating (low to high)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Rating (low to high)")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
    }

    @Test
    public void courseSortLikesHighToLow() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Helpfulness (high to low)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Helpfulness (high to low)")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void courseSortLikesLowToHigh() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("SORT 101"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_course_reviews)).perform(click());

        onView(withId(R.id.sort_menu_course)).perform(click());
        Thread.sleep(1000);

        onView(withText("Helpfulness (low to high)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_course)).check(matches(withSpinnerText("Helpfulness (low to high)")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(1)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(2)
                .onChildView(withId(R.id.courseComment2))
                .check(matches(withText("comment 3")));
    }
}
