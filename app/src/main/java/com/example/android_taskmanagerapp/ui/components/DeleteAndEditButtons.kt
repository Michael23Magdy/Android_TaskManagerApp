package com.example.android_taskmanagerapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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