package com.mango.cs_408_project;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ListView;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Elvin Uthuppan on 3/3/2017.
 *
 * modified by Kendall Z Bowles
 */

public class ViewProfReviews {

    @Rule
    public ActivityTestRule<Search> viewProfReviewsTest =
            new ActivityTestRule<Search>(Search.class);


    @Test
    public void addCourseShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("bUsTeR"));
        Thread.sleep(300);
        onView(withId(R.id.searchQueryField)).perform(pressKey(20)); //press down
        Thread.sleep(300);
        onView(withId(R.id.searchQueryField)).perform(pressKey(66)); //press enter
        Thread.sleep(2000);
        onView(withId(R.id.searchQueryField)).check(matches(withText("BUSTER DUNSMORE")));
        Thread.sleep(1000);
    }


    @Test
    public void viewReviews() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("BUSTER DUNSMORE"));
        onView(withId(R.id.searchSubmit)).perform(click());
        //onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(100);
        onView(withId(R.id.imageView3)).perform(swipeUp());
        Thread.sleep(300);
        onView(withId(R.id.view_reviews_button)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.reviews_name_text)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void addReview() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("BUSTER DUNSMORE"));
        onView(withId(R.id.searchSubmit)).perform(click());
        //onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(100);
        for(int i = 0; i<7; i++) {
            onView(withId(R.id.electronics_prof_text)).perform(swipeUp()); //THIS IS SO STUPID
        }
        Thread.sleep(1000);
        onView(withId(R.id.professor_info_addReview)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.first_name)).check(matches(withText("BUSTER")));
        onView(withId(R.id.last_name)).check(matches(withText("DUNSMORE")));

        //onView(withId(R.id.add_course_courseName)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void reviewFoundGoBack() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("BUSTER DUNSMORE"));
        onView(withId(R.id.searchSubmit)).perform(click());
        //onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.textProfName)).perform(pressBack());
        Thread.sleep(500);
        onView(withId(R.id.searchQueryField)).check(matches(withText("BUSTER DUNSMORE")));

        //onView(withId(R.id.add_course_courseName)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));

    }

    /*
    @Rule
    public ActivityTestRule<Search> viewProfReviewsTest =
            new ActivityTestRule<Search>(Search.class);



    @Test
    public void theAddCourseShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.professor_info_addReview_2)).perform(click()); //Clicks add review
        onView(withId(R.id.first_name)).check(matches(withText("BUSTER")));
        onView(withId(R.id.last_name)).check(matches(withText("DUNSMORE")));
        //onView(withId(R.id.listView)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void viewReviews() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        //intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void profLikeButtonClick() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Checks to see if reviews are displayed
        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button2))
                .perform(click());
        Thread.sleep(1000);
    }


    @Test
    public void aProfLikesChange() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("mr test"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.badge_notification2)).check(matches(withText("0"))); //Start of mr test

        Thread.sleep(2000);
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button2))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.badge_notification2)).check(matches(withText("1")));

        Thread.sleep(2000);
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Reset back to normal for next test
        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button2))
                .perform(click());

        Thread.sleep(1000);
    }
*/
}
