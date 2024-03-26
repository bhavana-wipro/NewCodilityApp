package com.example.appcodility.domain.repository

import com.example.appcodility.core.common.Resource
import com.example.appcodility.data.remote.dto.FreeGamesDto
import com.example.appcodility.domain.model.FreeGames
import kotlinx.coroutines.flow.Flow

interface FreeGamesRepository {
    fun getFreeGames() : Flow<Resource<List<FreeGames>>>
}