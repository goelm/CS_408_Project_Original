package com.mango.cs_408_project;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by KennyZheng on 2/11/17.
 */

public class AddInstructorReviewTest {


    @Rule
    public ActivityTestRule<AddInstructorReview> addInstructorReviewClass =
            new ActivityTestRule<AddInstructorReview>(AddInstructorReview.class);


    // Instructor Button

    @Test
    public void instructorButton() throws Exception {
        onView(withId(R.id.instructor)).perform(click());
        onView(withId(R.id.teachingassistant)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }

    @Test
    public void instructorButton2() throws Exception {
        onView(withId(R.id.teachingassistant)).perform(click());
        onView(withId(R.id.instructor)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }


    // Accessbility Button

    @Test
    public void accessButton() throws Exception {
        onView(withId(R.id.ezAccess)).perform(scrollTo(), click());
        onView(withId(R.id.hardAccess)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void accessButton2() throws Exception {
        onView(withId(R.id.hardAccess)).perform(scrollTo(), click());
        onView(withId(R.id.ezAccess)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }


    // Extra Credit Button

    @Test
    public void extraCreditButton() throws Exception {
        onView(withId(R.id.yesButton1)).perform(scrollTo(), click());
        onView(withId(R.id.noButton1)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void extraCreditButton2() throws Exception {
        onView(withId(R.id.noButton1)).perform(scrollTo(), click());
        onView(withId(R.id.yesButton1)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }


    // Toughness Buttons

    @Test
    public void toughnessButton() throws Exception {
        onView(withId(R.id.diffButton1)).perform(scrollTo(), click());
        onView(withId(R.id.diffButton1)).check(matches(isChecked()));
        onView(withId(R.id.diffButton2)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton3)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton4)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton5)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void toughnessButton2() throws Exception {
        onView(withId(R.id.diffButton2)).perform(scrollTo(), click());
        onView(withId(R.id.diffButton2)).check(matches(isChecked()));
        onView(withId(R.id.diffButton1)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton3)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton4)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton5)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void toughnessButton3() throws Exception {
        onView(withId(R.id.diffButton3)).perform(scrollTo(), click());
        onView(withId(R.id.diffButton3)).check(matches(isChecked()));
        onView(withId(R.id.diffButton2)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton1)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton4)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton5)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void toughnessButton4() throws Exception {
        onView(withId(R.id.diffButton4)).perform(scrollTo(), click());
        onView(withId(R.id.diffButton4)).check(matches(isChecked()));
        onView(withId(R.id.diffButton2)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton3)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton1)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton5)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void toughnessButton5() throws Exception {
        onView(withId(R.id.diffButton5)).perform(scrollTo(), click());
        onView(withId(R.id.diffButton5)).check(matches(isChecked()));
        onView(withId(R.id.diffButton2)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton3)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton4)).check(matches(not(isChecked())));
        onView(withId(R.id.diffButton1)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }


    // Misc Button

    @Test
    public void miscButton() throws Exception {
        onView(withId(R.id.yesButton2)).perform(scrollTo(), click());
        onView(withId(R.id.noButton2)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }

    @Test
    public void miscButton2() throws Exception {
        onView(withId(R.id.noButton2)).perform(scrollTo(), click());
        onView(withId(R.id.yesButton2)).check(matches(not(isChecked())));
        Thread.sleep(1000);
    }
}
