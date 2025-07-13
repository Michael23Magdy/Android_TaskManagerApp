package com.example.android_taskmanagerapp.model

interface TaskInterface {
    var title: String
    var description: String

    fun isDone(): Boolean
}