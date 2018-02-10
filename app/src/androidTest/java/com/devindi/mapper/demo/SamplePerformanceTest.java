package com.devindi.mapper.demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by devindi on 11.02.18.
 */
@RunWith(AndroidJUnit4.class)
public class SamplePerformanceTest {

    @Rule
    public ActivityTestRule<BenchmarkActivity> rule = new ActivityTestRule<>(BenchmarkActivity.class);

    @Test
    public void performanceTest() {
        onView(withId(R.id.root)).perform(click());
    }
}
