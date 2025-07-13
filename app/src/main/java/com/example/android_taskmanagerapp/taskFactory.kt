package com.example.android_taskmanagerapp

import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask

fun taskFactory(
    taskType: TaskType,
    title: String,
    description: String,
    numSubtasks: Int = 1
): AbstractTask {
    return when(taskType){
        TaskType.ONETIME -> OneTimeTask(title = title, description = description)
        TaskType.STREAK -> StreakTask(title = title, description = description)
        TaskType.PROGRESS -> ProgressTask(title = title, description = description, numSubtasks = numSubtasks)
    }
}