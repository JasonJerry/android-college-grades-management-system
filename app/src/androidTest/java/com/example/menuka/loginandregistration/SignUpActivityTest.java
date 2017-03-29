package com.example.menuka.loginandregistration;

import android.support.test.espresso.core.deps.guava.util.concurrent.ExecutionError;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by menuka on 3/30/17.
 */

@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {
    private String mStringToBeTyped;
    // fire up this activity for testing
    @Rule
    public ActivityTestRule<SignupActivity> signupActivityActivityTestRule =
            new ActivityTestRule<SignupActivity>(SignupActivity.class, true, false);

    @Before
    public void initValidString(){
        mStringToBeTyped = "harry@gmail.com";
    }

    @Test
    public void changeText_emailAddress(){
        // Type the email and press register
        onView(withId(R.id.email_edit_text))
                .perform(typeText(mStringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        // should remain in the same activity because passwors is not entered
        onView(withId(R.id.email_edit_text))
                .check(matches(withText(mStringToBeTyped)));
    }

}
