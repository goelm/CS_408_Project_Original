package com.mango.cs_408_project;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by manasigoel on 2/11/17.
 */

public class SelectIntentInstrumentedTests {
    @Rule
    public IntentsTestRule<SelectReview> selectReviewActivityTestRule =
            new IntentsTestRule<SelectReview>(SelectReview.class);

    @Test
    public void instructorButtonStartsAddInstructorActivity() throws Exception{
        onView(withId(R.id.instructor_review)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), AddInstructorReview.class)));
        Thread.sleep(1000);

    }

    @Test
    public void courseButtonStartsAddCourseActivity() throws Exception{
        onView(withId(R.id.course_review)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), AddCourseReview.class)));
        Thread.sleep(1000);

    }

    @Test
    public void searchButtonStartsAddCourseActivity() throws Exception{
        onView(withId(R.id.search)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), Search.class)));
        Thread.sleep(1000);

    }

}
