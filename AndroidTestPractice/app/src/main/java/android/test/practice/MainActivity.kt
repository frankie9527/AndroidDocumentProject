package android.test.practice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun go2Base(view: View?) {
        val intent = Intent(this,BaseUseActivity::class.java)
        startActivity(intent)
    }
    fun go2Recycler(view: View?) {
        val intent = Intent(this,RecyclerActivity::class.java)
        startActivity(intent)
    }
}