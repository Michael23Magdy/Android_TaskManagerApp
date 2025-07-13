package com.example.android_taskmanagerapp.ui.components.ProgressTask

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressIndicator(
    percentage: Float, // Value between 0 and 100
    strokeWidth: Dp = 8.dp,
    color: Color = Color.Blue,
    backgroundColor: Color = Color.LightGray,
    radius: Dp = 50.dp
) {
    Box(){
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