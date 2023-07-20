package com.example.rentmyproperty

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PropertyListFilterTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun canNavigateToPropertyListFragment() {
        onView(withId(R.id.btnPropertiesListView)).perform(ViewActions.click())
        onView(withId(R.id.filter_button))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun canNavigateToCameraFragment() {
        onView(withId(R.id.btncamera)).perform(ViewActions.click())
        onView(withId(R.id.button_take_picture))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun canFilterPropertyListSingleResult() {
        onView(withId(R.id.btnPropertiesListView)).perform(ViewActions.click())
        onView(withId(R.id.search_edit_text)).perform(ViewActions.typeText("bever"))
        onView(withId(R.id.filter_button)).perform(ViewActions.click())
        val recyclerView = onView(withId(R.id.properties_recycler_view))
        recyclerView.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertTrue(view is RecyclerView &&
                    view.adapter != null && (view.adapter?.itemCount ?: -1) > 0
            )

        }
    }

    @Test
    fun canFilterPropertyListNoResult() {
        onView(withId(R.id.btnPropertiesListView)).perform(ViewActions.click())
        onView(withId(R.id.search_edit_text)).perform(ViewActions.typeText("beverbeverbeverbever"))
        onView(withId(R.id.filter_button)).perform(ViewActions.click())
        val recyclerView = onView(withId(R.id.properties_recycler_view))
        recyclerView.check { view, noViewFoundException ->
            noViewFoundException?.apply {
                throw this
            }
            assertFalse(view is RecyclerView &&
                    view.adapter != null && (view.adapter?.itemCount ?: -1) > 0
            )
        }
    }
}
