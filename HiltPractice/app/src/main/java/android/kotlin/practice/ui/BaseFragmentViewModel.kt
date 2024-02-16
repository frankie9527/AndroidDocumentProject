package android.kotlin.practice.ui

import android.kotlin.practice.data.TestRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseFragmentViewModel @Inject constructor(val  testRepository: TestRepository) : ViewModel() {

}