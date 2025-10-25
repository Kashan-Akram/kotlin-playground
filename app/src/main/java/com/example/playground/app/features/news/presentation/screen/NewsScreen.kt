package com.example.playground.app.features.news.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playground.app.common.core.helper.UtilFunctions
import com.example.playground.app.features.news.domain.models.Article
import com.example.playground.app.features.news.presentation.components.NewsTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    uiState: NewsUiState,
    onIntent: (NewsIntents) -> Unit
) {

    val listState = rememberLazyListState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val visibleItems = listState.layoutInfo.visibleItemsInfo
            if (visibleItems.isEmpty()) return@derivedStateOf null

            val firstVisible = visibleItems.first().index
            val lastVisible = visibleItems.last().index
            val totalItems = listState.layoutInfo.totalItemsCount

            when {
                firstVisible < 5 -> LoadDirection.PREPEND
                totalItems - lastVisible < 5 -> LoadDirection.APPEND
                else -> null
            }
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore != null) {
            val firstVisible = listState.firstVisibleItemIndex
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            onIntent(NewsIntents.ScrollPositionChanged(firstVisible, lastVisible))
        }
    }

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
                    Text("News")
                }
            )
        }
    ) { innerPadding ->
        if (uiState.error.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.error,
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontSize = 30.sp
                )
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isLoading && listState.firstVisibleItemIndex <= 5) {
                    item(key = "loading_top") {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Loading Top",
                            textAlign = TextAlign.Center,
                            color = Color.Red
                        )
                    }
                }
                itemsIndexed(
                    items = uiState.newsList,
                    key = { i: Int, article: Article -> article.title.hashCode() }
                ) { i: Int, article: Article ->
                    NewsTile(article) { }
                    if (i != uiState.newsList.size - 1) {
                        HorizontalDivider(thickness = 1.dp, color = Color.White)
                    }
                }
                if (uiState.isLoading && !uiState.atEnd) {
                    item(key = "loading_bottom") {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Loading Bottom",
                            textAlign = TextAlign.Center,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}