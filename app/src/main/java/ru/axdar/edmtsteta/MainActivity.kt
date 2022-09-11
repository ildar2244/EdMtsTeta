package ru.axdar.edmtsteta

import NewsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.axdar.data.news.db.NewsLocalDataSource
import ru.axdar.data.news.remote.NewsRemoteDataSource
import ru.axdar.data.news.repository.NewsRepository
import ru.axdar.edmtsteta.ui.theme.EdMtsTetaTheme
import ru.axdar.news.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EdMtsTetaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen(
                        viewModel = NewsViewModel(
                            NewsRepository(
                                NewsLocalDataSource(applicationContext),
                                NewsRemoteDataSource()
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EdMtsTetaTheme {
        Greeting("Android")
    }
}