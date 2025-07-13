package com.example.android_taskmanagerapp.ui.components.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.android_taskmanagerapp.model.ProgressTask
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.TaskType
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.taskFactory

@Composable
fun EditTaskDialog(
    task: AbstractTask,
    onDismiss: () -> Unit,
    onConfirm: (AbstractTask, AbstractTask) -> Unit
){
    var taskTitle by remember { mutableStateOf(task.title) }
    var taskDescription by remember { mutableStateOf(task.description) }
    val selectedTaskType = task.type
    var numSubtasks by remember { mutableStateOf(if (selectedTaskType.name == TaskType.PROGRESS.name)
            (task as ProgressTask).numSubtasks else 10) }
    AlertDialog(
        title = { Text(text = "Edit Task", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth() ) },
        text = {
            Column {
                Row {
                    Icon(
                        painter = painterResource(id = selectedTaskType.icon), null
                    )
                    Text(
                        text = selectedTaskType.displayName,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text(text = "Task Title") }
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = taskDescription,
                    onValueChange = {taskDescription = it},
                    label = { Text(text = "Task Description") }
                )
                if(selectedTaskType.name == TaskType.PROGRESS.name){
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = numSubtasks.toString(),
                        onValueChange = { numSubtasks = it.toInt() },
                        label = { Text(text = "Number of Subtasks") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        task,
                        taskFactory(
                            taskType = selectedTaskType,
                            title = taskTitle,
                            description =  taskDescription,
                            numSubtasks = numSubtasks
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("Edit")
            }
        },
        dismissButton = {
            OutlinedButton (
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}

