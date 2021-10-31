package com.nkuppan.giphybrowser.ui.fragment


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.browser.util.MatcherUtil.childAtPosition
import com.nkuppan.giphybrowser.presentation.MainActivity
import com.nkuppan.giphybrowser.presentation.searchlist.GiphyListViewHolder
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class GiphyImageAttributesDtoDetailsFragmentTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun giphyImageDetailsFragmentTest() {
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
        appCompatEditText.perform(replaceText("Android"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.query), withText("Android"),
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
        appCompatEditText2.perform(pressImeActionButton())

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        Thread.sleep(5000)

        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<GiphyListViewHolder>(
                0,
                click()
            )
        )

        val appCompatImageButton = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        appCompatImageButton.perform(click())

        // Checking query field present in the giphy browser list screen
        onView(withId(R.id.query)).check(matches(isDisplayed()))
    }
}
