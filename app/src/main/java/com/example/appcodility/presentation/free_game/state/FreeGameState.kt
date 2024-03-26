package com.example.appcodility.presentation.free_game.state

import com.example.appcodility.domain.model.FreeGames

data class FreeGameState (
    val freeGames: List<FreeGames>? = emptyList(),
    val errorMsg: String? = "",
    val isLoading: Boolean = false
)