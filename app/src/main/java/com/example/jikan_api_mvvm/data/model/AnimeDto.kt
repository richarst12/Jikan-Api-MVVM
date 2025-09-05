package com.example.jikan_api_mvvm.data.model

data class AnimeDto(
    val mal_id: Int,
    val title: String?,
    val episodes: Int?,
    val score: Double?,
    val images: ImagesDto?,
    val trailer: TrailerDto?
)