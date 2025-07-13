package com.example.android_taskmanagerapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.R
import com.example.android_taskmanagerapp.model.OneTimeTask

@Composable
fun OneTimeTaskComponent(
    task: OneTimeTask,
    onDoneChange: () -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column (
        modifier = modifier
            .fillMaxWidth()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton (
                onClick = onDoneChange
            ){
                if(task.isDone()){
                    Icon(
                        painter = painterResource(R.drawable.baseline_check_circle_24),
                        contentDescription = "Checked",
                        tint = Color.Green,
                        modifier = Modifier.size(26.dp)
                    )
                }
                else {
                    Icon(
                        painter = painterResource(R.drawable.baseline_check_circle_24),
                        contentDescription = "Unchecked",
                        tint = Color.LightGray,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            Text(
                text = task.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1F)
            )
            ExpandButton(
                isExpanded = expanded,
                onExpandChange = { expanded = !expanded }
            )
        }
        if(expanded){
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Task Description",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium
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