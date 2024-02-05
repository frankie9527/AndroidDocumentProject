package android.livedata.practice

import androidx.activity.viewModels
import android.livedata.practice.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {
    val practiceViewModel: PracticeViewModel by viewModels {
        PracticeVMFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.lifecycleOwner = this;
        binding.viewmodel = practiceViewModel;
    }
}