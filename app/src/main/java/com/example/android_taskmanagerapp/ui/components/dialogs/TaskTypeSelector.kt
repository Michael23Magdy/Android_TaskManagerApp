package com.example.android_taskmanagerapp.ui.components.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.android_taskmanagerapp.TaskType


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