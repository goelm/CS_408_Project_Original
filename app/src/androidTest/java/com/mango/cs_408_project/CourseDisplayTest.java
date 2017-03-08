package com.mango.cs_408_project;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;

/**
 * Created by KennyZheng on 3/3/17.
 */

public class CourseDisplayTest {

    @Rule
    public IntentsTestRule<CourseDisplay> courseDisplayTestRule =
            new IntentsTestRule<CourseDisplay>(CourseDisplay.class);

}
