package com.example.android_taskmanagerapp.ui.components.StreakTask

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.OneTimeTask
import com.example.android_taskmanagerapp.model.ProgressTask
import com.example.android_taskmanagerapp.model.StreakTask
import com.example.android_taskmanagerapp.ui.components.DeleteAndEditButtons
import com.example.android_taskmanagerapp.ui.components.DescriptionBox
import com.example.android_taskmanagerapp.ui.components.ExpandButton
import com.example.android_taskmanagerapp.ui.components.OneTimeTask.OneTimeTaskComponent
import com.example.android_taskmanagerapp.ui.components.ProgressTask.ProgressTaskComponent

@Composable
fun StreakTaskComponent(
    task: StreakTask,
    onStreakIncrease: () -> Unit,
    onStreakReset: () -> Unit,
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
            Box() {
                IconButton(
                    onClick = onStreakIncrease,
                    modifier = Modifier
                            .size(80.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_local_fire_department_24),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Text(
                        text = task.currentStreak.toString(),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }
            }

            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "your longest streak is ${task.longestStreak}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
            IconButton(
                onClick = onStreakReset,
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_rotate_left_24),
                    contentDescription = null,
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
                DescriptionBox(task.description)
                DeleteAndEditButtons(
                    onDelete = onDelete,
                    onEdit = onEdit,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StreakTaskPreview(){
    Column {
        ProgressTaskComponent(
            task = ProgressTask(
                title = "HW",
                description = "finish 3 pages",
                progressValue = 40,
                numSubtasks = 100
            ),
            onProgressChange = {}
        )
        StreakTaskComponent(
            task = StreakTask(
                title = "HW",
                description = "finish 3 pages",
                currentStreak = 1,
                longestStreak = 1
            ),
            onStreakIncrease = {},
            onStreakReset = {}
        )
        OneTimeTaskComponent(
            OneTimeTask("HW", "finish 3 pages"),
            {}
        )
    }
}
