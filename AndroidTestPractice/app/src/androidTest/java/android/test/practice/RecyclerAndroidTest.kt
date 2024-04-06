package android.test.practice

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView


import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule

/**
 * @author: Frankie
 * @Date: 2024/4/6
 * @Description:
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class RecyclerAndroidTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(RecyclerActivity::class.java)

    @Test
    fun scrollTo40Item_checkItsText() {//
       // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recylcer))
            .perform(RecyclerViewActions.scrollToPosition<UnitRecyclerAdapter.MyHolder>(40))
        // 滚动到40 检查是否显示出来
        onView(withText("this is the " + 40 + " item")).check(matches(isDisplayed()))

    }
}