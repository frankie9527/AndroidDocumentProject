package android.kotlin.practice.ui

import android.kotlin.practice.network.RetrofitService
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HttpViewModel @Inject constructor(private val service: RetrofitService) : ViewModel() {

    fun getVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val map = service.getMovies();
                Log.e("HttpViewModel","map"+map.toString());
            } catch (error:  Exception) {
                Log.e("HttpViewModel",error.toString());
            }
        }
    }
}