package com.example.appcodility.data.remote

import com.example.appcodility.data.remote.dto.FreeGamesDto
import retrofit2.http.GET

interface FreeGameApi {
    @GET("games")
    suspend fun getFreeGame() : List<FreeGamesDto>
}