package com.example.jikan_api_mvvm.data.model.anime_detail

import com.example.jikan_api_mvvm.data.model.ImagesDto
import com.example.jikan_api_mvvm.data.model.TrailerDto

data class AnimeDetailDto(
    val mal_id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val images: ImagesDto?,
    val trailer: TrailerDto?,
    val genres: List<GenreDto>?
)
data class GenreDto(
    val name: String?
)
