package com.example.jikan_api_mvvm.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jikan_api_mvvm.data.model.AnimeDto

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val episodes: Int,
    val score: Double,
    val posterUrl: String
)

fun AnimeDto.toEntity() = AnimeEntity(
    id = mal_id,
    title = title.orEmpty(),
    episodes = episodes ?: 0,
    score = score ?: 0.0,
    posterUrl = images?.jpg?.image_url.orEmpty()
)
