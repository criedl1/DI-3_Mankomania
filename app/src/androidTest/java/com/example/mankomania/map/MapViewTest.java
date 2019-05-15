package com.example.mankomania.map;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;

import com.example.mankomania.MainActivity;
import com.example.mankomania.R;


import org.hamcrest.Matcher;
import org.junit.Rule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MapViewTest{
   /* public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test //2131230840
    public void test() {
        try {
        onView(withText("CREATE LOBBY")).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.next1)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.back1)).perform(click());
        Thread.sleep(1000);
         onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_start)));
         onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_aktie1)));
        Thread.sleep(1000);
        onView(withId(R.id.next1)).perform(click());
            onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_lindwurm)));
            onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_lottery)));
        Thread.sleep(1000);
        onView(withId(R.id.next1)).perform(click());
            onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_casino)));
            onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_getsomemoney)));
        Thread.sleep(1000);
        onView(withId(R.id.next1)).perform(click());
            onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_alterplatz)));
            onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_aktie2)));
        onView(withId(R.id.next1)).perform(click());
            onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_horserace)));
            onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_stadium)));
        onView(withId(R.id.next1)).perform(click());
            onView(withId(R.id.imageViewStart)).check(matches(withDrawable(R.drawable.field_casino)));
            onView(withId(R.id.imageView2)).check(matches(withDrawable(R.drawable.field_alterplatz)));
        }
        catch (Exception e) {

        }
    }*/

 }

