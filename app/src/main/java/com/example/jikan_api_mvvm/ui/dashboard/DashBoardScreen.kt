package com.example.jikan_api_mvvm.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.jikan_api_mvvm.data.local.AnimeEntity
import com.example.jikan_api_mvvm.ui.base.UiState
import com.example.jikan_api_mvvm.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    viewModel: MainViewModel,
    onAnimeClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    val state by viewModel.topAnime.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.loadTopAnime() }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Top Anime") }) }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val s = state) {
                is UiState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                is UiState.Error -> {
                    ErrorView(message = s.message, onRetry = onRetry)
                }
                is UiState.Success -> {
                    AnimeList(
                        list = s.data,
                        onAnimeClick = onAnimeClick
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimeList(
    list: List<AnimeEntity>,
    onAnimeClick: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(list) { item ->
            AnimeRow(item, onClick = { onAnimeClick(item.id) })
        }
    }
}

@Composable
private fun AnimeRow(item: AnimeEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(Modifier.padding(12.dp)) {
            AsyncImage(
                model = item.posterUrl,
                contentDescription = item.title,
                modifier = Modifier.size(72.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(item.title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text("Episodes: ${item.episodes}")
                Text("Rating: ${item.score}")
            }
        }
    }
}

@Composable
private fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message)
        Spacer(Modifier.height(8.dp))
        Button(onClick = onRetry) { Text("Retry") }
    }
}
