package android.kotlin.practice.ui

import android.kotlin.practice.data.dao.User
import android.kotlin.practice.data.dao.UserRepository
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaoViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
     fun insert(name: String, num: String) {
        viewModelScope.launch(Dispatchers.IO){
            val users = repository.insert(User(null,name,num));
        }
    }

    fun query() {
        viewModelScope.launch(Dispatchers.IO){
            val users = repository.getAll();
            Log.e("DaoViewModel", users.toString());
        }

    }

}