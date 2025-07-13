package com.example.android_taskmanagerapp.model

data class OneTimeTask(
    override var title: String,
    override var description: String,
    var done: Boolean = false
) : TaskInterface {
    override fun isDone(): Boolean {
        return done
    }
}