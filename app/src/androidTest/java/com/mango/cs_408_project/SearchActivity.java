package com.mango.cs_408_project;

import android.content.ComponentName;
import android.support.test.espresso.core.deps.guava.collect.TreeBasedTable;
import android.support.test.espresso.core.deps.guava.util.concurrent.ThreadFactoryBuilder;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.Tracer;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiSelector;
import android.widget.AutoCompleteTextView;
import android.widget.TabHost;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by manasigoel on 2/11/17.
 */

public class SearchActivity {

    @Rule
    public IntentsTestRule<Search> searchActivityTestRule =
            new IntentsTestRule<Search>(Search.class);


    @Test
    public void defaultSearch() throws Exception {
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("Search field can not be empty")));
        Thread.sleep(1000);
    }


    @Test
    public void searchWithSpecial() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("search!@#$%^"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("Please try again without special characters")));
        Thread.sleep(1000);
    }


    //TESTS AFTER NEW SEARCH FUNCTIONALITY

    @Test
    public void search_addNewCourse() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("noSearch"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        Thread.sleep(1000);
        onView(withId(R.id.new_course_button)).perform(scrollTo(), click());
        intended(hasComponent(new ComponentName(getTargetContext(), AddCourseReview.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_addNewInstructor() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("noSearch"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        Thread.sleep(1000);
        onView(withId(R.id.new_instructor_button)).perform(scrollTo(), click());
        intended(hasComponent(new ComponentName(getTargetContext(), AddInstructorReview.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_courseLower() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("hist 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_mix() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("hIsT 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_courseUpper() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("HIST 371"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_allLowerCase() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("firsttest lasttest"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), ProfDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_mixUpperCase() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("FiRsTtEsT lAsTtEsT"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), ProfDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_allUpperCase() throws Exception {
        onView(withId(R.id.searchQueryField)).perform(typeText("FIRSTTEST LASTTEST"));
        onView(withId(R.id.searchSubmit)).perform(click());
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), ProfDisplay.class)));
        Thread.sleep(1000);
    }

    @Test
    public void search_autofill_prof() throws Exception {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        onView(withId(R.id.searchQueryField)).perform(typeText("KEN"));
        Thread.sleep(300);
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(allOf(withId(R.id.searchQueryField))).check(matches(withText("KENDALL BOWLES")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), ProfDisplay.class)));
    }

    @Test
    public void search_autofill_course() throws Exception {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        onView(withId(R.id.searchQueryField)).perform(typeText("hist"));
        Thread.sleep(300);
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(allOf(withId(R.id.searchQueryField))).check(matches(withText("HIST 371")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        intended(hasComponent(new ComponentName(getTargetContext(), CourseDisplay.class)));
    }

    @Test
    public void search_autofill_fail() throws Exception {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        onView(withId(R.id.searchQueryField)).perform(typeText("KZB"));
        Thread.sleep(300);
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(1000);
        onView(allOf(withId(R.id.searchQueryField))).check(matches(withText("KZB")));
        onView(withId(R.id.searchSubmit)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.success_fail_message)).check(matches(withText("does not exist")));
        Thread.sleep(1000);
    }

}
