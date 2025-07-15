package com.example.android_taskmanagerapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.OneTimeTask

@Composable
fun OneTimeTaskComponent(
    task: OneTimeTask,
    onDoneChange: () -> Unit,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
    onEdit: () -> Unit = {}
){
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
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton (
                onClick = onDoneChange,
                modifier = Modifier
                        .size(80.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.baseline_check_circle_24),
                    contentDescription = if(task.isDone()) "Checked" else "Unchecked",
                    tint = if(task.isDone()) Color.Green else Color.LightGray,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
            Text(
                text = task.title,
                modifier = Modifier.weight(1F),
                style = MaterialTheme.typography.headlineSmall.merge(
                    TextStyle(
                        textDecoration = if (task.isDone()) TextDecoration.LineThrough else TextDecoration.None,
                        color = if (task.isDone()) Color.Gray else Color.Black
                    )
                )
            )
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
                DescriptionBox(task.description)
                DeleteAndEditButtons(
                    onDelete = onDelete,
                    onEdit = onEdit,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun OneTimeTaskPreview(){
    LazyColumn {
        items(10){
            OneTimeTaskComponent(
                OneTimeTask("HW", "finish 3 pages"),
                {}
            )
        }
    }
}