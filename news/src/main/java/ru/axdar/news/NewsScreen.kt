import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.axdar.data.news.repository.News
import ru.axdar.news.NewsState
import ru.axdar.news.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel) {

    val state = viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            is NewsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            is NewsState.Error -> {
                Column(
                    modifier = Modifier
                        .align(Center)
                ) {
                    Text(
                        text = (state.value as NewsState.Error).throwable.toString(),
                        modifier = Modifier
                            .wrapContentSize(),
                        textAlign = TextAlign.Center
                    )
                    Button(
                        modifier = Modifier
                            .wrapContentWidth(),
                        onClick = { viewModel.refreshData() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        Text(text ="Повторить", color = Color.White)
                    }
                }
            }
            is NewsState.Content -> {
                val news = (state.value as NewsState.Content).newsList
                Column(Modifier.fillMaxSize()) {
                    LazyColumn(
                        Modifier
                            .weight(1f)
                            .padding(bottom = 16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(
                            items = news,
                            itemContent = { NewsListItem(news = it) }
                        )
                    }
                    Button(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .wrapContentWidth(),
                        onClick = { viewModel.refreshData() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    ) {
                        Text(text ="Обновить", color = Color.White)
                    }
                }

            }
        }
    }
}

@Composable
fun NewsListItem(news: News) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = news.title, style = typography.h6)
                Text(text = news.content, style = typography.body2)
                Text(
                    text = news.author,
                    style = typography.subtitle1,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}