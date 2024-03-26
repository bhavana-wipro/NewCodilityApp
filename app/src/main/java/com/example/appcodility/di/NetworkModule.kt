package com.example.appcodility.di

import com.example.appcodility.core.utils.Constants
import com.example.appcodility.data.remote.FreeGameApi
import com.example.appcodility.data.repository.FreeGamesRepositoryImpl
import com.example.appcodility.domain.repository.FreeGamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFreeGameApi(retrofit: Retrofit): FreeGameApi {
        return retrofit.create(FreeGameApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFreeGameRepository(freeGameApi: FreeGameApi): FreeGamesRepository {
        return FreeGamesRepositoryImpl(freeGameApi)
    }
}