package com.eeema.android.components.percentage

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults.ProgressAnimationSpec
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eeema.android.theme.SavingsTheme

@Composable
fun CircularPercentage(
    percentage: Float, // Value between 0f and 100f
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 12.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    val progress = (percentage / 100f).coerceIn(0f, 1.0f)
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressAnimationSpec
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.aspectRatio(1f)
    ) {
        CircularProgressIndicator(
            progress = { animatedProgress },
            modifier = Modifier.fillMaxSize(),
            strokeWidth = strokeWidth,
            color = color,
            trackColor = trackColor
        )
        Text(
            text = "${(animatedProgress * 100).toInt()}%",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        CircularPercentage(
            percentage = 50f,
            modifier = Modifier.size(300.dp)
        )
    }
}