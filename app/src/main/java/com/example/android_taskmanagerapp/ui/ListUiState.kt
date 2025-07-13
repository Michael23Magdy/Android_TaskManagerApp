package com.example.android_taskmanagerapp.ui

import com.example.android_taskmanagerapp.model.AbstractTask

data class ListUiState(
    val tasks: List<AbstractTask> = emptyList()
)