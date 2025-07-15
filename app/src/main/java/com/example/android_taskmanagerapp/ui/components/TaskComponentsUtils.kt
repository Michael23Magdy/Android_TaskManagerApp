package com.example.android_taskmanagerapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.R

@Composable
fun DeleteAndEditButtons (
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = onDelete,
            modifier = Modifier.weight(1F)
        ) {
            Text(
                text = "Delete"
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = onEdit,
            modifier = Modifier.weight(1F)
        ) {
            Text(
                text = "Edit"
            )
        }
    }
}

@Composable
fun DescriptionBox(
    description: String,
) {
    Text(
        text = description.ifEmpty { "no Description" },
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray,
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
fun ExpandButton(
    isExpanded: Boolean,
    onExpandChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onExpandChange,
        modifier = modifier
    ) {
        if (isExpanded){
            Icon(
                painter = painterResource(R.drawable.outline_expand_circle_up_24),
                contentDescription = "Drop down button"
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.outline_expand_circle_down_24),
                contentDescription = "Drop down button"
            )
        }
    }
}