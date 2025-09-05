package com.example.jikan_api_mvvm.data.model.character

data class CharactersResponse(
    val data: List<CharacterEdgeDto>
)

data class CharacterEdgeDto(
    val role: String?,
    val character: CharacterDto?
)

data class CharacterDto(
    val name: String?
)
