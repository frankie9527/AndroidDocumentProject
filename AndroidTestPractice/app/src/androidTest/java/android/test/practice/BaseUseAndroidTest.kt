package android.test.practice


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author: Frankie
 * @Date: 2024/4/5
 * @Description: viewDemoActivity 测试
 */
@RunWith(AndroidJUnit4::class)
class BaseUseAndroidTest {
    // A valid string with a valid ending
    private var tvHint: String? = null
    @Before
    fun initValidStrings() {
        // Produce a string with valid ending.
        tvHint = "input can not be empty"


    }
    // 检查 EditText hint
    @Test
    fun editText_hint_check() {
        ActivityScenario.launch<BaseUseActivity>(BaseUseActivity::class.java).use { scenario ->
            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
            val hint = appContext.resources.getText(R.string.hint)
            onView(withId(R.id.ed)).check(matches(HintMatcher.withHint(hint.toString())))
        }
    }

    @Test
    fun input_empty_check() {
        ActivityScenario.launch<BaseUseActivity>(BaseUseActivity::class.java).use { scenario ->
            //输入空字符串
            onView(withId(R.id.ed)).perform(typeText(""), closeSoftKeyboard())
            // 模拟点击
            onView(withId(R.id.bt_check)).perform(click())
            //判断 textView 是否显示
            onView(withId(R.id.tv)).check(matches(isDisplayed()))
            //判断 textView 文字是否一样
            onView(withId(R.id.tv)).check(matches(withText(tvHint)))
        }
    }

    @Test
    fun input_not_empty_check() {
        ActivityScenario.launch<BaseUseActivity>(BaseUseActivity::class.java).use { scenario ->
            //输入空字符串
            onView(withId(R.id.ed)).perform(typeText("hello"), closeSoftKeyboard())
            // 模拟点击
            onView(withId(R.id.bt_check)).perform(click())
            //判断 textView 是否显示
            onView(withId(R.id.tv)).check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun openWeb() {
        ActivityScenario.launch<BaseUseActivity>(BaseUseActivity::class.java).use { scenario ->
            onView(
                withId(R.id.img_open_web)
            ).perform(click())
        }
    }
}