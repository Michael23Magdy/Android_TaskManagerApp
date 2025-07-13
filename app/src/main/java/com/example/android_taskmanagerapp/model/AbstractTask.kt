package com.example.android_taskmanagerapp.model

import com.example.android_taskmanagerapp.TaskType
import java.util.Date
import java.util.UUID

abstract class AbstractTask {
    val id: UUID = UUID.randomUUID()
    abstract val title: String
    abstract val description: String
    abstract val type: TaskType
    val dateCreated: Date = Date()
    abstract fun isDone(): Boolean
}