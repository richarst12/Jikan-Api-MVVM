package com.example.jikan_api_mvvm.data.api


import com.example.jikan_api_mvvm.data.model.TopAnimeResponse
import com.example.jikan_api_mvvm.data.model.anime_detail.AnimeDetailResponse
import com.example.jikan_api_mvvm.data.model.character.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {

    // https://api.jikan.moe/v4/top/anime?page=1
    @GET("v4/top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): TopAnimeResponse

    // https://api.jikan.moe/v4/anime/{id}
    @GET("v4/anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse

    // https://api.jikan.moe/v4/anime/{id}/characters
    @GET("v4/anime/{id}/characters")
    suspend fun getCharacters(
        @Path("id") id: Int
    ): CharactersResponse
}