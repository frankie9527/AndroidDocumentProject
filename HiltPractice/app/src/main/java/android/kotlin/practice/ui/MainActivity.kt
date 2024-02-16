package android.kotlin.practice.ui

import android.content.Intent
import android.kotlin.practice.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.bt_base).setOnClickListener {
            startActivity(Intent(this, BaseActivity::class.java))
        }
        findViewById<View>(R.id.bt_dao).setOnClickListener {
            startActivity(Intent(this, DaoActivity::class.java))
        }
        findViewById<View>(R.id.bt_http).setOnClickListener {
            startActivity(Intent(this, HttpActivity::class.java))
        }
    }
}