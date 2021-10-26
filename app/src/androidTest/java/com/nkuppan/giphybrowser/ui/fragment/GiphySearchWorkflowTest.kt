package com.nkuppan.giphybrowser.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.browser.util.MatcherUtil.childAtPosition
import com.nkuppan.giphybrowser.ui.activity.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GiphySearchWorkflowTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private fun findSearchContainerAddTextAndSubmit(aQueryString: String) {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.query),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.search_container),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(
            ViewActions.replaceText(aQueryString),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(1000)

        appCompatEditText.perform(pressImeActionButton())
    }

    @Test
    fun testIsSearchContainerAvailable() {
        onView(withId(R.id.search_container)).check(matches(isDisplayed()))
    }

    @Test
    fun testIsQueryEditTextAvailable() {
        onView(withId(R.id.query)).check(matches(isDisplayed()))
    }

    @Test
    fun testIsGifRadioButtonExist() {
        onView(withId(R.id.gif)).check(matches(isDisplayed()))
    }

    @Test
    fun testIsStickerRadioButtonExist() {
        onView(withId(R.id.stickers)).check(matches(isDisplayed()))
    }

    @Test
    fun testWithEmptyStringSearch() {

        onView(withId(R.id.query)).check(matches(isDisplayed()))

        findSearchContainerAddTextAndSubmit("  ")

        // Should not navigate to the next page without query text
        onView(withId(R.id.giphy_bottom_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun testLaunchGiphyBrowsPageFromSearchFragmentWithGifSearch() {

        onView(withId(R.id.gif)).check(matches(isDisplayed()))

        onView(withId(R.id.gif)).perform(click())

        findSearchContainerAddTextAndSubmit("Android")

        // Checking query field present in the giphy browser list screen
        onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))
    }

    @Test
    fun testLaunchGiphyBrowsPageFromSearchFragmentWithStickerSearch() {

        onView(withId(R.id.stickers)).check(matches(isDisplayed()))

        onView(withId(R.id.stickers)).perform(click())

        findSearchContainerAddTextAndSubmit("Android")

        // Checking query field present in the giphy browser list screen
        onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))
    }
}
