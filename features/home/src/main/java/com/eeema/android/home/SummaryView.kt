package com.eeema.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eeema.android.components.card.CircularPercentageCard
import com.eeema.android.components.card.InfoCard
import com.eeema.android.theme.SavingsTheme

@Composable
internal fun SummaryView(
    state: SummaryUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        CircularPercentageCard(percentage = state.percentage)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            InfoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = "Spent",
                description = "${state.spent}$",
            )
            InfoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = "Remaining",
                description = "${state.remaining}$",
                backgroundColor = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SavingsTheme {
        SummaryView(
            state = SummaryUiState(percentage = 34f, spent = 800f, remaining = 1000f),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
