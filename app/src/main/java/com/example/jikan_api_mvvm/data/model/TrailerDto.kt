package com.example.jikan_api_mvvm.data.model

data class TrailerDto(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?
)

data class ImagesDto(
    val jpg: JpgDto?
)

data class JpgDto(
    val image_url: String?,
    val large_image_url: String?
)