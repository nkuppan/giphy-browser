package com.nkuppan.giphybrowser.ui.fragment

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.browser.util.MatcherUtil
import com.nkuppan.giphybrowser.presentation.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GiphyBrowserListFragmentTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun launchGiphyBrowsPageFromSearchFragment() {

        val appCompatEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.query),
                MatcherUtil.childAtPosition(
                    MatcherUtil.childAtPosition(
                        ViewMatchers.withId(R.id.search_container),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        appCompatEditText.perform(
            ViewActions.replaceText("Android"),
            ViewActions.closeSoftKeyboard()
        )

        val appCompatEditText2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.query), ViewMatchers.withText("Android"),
                MatcherUtil.childAtPosition(
                    MatcherUtil.childAtPosition(
                        ViewMatchers.withId(R.id.search_container),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )

        Thread.sleep(1000)

        appCompatEditText2.perform(ViewActions.pressImeActionButton())

        // Checking query field present in the giphy browser list screen
        Espresso.onView(ViewMatchers.withId(R.id.query))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.progressIndicator))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(5000)
    }
}
