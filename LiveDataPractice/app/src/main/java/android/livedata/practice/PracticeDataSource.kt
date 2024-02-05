package android.livedata.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class PracticeDataSource(private val ioDispatcher: CoroutineDispatcher) : DataSource {
    override fun getCurrentCount(): LiveData<Long> {
        return liveData {
            while (true) {
                emit(System.currentTimeMillis())
                delay(1000)
            }
        }
    }

    private val UserName = listOf("Tom", "Frankie", "Bobo")
    private val UserFeel = listOf("good", "bad")
    override fun getUser(): LiveData<String> {
        var counter = 0;
        return liveData {
            while (true) {
                emit(UserName[counter % UserName.size])
                counter++
                delay(2000)
            }
        }
    }

    override fun getFeel(): LiveData<String> {
        var counter = 0;
        return liveData {
            while (true) {
                emit(UserFeel[counter % UserFeel.size])
                counter++
                delay(2000)
            }
        }
    }

    private val _loadStatus = MutableLiveData<String>("complete");
    override val loadStatus: LiveData<String> =_loadStatus;
    override suspend fun fetchStatus() {
        withContext(Dispatchers.Main) {
            _loadStatus.value = "loading"
            _loadStatus.value = simulateNetworkDataFetch()
        }
    }

    private suspend fun simulateNetworkDataFetch(): String = withContext(ioDispatcher) {
        delay(3000)
        "complete"
    }
}