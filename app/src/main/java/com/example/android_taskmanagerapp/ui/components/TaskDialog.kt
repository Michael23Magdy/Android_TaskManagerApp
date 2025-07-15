package com.example.android_taskmanagerapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.TaskType
import com.example.android_taskmanagerapp.model.AbstractTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.taskFactory

@Composable
fun TaskDialog(
    initialTask: AbstractTask,
    onDismiss: () -> Unit,
    onConfirm: (AbstractTask) -> Unit,
    confirmText: String,
){
    var taskTitle by remember { mutableStateOf(initialTask.title) }
    var taskDescription by remember { mutableStateOf(initialTask.description) }
    var selectedTaskType by remember { mutableStateOf(initialTask.type) }
    var numSubtasks by remember {
        mutableIntStateOf(if (selectedTaskType.name == TaskType.PROGRESS.name)
            (initialTask as ProgressTask).numSubtasks else 10)
    }
    AlertDialog(
        title = { Text(text = "Add New Task", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth() ) },
        text = {
            Column {
                TaskTypeSelector(
                    selectedTaskType = selectedTaskType,
                    onTaskTypeSelected = { selectedTaskType = it }
                )
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
                        value = if(numSubtasks == 0) "" else numSubtasks.toString(),
                        onValueChange = { numSubtasks = if(it.isEmpty()) 0 else it.toInt() },
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
                enabled = taskTitle.isNotBlank() && (selectedTaskType.name != TaskType.PROGRESS.name || numSubtasks > 0),
                onClick = {
                    onConfirm(
                        taskFactory(
                            taskType = selectedTaskType,
                            title = taskTitle,
                            description =  taskDescription,
                            numSubtasks = numSubtasks,
                            progressValue = if(initialTask is ProgressTask) initialTask.progressValue else 0
                        )
                    )
                    onDismiss()
                }
            ) {
                Text(text = confirmText)
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

@Composable
fun TaskTypeSelector(
    selectedTaskType: TaskType,
    onTaskTypeSelected: (TaskType) -> Unit
) {
    val options = listOf(
        TaskType.ONETIME,
        TaskType.STREAK,
        TaskType.PROGRESS
    )

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                    options.lastIndex -> RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                    else -> RectangleShape
                },
                icon = { "" },
                selected = selectedTaskType.name == option.name,
                onClick = { onTaskTypeSelected(option) },
                modifier = Modifier.weight(1f),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.primary,
                    activeContentColor = MaterialTheme.colorScheme.onPrimary,
                    inactiveContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    text = option.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
