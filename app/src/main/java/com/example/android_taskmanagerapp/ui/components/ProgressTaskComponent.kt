package com.example.android_taskmanagerapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.ProgressTask

@Composable
fun ProgressTaskComponent(
    task: ProgressTask,
    onProgressChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
    onEdit: () -> Unit = {}
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val springSpec = spring<IntSize>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .animateContentSize(animationSpec = springSpec)
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
                    style = MaterialTheme.typography.headlineSmall.merge(
                        TextStyle(
                            textDecoration = if (task.isDone()) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (task.isDone()) Color.Gray else Color.Black
                        )
                    )
                )
                Text(
                    text = stringResource(R.string.progress_statment, task.progressValue, task.numSubtasks),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
            ExpandButton(
                isExpanded = expanded,
                onExpandChange = { expanded = !expanded }
            )
        }
        AnimatedVisibility (
            visible = expanded,
            enter = expandVertically (animationSpec = springSpec),
            exit = shrinkVertically (animationSpec = springSpec)
        ) {
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
                    onEdit = onEdit,
                )
            }
        }
    }
}

@Composable
fun CircularProgressIndicator(
    percentage: Float, // Value between 0 and 100
    strokeWidth: Dp = 8.dp,
    color: Color = Color.Blue,
    backgroundColor: Color = Color.LightGray,
    radius: Dp = 50.dp
) {
    Box {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
                .padding(strokeWidth / 2)
        ) {
            // Draw background circle
            drawCircle(
                color = backgroundColor,
                radius = size.minDimension / 2,
                style = Stroke(width = strokeWidth.toPx())
            )

            // Draw progress arc
            drawArc(
                color = color,
                startAngle = -90f, // Start from top (12 o'clock position)
                sweepAngle = 360 * percentage /100,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx()),
                size = Size(size.width, size.height)
            )
        }
        Text(
            text = percentage.toInt().toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = if(percentage >= 100.0f) 12.sp else 16.sp,
            fontWeight =  FontWeight.Bold,
            color = color
        )
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