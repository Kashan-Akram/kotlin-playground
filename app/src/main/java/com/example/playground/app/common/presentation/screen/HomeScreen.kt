package com.example.playground.app.common.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.playground.app.common.core.helper.UtilFunctions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onIntent: (HomeIntents) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        containerColor = Color.DarkGray,
        topBar = {
            val color = remember { UtilFunctions.randomColor() }
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                colors = TopAppBarColors(
                    containerColor = color,
                    scrolledContainerColor = color,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black,
                    subtitleContentColor = Color.Black
                ),
                title = {
                    Text("Select Feature")
                }
            )
        }
    ) { innerPadding ->
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(20.dp),
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            uiState.tiles.forEach { feature ->
                FeatureTile(
                    modifier = Modifier.weight(1F),
                    featureName = feature,
                    onFeatureClicked = {
                        onIntent(HomeIntents.FeatureClicked(feature))
                    }
                )
            }
        }
    }
}

@Composable
fun FeatureTile(
    modifier: Modifier,
    featureName: String,
    onFeatureClicked: () -> Unit
) {
    val color = remember { UtilFunctions.randomColor() }
    Box(
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(10.dp),
                ambientColor = color,
                spotColor = color
            )
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable(
                enabled = featureName.isNotBlank(),
                onClick = onFeatureClicked
            )
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = featureName,
            fontWeight = FontWeight.Bold
        )
    }
}