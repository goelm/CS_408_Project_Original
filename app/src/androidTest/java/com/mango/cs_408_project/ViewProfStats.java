package com.mango.cs_408_project;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by manasigoel on 3/3/17.
 */

public class ViewProfStats {
    @Rule
    public ActivityTestRule<ProfDisplay> profDisplayActivityTestRule =
            new ActivityTestRule<ProfDisplay>(ProfDisplay.class){
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, MainActivity.class);
                    result.putExtra("user_input", "TEST TEST");
                    return result;
                }
            };

    @Test
    public void statsDisplayed() throws Exception {

        Thread.sleep(2000);
        onView(withId(R.id.textProfName)).check(matches(withText("TEST TEST")));
        onView(withId(R.id.proORta)).check(matches(withText("Instructor")));
        onView(withId(R.id.num_reviews_text)).check(matches(withText("1 reviews")));
        onView(withId(R.id.value_lecture_heading)).check(matches(withText("Lectures are valuable: (0%)")));
        onView(withId(R.id.understandable_text)).check(matches(withText("Professor is understandable: (0%)")));
        onView(withId(R.id.extra_credit_text)).check(matches(withText("There is extra credit: (100%)")));
        onView(withId(R.id.help_sessions_prof)).check(matches(withText("There are help sessions: (100%)")));
        onView(withId(R.id.electronics_prof_text)).check(matches(withText("Electronics are allowed: (100%)")));
        onView(withId(R.id.toughness_prof_text)).check(matches(withText("Toughness: (5.0/5)")));

    }

    @Test
    public void barsDisplayed() throws Exception {

        Thread.sleep(2000);
        onView(withId(R.id.value_lecture_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.understandable_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.extra_credit_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.help_sessions_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.electronics_prof_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.toughness_bar)).check(matches(isDisplayed()));

    }
}
