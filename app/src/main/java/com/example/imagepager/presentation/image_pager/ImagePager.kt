package com.example.imagepager.presentation.image_pager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.imagepager.presentation.image_pager.components.Loading
import com.example.imagepager.presentation.image_pager.components.Pager

@Composable
fun ImagePager(state: State<ImagePagerState>) {
    if (state.value.isFetchingApi) {
        Loading()
    } else if (state.value.page != null) {
        Pager(state.value.page)
    }
}