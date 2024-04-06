package android.test.practice

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * @author: Frankie
 * @Date: 2024/4/6
 * @Description:
 */

object HintMatcher {
    fun withHint(substring: String): Matcher<View> {
        return withHint(Matchers.`is`(substring))
    }
    fun withHint(stringMatcher: Matcher<String>): Matcher<View> {
        checkNotNull<Matcher<String>>(stringMatcher)
        return object : BoundedMatcher<View, EditText>(EditText::class.java) {
            public override fun matchesSafely(view: EditText): Boolean {
                val hint = view.hint
                return hint != null && stringMatcher.matches(hint.toString())
            }

            override fun describeTo(description: Description) {
                description.appendText("with hint: ")
                stringMatcher.describeTo(description)
            }
        }
    }
}

