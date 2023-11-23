package com.example.imagepager.presentation.image_pager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.imagepager.presentation.image_pager.components.Error
import com.example.imagepager.presentation.image_pager.components.Loading
import com.example.imagepager.presentation.image_pager.components.Pager

@Composable
fun ImagePager(state: State<ImagePagerState>, onEvent: (ImagePagerEvent) -> Unit) {
    if (state.value.isFetchingApi) {
        Loading()
    } else if (state.value.error.isNotBlank()) {
        Error(state.value.error)
    } else if (state.value.photos != null) {
        Pager(state.value.photos!!, onEvent)
    }
}