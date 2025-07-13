package com.example.android_taskmanagerapp
import androidx.annotation.DrawableRes
import com.example.android_taskmanagerapp.R
enum class TaskType(val displayName: String, @DrawableRes val icon: Int) {
    ONETIME(displayName = "One Time", icon = R.drawable.baseline_check_circle_24),
    STREAK(displayName = "Streak", icon = R.drawable.baseline_local_fire_department_24),
    PROGRESS(displayName = "Progress", icon = R.drawable.baseline_percent_24),
}