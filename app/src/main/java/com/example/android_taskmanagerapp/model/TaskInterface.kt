package com.example.android_taskmanagerapp.model

import java.util.Date
import java.util.UUID

abstract class TaskInterface {
    val id: UUID = UUID.randomUUID()
    abstract val title: String
    abstract val description: String
    val dateCreated: Date = Date()
    abstract fun isDone(): Boolean
}