package android.test.practice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class BaseUseActivity : AppCompatActivity() {
    private var ed: EditText? = null;
    private var tv: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_demo)
        ed = findViewById(R.id.ed);
        tv = findViewById(R.id.tv);
    }

    fun btCheck(view: View?) {
        val str=ed?.text.toString().trim();
        if (TextUtils.isEmpty(str)){
            tv?.visibility=View.VISIBLE;
            tv?.text="input can not be empty";
        }else{
            tv?.visibility=View.GONE;
        }
    }

    fun openWeb(view: View?) {
        // fire a intent
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("http://www.baidu.com"))
        startActivity(intent)
    }
}