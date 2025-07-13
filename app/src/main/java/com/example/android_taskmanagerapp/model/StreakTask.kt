package com.example.android_taskmanagerapp.model

import java.util.UUID

data class StreakTask(
    override val description: String,
    override val title: String,
    val currentStreak: Int,
    val longestStreak: Int
) : TaskInterface() {
    override fun isDone(): Boolean {
        return false
    }
}