package com.example.android_taskmanagerapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.android_taskmanagerapp.R

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