package com.example.android_taskmanagerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import com.example.android_taskmanagerapp.ui.components.dialogs.AddTaskDialog
import com.example.android_taskmanagerapp.ui.components.OneTimeTask.OneTimeTaskComponent
import com.example.android_taskmanagerapp.ui.components.ProgressTask.ProgressTaskComponent
import com.example.android_taskmanagerapp.ui.components.StreakTask.StreakTaskComponent
import com.example.android_taskmanagerapp.ui.components.dialogs.EditTaskDialog

@Composable
fun MyTasksScreen(
    listViewModel: ListViewModel = viewModel(),
    modifier: Modifier = Modifier
){
    val listUiState by listViewModel.uiState.collectAsState()
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var showEditTaskDialog: AbstractTask? by remember { mutableStateOf(null) }
    Box(){
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Box{
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
            }
            if(listUiState.tasks.isEmpty()){
                item {
                    Text(
                        text = "No tasks yet",
                    )
                }
            } else {
                items(listUiState.tasks) { task ->
                    when(task){
                        is OneTimeTask -> {
                            OneTimeTaskComponent(
                                task = task,
                                onDoneChange = {},
                                onDelete = { listViewModel.deleteTask(task) },
                                onEdit = { showEditTaskDialog = task }
                            )
                        }
                        is ProgressTask -> {
                            ProgressTaskComponent(
                                task = task,
                                onProgressChange = {},
                                onDelete = { listViewModel.deleteTask(task) },
                                onEdit = { showEditTaskDialog = task }
                            )
                        }
                        is StreakTask -> {
                            StreakTaskComponent(
                                task = task,
                                onStreakIncrease = {},
                                onStreakReset = {},
                                onDelete = { listViewModel.deleteTask(task) },
                                onEdit = { showEditTaskDialog = task }
                            )
                        }
                    }
                }
            }

            item{
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        FloatingActionButton (
            onClick = { showAddTaskDialog = !showAddTaskDialog},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }

        if (showAddTaskDialog){
            AddTaskDialog (
                onDismiss = { showAddTaskDialog = !showAddTaskDialog },
                onConfirm = { listViewModel.addTask(it) }
            )
        }

        when(showEditTaskDialog){
            is OneTimeTask -> {
                val task = showEditTaskDialog as OneTimeTask
                EditTaskDialog (
                    task = task,
                    onDismiss = { showEditTaskDialog = null },
                    onConfirm = { oldTask, newTask ->
                        listViewModel.editTask(oldTask as OneTimeTask, newTask as OneTimeTask)
                    }
                )
            }
            is ProgressTask -> {
                val task = showEditTaskDialog as ProgressTask
                EditTaskDialog (
                    task = task,
                    onDismiss = { showEditTaskDialog = null },
                    onConfirm = { oldTask, newTask ->
                        listViewModel.editTask(oldTask as ProgressTask, newTask as ProgressTask)
                    }
                )
            }
            is StreakTask -> {
                val task = showEditTaskDialog as StreakTask
                EditTaskDialog (
                    task = task,
                    onDismiss = { showEditTaskDialog = null },
                    onConfirm = { oldTask, newTask ->
                        listViewModel.editTask(oldTask as StreakTask, newTask as StreakTask)
                    }
                )
            }
        }
    }
}

@Composable
fun TasksList(){

}