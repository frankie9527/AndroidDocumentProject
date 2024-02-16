package android.kotlin.practice.ui

import android.kotlin.practice.R
import android.kotlin.practice.data.TestRepository
import android.kotlin.practice.di.ActivityScope
import android.kotlin.practice.di.AppScope
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    private val TAG = this.javaClass.toString()

    @AppScope
    @Inject
    lateinit var appHash: String

    @ActivityScope
    @Inject
    lateinit var activityHash: String

    @Inject
    lateinit var testRepository: TestRepository;
    val viewModel: BaseActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder, BaseFragment())
            .commit()

        Log.v(TAG, "app : $appHash")
        Log.v(TAG, "activity : $activityHash")
        Log.v(TAG, "testRepository: ${testRepository}")
        Log.v(TAG, "activity vm: $viewModel")
        Log.v(TAG, "activity vm repo: ${viewModel.test}")

    }
}