package android.kotlin.practice.ui

import android.kotlin.practice.App
import android.kotlin.practice.R
import android.kotlin.practice.data.dao.User
import android.kotlin.practice.data.dao.UserDao
import android.kotlin.practice.data.dao.UserDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DaoActivity : AppCompatActivity() {
    val viewModel: DaoViewModel by viewModels()
    var dao: UserDao? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dao)
        initView();
    }

    private fun initView() {
        val ed_name = findViewById<EditText>(R.id.ed_name);
        val ed_num = findViewById<EditText>(R.id.ed_num);
        findViewById<Button>(R.id.bt_insert).setOnClickListener {
            val name = ed_name.text.trim().toString();
            val num = ed_num.text.trim().toString();
            viewModel.insert(name, num);

        }
        findViewById<Button>(R.id.bt_query).setOnClickListener {
            viewModel.query()
        }
    }
}