package com.example.menuka.loginandregistration;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by menuka on 3/5/17.
 */

@RunWith(AndroidJUnit4.class)
public class NotesScreenTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void clickLoginButton_validates_inputs() throws Exception {
        onView(withId(R.id.btn_login))
                .perform(click());
        onView(withId(R.id.btn_login))
                .check(matches(isDisplayed()));
    }
}
