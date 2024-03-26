package com.example.appcodility.domain.usecase

import com.example.appcodility.domain.repository.FreeGamesRepository
import javax.inject.Inject

class FreeGameUseCase @Inject constructor(private val repository: FreeGamesRepository) {
    operator fun invoke() = repository.getFreeGames()
}
