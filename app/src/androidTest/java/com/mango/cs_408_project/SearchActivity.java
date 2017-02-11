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
 * Created by manasigoel on 2/11/17.
 */

public class SearchActivity {

    @Rule
    public ActivityTestRule<Search> searchActivityTestRule =
            new ActivityTestRule<Search>(Search.class);


    @Test
    public void searchWithSpecial() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("search!@#$%^"));
        onView(withId(R.id.success_fail_message)).check(matches(withText("Please try again without special characters")));
        Thread.sleep(1000);
    }

    @Test
    public void searchWithoutSpecial() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("search no special characters"));
        onView(withId(R.id.success_fail_message)).check(matches(withText("Good searh query!")));
        Thread.sleep(1000);
    }


}
