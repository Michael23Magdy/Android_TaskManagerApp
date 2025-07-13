package com.example.android_taskmanagerapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.ProgressTask

@Composable
fun ProgressTaskComponent(
    task: ProgressTask,
    onProgressChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card (
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.Cyan)
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
                    modifier = Modifier.padding(12.dp)
                ) {
                    DescriptionBox(task.description)
                }
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