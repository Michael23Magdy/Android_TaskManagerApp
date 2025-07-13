package com.example.android_taskmanagerapp.model

import java.util.UUID
import kotlin.math.min

data class ProgressTask(
    override val title: String,
    override val description: String,
    var progressValue: Int,
    var numSubtasks: Int
) : TaskInterface(){
    fun progressPercentage(): Int {
        return min(progressValue * 100 / numSubtasks, 100)
    }

    override fun isDone(): Boolean {
        return progressValue >= numSubtasks
    }
}