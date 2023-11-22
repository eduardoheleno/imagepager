package com.example.imagepager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagepager.presentation.image_pager.ImagePager
import com.example.imagepager.presentation.image_pager.ImagePagerEvent
import com.example.imagepager.presentation.ui.theme.ImagepagerTheme
import com.example.imagepager.presentation.viewmodel.ImagePagerViewModel
import com.example.imagepager.util.NetworkHandle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var imagePagerViewModel: ImagePagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagepagerTheme {
                LaunchedEffect(Unit) {
                    launch(Dispatchers.IO) {
                        val networkHandle = NetworkHandle()

                        if (
                            networkHandle.isNetworkConnected(applicationContext) &&
                            networkHandle.isInternetAvailable()
                        ) {
                            imagePagerViewModel.onEvent(ImagePagerEvent.FetchImages)
                        } else {
                            val error = "Your device has no access to the internet."
                            imagePagerViewModel.onEvent(ImagePagerEvent.Error(error))
                        }
                    }
                }

                imagePagerViewModel = hiltViewModel()
                ImagePager(imagePagerViewModel.state)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagepagerTheme {
        Greeting("Android")
    }
}