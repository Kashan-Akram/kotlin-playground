package com.example.playground.app.features.news.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playground.app.features.news.domain.models.Article

@Composable
fun NewsTile(
    article: Article,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 15.dp)
            .clickable(
                enabled = article.content.isNotBlank(),
                onClick = onClick
            ),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = article.title,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 20.sp,
        )
        Text(
            text = article.description,
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 10.sp,
        )
    }
}