package com.example.android_taskmanagerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import com.example.android_taskmanagerapp.model.TaskInterface
import com.example.android_taskmanagerapp.ui.components.OneTimeTaskComponent
import com.example.android_taskmanagerapp.ui.components.ProgressTaskComponent
import com.example.android_taskmanagerapp.ui.components.StreakTaskComponent

@Composable
fun MyTasksScreen(){
    val tasks: List<TaskInterface> = listOf<TaskInterface>(
        OneTimeTask(
            title = "HW",
            description = "finish 3 pages"
        ),
        ProgressTask(
            title = "HW",
            description = "finish 3 pages",
            progressValue = 40,
            numSubtasks = 100
        ),
        ProgressTask(
            title = "HW",
            description = "finish 3 pages",
            progressValue = 40,
            numSubtasks = 100
        ),
        OneTimeTask(
            title = "HW",
            description = "finish 3 pages"
        ),
        StreakTask(
            title = "HW",
            description = "finish 3 pages",
            currentStreak = 1,
            longestStreak = 1
        ),
        ProgressTask(
            title = "HW",
            description = "finish 3 pages",
            progressValue = 40,
            numSubtasks = 100
        ),
        OneTimeTask(
            title = "HW",
            description = "finish 3 pages"
        ),
        StreakTask(
            title = "HW",
            description = "finish 3 pages",
            currentStreak = 1,
            longestStreak = 1
        ),
        OneTimeTask(
            title = "HW",
            description = "finish 3 pages"
        ),
    )
    Column {
        Box {
            Image(
                painter = painterResource(R.drawable._8_988682_minimalist_forest_wallpaper_hd),
                contentDescription = null,
                modifier =  Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "My Tasks",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 14.dp)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(tasks) { task ->
                when(task){
                    is OneTimeTask -> {
                        OneTimeTaskComponent(
                            task = task,
                            onDoneChange = {}
                        )
                    }
                    is ProgressTask -> {
                        ProgressTaskComponent(
                            task = task,
                            onProgressChange = {}
                        )
                    }
                    is StreakTask -> {
                        StreakTaskComponent(
                            task = task,
                            onStreakIncrease = {},
                            onStreakReset = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TasksList(){

}