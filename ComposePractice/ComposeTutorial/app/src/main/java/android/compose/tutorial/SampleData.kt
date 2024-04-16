package android.compose.tutorial


import android.compose.practice.data.Message


/**
 * @author: Frankie
 * @Date: 2024/4/9
 * @Description:
 */
class SampleData{
    companion object{
       val conversationSample=listOf(
        Message("frankie", "today is a nice day， i love stay with u     today is a nice day， i love stay with u"),
        Message("sunny", "yes,let go out with me     yes,let go out with me"),
        Message("frankie", "no, i want to play game     no, i want to play game"),
        Message("sunny", "fuck,you deserve to be single      fuck,you deserve to be single"),
        Message("frankie", "thx"),
        Message("sunny", "How did I get to know you     How did I get to know you")
        )
    }
}
