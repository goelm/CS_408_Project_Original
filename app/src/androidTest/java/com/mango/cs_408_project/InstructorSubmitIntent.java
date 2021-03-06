package com.mango.cs_408_project;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by manasigoel on 2/11/17.
 */

public class InstructorSubmitIntent {

    @Rule
    public IntentsTestRule<AddInstructorReview> addInstructorReviewIntentsTestRule =
            new IntentsTestRule<AddInstructorReview>(AddInstructorReview.class);

    @Test
    public void submitCourseButtonStartsSelectActivity() throws Exception{
        onView(withId(R.id.submitBut)).perform(scrollTo(), click());
        //intended(hasComponent(new ComponentName(getTargetContext(), SelectReview.class)));
        Thread.sleep(1000);

    }

}
