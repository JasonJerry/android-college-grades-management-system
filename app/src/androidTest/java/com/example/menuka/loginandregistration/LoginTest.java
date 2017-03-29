package com.example.menuka.loginandregistration;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by menuka on 3/30/17.
 */

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    public ActivityTestRule<SignupActivity> signupActivityActivityTestRule =
            new ActivityTestRule<>(SignupActivity.class);

    @Test
    public void clickLoginButton_validatesInputs() throws Exception{
        // clicking register without filling details
        onView(withId(R.id.sign_up_button))
                .perform(click());
        onView(withId(R.id.sign_up_button))
                .check(matches(isDisplayed()));
    }
}
