package com.example.appcodility.data.repository

import com.example.appcodility.core.common.Resource
import com.example.appcodility.data.remote.FreeGameApi
import com.example.appcodility.data.remote.mapper.toDomainFreeGames
import com.example.appcodility.domain.model.FreeGames
import com.example.appcodility.domain.repository.FreeGamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class FreeGamesRepositoryImpl @Inject constructor(private val freeGameApi: FreeGameApi) : FreeGamesRepository{
    override fun getFreeGames(): Flow<Resource<List<FreeGames>>> = flow{
        emit(Resource.Loading())
        val result = freeGameApi.getFreeGame().map {
            it.toDomainFreeGames()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}