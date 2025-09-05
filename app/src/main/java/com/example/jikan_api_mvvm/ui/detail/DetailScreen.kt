package com.example.jikan_api_mvvm.ui.detail

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import com.example.jikan_api_mvvm.data.model.anime_detail.AnimeDetailResponse
import com.example.jikan_api_mvvm.data.model.character.CharactersResponse
import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.jikan_api_mvvm.ui.base.UiState
import com.example.jikan_api_mvvm.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val detailState by viewModel.animeDetail.collectAsStateWithLifecycle()
    val charState by viewModel.characters.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.loadAnimeDetail(id)
        viewModel.loadCharacters(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            when (val s = detailState) {
                is UiState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){ CircularProgressIndicator()}
                is UiState.Error -> Text("Error: ${s.message}")
                is UiState.Success -> {
                    Content(
                        detail = s.data,
                        characters = when (charState) {
                            is UiState.Success -> (charState as UiState.Success<CharactersResponse>).data
                            else -> null
                        }
                    )
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun Content(detail: AnimeDetailResponse, characters: CharactersResponse?) {
    val detail = detail.data

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            if (!detail.trailer?.embed_url.isNullOrEmpty()) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    factory = { ctx ->
                        WebView(ctx).apply {
                            settings.javaScriptEnabled = true
                            loadUrl(detail.trailer.embed_url)
                        }
                    }
                )
            } else {
                AsyncImage(
                    model = detail.images?.jpg?.large_image_url ?: detail.images?.jpg?.image_url,
                    contentDescription = detail.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }
        }

        item {
            Text(detail.title.orEmpty(), style = MaterialTheme.typography.headlineSmall)
        }

        item {
            Text(detail.synopsis.orEmpty())
        }

        item {
            Text("Episodes: ${detail.episodes ?: 0}")
            Text("Rating: ${detail.score ?: 0.0}")
        }

        item {
            Text("Genres: ${detail.genres?.joinToString { it.name.orEmpty() } ?: "-"}")
        }

        item {
            val cast = characters?.data
                ?.filter { it.role.equals("Main", ignoreCase = true) }
                ?.mapNotNull { it.character?.name }
                ?.take(6)
                ?: emptyList()
            if (cast.isNotEmpty()) {
                Text("Main Cast: ${cast.joinToString()}")
            }
        }
    }
}
