package com.devindi.mapper.demo;

import android.Manifest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by devindi on 06.03.18.
 */
@RunWith(AndroidJUnit4.class)
public class ClickPerfTest {

    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    @Rule
    public ActivityTestRule<BenchmarkActivity> activityTestRule = new ActivityTestRule<>(BenchmarkActivity.class);

    @Test
    public void simpleMappingTest() {
        onView(withId(R.id.btn_simple)).perform(click());
    }

    @Test
    public void renameMappingTest() {
        onView(withId(R.id.btn_rename)).perform(click());
    }

    @Test
    public void complexMappingTest() {
        onView(withId(R.id.btn_order)).perform(click());
    }
}
