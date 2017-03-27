package com.mango.cs_408_project;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ListView;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.espresso.intent.rule.IntentsTestRule;
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
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
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
    public IntentsTestRule<Search> viewProfReviewsTest =
            new IntentsTestRule<Search>(Search.class);

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
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.textProfName)).perform(pressBack());
        Thread.sleep(500);
        onView(withId(R.id.searchQueryField)).check(matches(withText("BUSTER DUNSMORE")));

    }

    @Test
    public void theAddProfShowsFillsName() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.instructor_rating)).perform(swipeUp()); //To fix off screen issue
        onView(withId(R.id.instructor_rating)).perform(swipeUp()); //To fix off screen issue

        Thread.sleep(1000);
        onView(withId(R.id.professor_info_addReview)).perform(click()); //Clicks add review
        Thread.sleep(1000);
        onView(withId(R.id.first_name)).check(matches(withText("BUSTER")));
        onView(withId(R.id.last_name)).check(matches(withText("DUNSMORE")));
        Thread.sleep(1000);
    }

    @Test
    public void profLikeButtonClick() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("buster dunsmore"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.textProfName)).perform(swipeUp()); //To fix off screen issue
        Thread.sleep(2000);
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
                .onChildView(withId(R.id.badge_notification2)).check(matches(withText("1"))); //Start of mr test

        Thread.sleep(2000);
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button2))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.badge_notification2)).check(matches(withText("2")));

        Thread.sleep(2000);
        onView(withId(R.id.prof_listView_reviews)).check(matches(isDisplayed())); //Reset back to normal for next test
        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.likes_button2))
                .perform(click());

        Thread.sleep(1000);
    }

    @Test
    public void profSortOldToNew() throws Exception {

        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Oldest to newest")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Oldest to newest")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
    }

    @Test
    public void profSortNewToOld() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Newest to oldest")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Newest to oldest")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void profSortRatingHighToLow() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Rating (high to low)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Rating (high to low)")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void profSortRatingLowToHigh() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Rating (low to high)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Rating (low to high)")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
    }

    @Test
    public void profSortLikesHighToLow() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Helpfulness (high to low)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Helpfulness (high to low)")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
    }

    @Test
    public void profSortLikesLowToHigh() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("PROF SORT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.view_reviews_button)).perform(click());

        onView(withId(R.id.sort_menu_prof)).perform(click());
        Thread.sleep(1000);

        onView(withText("Helpfulness (low to high)")).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.sort_menu_prof)).check(matches(withSpinnerText("Helpfulness (low to high)")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(0)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 1")));
        Thread.sleep(1000);

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(1)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 2")));

        onData(anything()).inAdapterView(withId(R.id.prof_listView_reviews))
                .atPosition(2)
                .onChildView(withId(R.id.profComment2))
                .check(matches(withText("comment 3")));
    }

}
