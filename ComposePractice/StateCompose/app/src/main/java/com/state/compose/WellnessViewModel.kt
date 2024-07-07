package com.state.compose
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


/**
 * @author: Frankie
 * @Date: 2024/7/8
 * @Description:
 */


class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }
}

