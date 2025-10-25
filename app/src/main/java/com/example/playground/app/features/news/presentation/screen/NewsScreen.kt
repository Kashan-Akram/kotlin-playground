package com.example.playground.app.features.news.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.playground.app.common.core.helper.UtilFunctions
import com.example.playground.app.features.news.presentation.components.NewsTile
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun NewsScreen() {

    val viewModel = hiltViewModel<NewsViewModel>()
    val pagingItems = viewModel.articlesFlow.collectAsLazyPagingItems()

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

        val isRefreshing by remember { mutableStateOf(false) }

        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            isRefreshing = isRefreshing,
            onRefresh = {
                pagingItems.refresh()
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = pagingItems.itemCount
                ) { index ->
                    pagingItems[index]?.let { article ->
                        NewsTile(article) {}
                    }
                }
            }
        }

    }
}