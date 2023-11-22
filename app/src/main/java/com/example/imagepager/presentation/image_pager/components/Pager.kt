package com.example.imagepager.presentation.image_pager.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.imagepager.data.remote.model.Page

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(page: Page?) {
    val pagerState = rememberPagerState(pageCount = {
        page!!.photos.size
    })

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        VerticalPager(state = pagerState) {
            AsyncImage(
                model = page!!.photos[it].src.portrait,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun PagerPreview() {
    Pager(null)
}
