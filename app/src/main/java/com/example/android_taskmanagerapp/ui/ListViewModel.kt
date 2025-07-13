package com.example.android_taskmanagerapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class ListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    fun addTask(task: AbstractTask) {
        _uiState.value = _uiState.value.copy(tasks = _uiState.value.tasks + task)
    }
    fun deleteTask(task: AbstractTask) {
        _uiState.value = _uiState.value.copy(tasks = _uiState.value.tasks - task)
    }

    fun editTask(newTask: AbstractTask, id: UUID){
        Log.d("TaskB", "editTask: ${_uiState.value.tasks}")
        Log.d("TaskB", "editTask: $newTask")
        Log.d("TaskB", "editTask: ${_uiState.value.tasks[0].id}")

        Log.d("TaskB", "editTask: ${newTask.id}")

        _uiState.value = _uiState.value.copy(tasks = _uiState.value.tasks.map {
            if (it.id == id) newTask else it
        })
        Log.d("TaskB", "editTask: ${_uiState.value.tasks}")
    }

    fun editTask(oldTask: OneTimeTask, newTask: OneTimeTask) {
        Log.d("OneTime", "editTask: ${oldTask.id}")
        val newTaskSameId = oldTask.copy(title = newTask.title, description = newTask.description)
        Log.d("OneTime", "editTask: ${newTaskSameId.id}")
        editTask(newTaskSameId, oldTask.id)
    }

    fun editTask(oldTask: ProgressTask, newTask: ProgressTask) {
        val newTaskSameId = oldTask.copy(title = newTask.title, description = newTask.description, numSubtasks = newTask.numSubtasks)
        Log.d("Progress", "editTask: $newTaskSameId")
        editTask(newTaskSameId, oldTask.id)
    }
    fun editTask(oldTask: StreakTask, newTask: StreakTask) {
        val newTaskSameId = oldTask.copy(title = newTask.title, description = newTask.description)
        Log.d("Streak", "editTask: $newTaskSameId")
        editTask(newTaskSameId, oldTask.id)
    }

}