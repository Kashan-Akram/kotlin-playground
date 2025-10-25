package com.example.playground.app.features.news.presentation.screen

import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.playground.app.common.core.helper.UtilFunctions
import com.example.playground.app.features.news.domain.models.Article
import com.example.playground.app.features.news.presentation.components.NewsTile
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun NewsScreen(
    uiState: NewsUiState,
    onIntent: (NewsIntents) -> Unit
) {

    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible == totalItems - 1 && totalItems > 0
        }.collect { isAtBottom ->
            if (isAtBottom) {
                Log.d("myLogs", "view model function called")
                onIntent(NewsIntents.LoadMore)
            }
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
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading && listState.firstVisibleItemIndex <= 3) {
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