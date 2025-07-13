package com.example.android_taskmanagerapp.model

import com.example.android_taskmanagerapp.TaskType

data class OneTimeTask(
    override val title: String,
    override val description: String,
    override val type: TaskType = TaskType.ONETIME,
    var done: Boolean = false
) : AbstractTask() {
    override fun isDone(): Boolean {
        return done
    }
}