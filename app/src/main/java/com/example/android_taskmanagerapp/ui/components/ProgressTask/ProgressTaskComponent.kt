package com.example.android_taskmanagerapp.ui.components.ProgressTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.ui.components.DeleteAndEditButtons
import com.example.android_taskmanagerapp.ui.components.DescriptionBox
import com.example.android_taskmanagerapp.ui.components.ExpandButton

@Composable
fun ProgressTaskComponent(
    task: ProgressTask,
    onProgressChange: (Float) -> Unit,
    onDelete: () -> Unit = {},
    onEdit: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .size(80.dp)
            ) {
                CircularProgressIndicator(
                    percentage = task.progressPercentage().toFloat(),
                    color = Color.Blue,
                    backgroundColor = Color.LightGray,
                    radius = 26.dp,
                    strokeWidth = 8.dp
                )
            }
            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "you have finished ${task.progressValue} out of ${task.numSubtasks}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
            ExpandButton(
                isExpanded = expanded,
                onExpandChange = { expanded = !expanded }
            )
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                    Slider(
                        value = task.progressValue.toFloat(),
                        onValueChange = onProgressChange,
                        valueRange = 0f..task.numSubtasks.toFloat()
                    )
                DescriptionBox(task.description)
                DeleteAndEditButtons(
                    onDelete = onDelete,
                    onEdit = onEdit
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProgressTaskPreview(){
    ProgressTaskComponent(
        task = ProgressTask(
            title = "HW",
            description = "finish 3 pages",
            progressValue = 8,
            numSubtasks = 10
        ),
        onProgressChange = {}
    )
}