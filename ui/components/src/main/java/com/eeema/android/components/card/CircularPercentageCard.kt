package com.eeema.android.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eeema.android.components.percentage.CircularPercentage
import com.eeema.android.theme.SavingsTheme
import com.eeema.android.theme.backgroundColor600

@Composable
fun CircularPercentageCard(
    percentage: Float,
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.backgroundColor600,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularPercentage(
                percentage = percentage,
                modifier = Modifier.size(150.dp),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        CircularPercentageCard(percentage = 75f)
    }
}
