package android.kotlin.practice.ui

import android.kotlin.practice.R
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HttpActivity : AppCompatActivity() {
    val viewModel: HttpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        findViewById<Button>(R.id.button_http).setOnClickListener {
            viewModel.getVideoList()
        }

    }
}