package com.example.jikan_api_mvvm.data.repo

import com.example.jikan_api_mvvm.data.api.NetworkApiService
import com.example.jikan_api_mvvm.data.local.AnimeDao
import com.example.jikan_api_mvvm.data.local.toEntity
import javax.inject.Inject


class AnimeRepository @Inject constructor(
    private val api: NetworkApiService,
    private val dao: AnimeDao
) {

    suspend fun getTopAnime(): List<com.example.jikan_api_mvvm.data.local.AnimeEntity> {
        return try {
            val remote = api.getTopAnime().data
            dao.clearAll()
            dao.insertAll(remote.map { it.toEntity() })
            dao.getAll()
        } catch (e: Exception) {
            dao.getAll()
        }
    }

    suspend fun getAnimeDetail(id: Int) = api.getAnimeDetail(id)

    suspend fun getCharacters(id: Int) = api.getCharacters(id)
}
