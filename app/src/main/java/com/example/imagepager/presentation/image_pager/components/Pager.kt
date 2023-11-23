package com.example.imagepager.presentation.image_pager.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.imagepager.data.remote.model.Photo
import com.example.imagepager.presentation.image_pager.ImagePagerEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(photos: List<Photo>, onEvent: (ImagePagerEvent) -> Unit) {
    var page by remember { mutableIntStateOf(1) }
    var pageCount by remember { mutableIntStateOf(photos.size) }
    val pagerState = rememberPagerState { pageCount }

    LaunchedEffect(key1 = pagerState.currentPage, key2 = photos) {
        pageCount = photos.size

        if (pagerState.currentPage == pagerState.pageCount - 1) {
            page++
            onEvent(ImagePagerEvent.FetchPage(page))
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        VerticalPager(state = pagerState) {
            AsyncImage(
                model = photos[it].src.portrait,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
