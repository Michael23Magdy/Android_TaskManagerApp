package com.example.android_taskmanagerapp.ui

import androidx.lifecycle.ViewModel
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import kotlin.math.max

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
        _uiState.value = _uiState.value.copy(tasks = _uiState.value.tasks.map {
            if (it.id == id) newTask else it
        })
    }

    fun editTask(oldTask: AbstractTask, newTask: AbstractTask){
        editTask(newTask, oldTask.id)
    }

    fun doneTask(task: OneTimeTask) {
        val newTask = task.copy(done = !task.done)
        editTask(newTask, task.id)
    }

    fun increaseStreak(task: StreakTask) {
        val newTask = task.copy(
            currentStreak = task.currentStreak.inc(),
            longestStreak = max(task.currentStreak.inc(), task.longestStreak)
        )
        editTask(newTask, task.id)
    }
    fun resetStreak(task: StreakTask) {
        val newTask = task.copy(currentStreak = 0)
        editTask(newTask, task.id)
    }

    fun updateProgress(task: ProgressTask, newProgress: Int){
        val newTask = task.copy(progressValue = newProgress)
        editTask(newTask, task.id)
    }

}