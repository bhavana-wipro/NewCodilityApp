package com.example.appcodility.data.remote.mapper

import com.example.appcodility.data.remote.dto.FreeGamesDto
import com.example.appcodility.domain.model.FreeGames

fun FreeGamesDto.toDomainFreeGames() : FreeGames {
    return FreeGames(gameUrl, id, shortDescription, thumbnail, title)
}