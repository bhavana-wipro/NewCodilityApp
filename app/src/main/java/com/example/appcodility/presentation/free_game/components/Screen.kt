package com.example.appcodility.presentation.free_game.components

sealed class Screen(val route: String) {
    object GameScreen : Screen("game_screen")
}