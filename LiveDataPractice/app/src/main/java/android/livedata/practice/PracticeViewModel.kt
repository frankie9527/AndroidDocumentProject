package android.livedata.practice


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PracticeViewModel(
    val dataSource: DataSource
) : ViewModel() {
    val count = dataSource.getCurrentCount();
    val user = dataSource.getUser();
    val feel = dataSource.getFeel()

     val status = dataSource.loadStatus

    fun getLoadStatus() {
        viewModelScope.launch {
            dataSource.fetchStatus()
        }
    }


}

/**
 * Factory for [PracticeViewModel].
 */
object PracticeVMFactory : ViewModelProvider.Factory {

    val dataSource = PracticeDataSource(Dispatchers.IO);

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PracticeViewModel(dataSource) as T
    }
}

interface DataSource {
    fun getCurrentCount(): LiveData<Long>
    fun getUser(): LiveData<String>
    fun getFeel(): LiveData<String>
    val loadStatus: LiveData<String>
    suspend fun fetchStatus()
}