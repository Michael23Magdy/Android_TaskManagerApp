package com.example.android_taskmanagerapp.model

import com.example.android_taskmanagerapp.TaskType

data class StreakTask(
    override val description: String,
    override val title: String,
    override val type: TaskType = TaskType.STREAK,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0
) : AbstractTask() {
    override fun isDone(): Boolean {
        return false
    }
}