package com.example.android_taskmanagerapp.model

import com.example.android_taskmanagerapp.TaskType
import kotlin.math.min

data class ProgressTask(
    override val title: String,
    override val description: String,
    var numSubtasks: Int,
    override val type: TaskType = TaskType.PROGRESS,
    var progressValue: Int = 0
) : AbstractTask(){
    fun progressPercentage(): Int {
        return min(progressValue * 100 / numSubtasks, 100)
    }

    override fun isDone(): Boolean {
        return progressValue >= numSubtasks
    }
}