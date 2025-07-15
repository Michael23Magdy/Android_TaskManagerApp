package com.example.android_taskmanagerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import com.example.android_taskmanagerapp.ui.components.TaskDialog
import com.example.android_taskmanagerapp.ui.components.OneTimeTask.OneTimeTaskComponent
import com.example.android_taskmanagerapp.ui.components.ProgressTask.ProgressTaskComponent
import com.example.android_taskmanagerapp.ui.components.StreakTask.StreakTaskComponent
@Composable
fun MyTasksScreen(
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = viewModel()
){
    val listUiState by listViewModel.uiState.collectAsState()
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var showEditTaskDialog: AbstractTask? by remember { mutableStateOf(null) }
    Box(
        modifier = modifier.fillMaxSize()
    ){
        if(listUiState.tasks.isEmpty()){
            Column(modifier = Modifier.fillMaxSize()) {
                MyTasksScreenTopSection()
                EmptyTasksScreen()
            }
        } else {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                item { MyTasksScreenTopSection() }
                items(listUiState.tasks) {
                    TaskComponent(
                        listViewModel = listViewModel,
                        onDelete = { listViewModel.deleteTask(it) },
                        onEdit = { showEditTaskDialog = it },
                        task = it,
                    )
                }
                item{
                    Spacer(modifier = Modifier.height(100.dp))
                }
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
            TaskDialog (
                initialTask = OneTimeTask("", ""),
                onDismiss = { showAddTaskDialog = !showAddTaskDialog },
                onConfirm = { listViewModel.addTask(it) },
                confirmText = "Add"
            )
        }
        showEditTaskDialog?.let {
            TaskDialog (
                initialTask = it,
                onDismiss = { showEditTaskDialog = null },
                onConfirm = { newTask ->
                    listViewModel.editTask(showEditTaskDialog!!, newTask)
                },
                confirmText = "Edit"
            )
        }
    }
}

@Composable
fun MyTasksScreenTopSection(){
    Box{
        Image(
            painter = painterResource(R.drawable.old_home_background),
            contentDescription = null,
            modifier =  Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(R.string.my_tasks),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = dimensionResource(R.dimen.TopSectionPadding),
                    bottom = dimensionResource(R.dimen.TopSectionPadding)
                )
        )
    }
}

@Composable
fun EmptyTasksScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            painter = painterResource(R.drawable.moon),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            tint = Color.LightGray
        )
        Text(
            text = stringResource(R.string.no_tasks),
            fontSize = 30.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskComponent(
    listViewModel: ListViewModel,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    task: AbstractTask,
){
    when(task){
        is OneTimeTask -> {
            OneTimeTaskComponent(
                task = task,
                onDoneChange = { listViewModel.doneTask(task) },
                onDelete = onDelete,
                onEdit = onEdit
            )
        }
        is ProgressTask -> {
            ProgressTaskComponent(
                task = task,
                onProgressChange = { newProgress -> listViewModel.updateProgress(task, newProgress.toInt())},
                onDelete = onDelete,
                onEdit = onEdit
            )
        }
        is StreakTask -> {
            StreakTaskComponent(
                task = task,
                onStreakIncrease = {listViewModel.increaseStreak(task)},
                onStreakReset = {listViewModel.resetStreak(task)},
                onDelete = onDelete,
                onEdit = onEdit
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyTasksScreenPreview(){
    MyTasksScreen()
}