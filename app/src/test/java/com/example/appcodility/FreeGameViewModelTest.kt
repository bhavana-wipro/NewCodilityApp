package com.example.appcodility

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.appcodility.core.common.Resource
import com.example.appcodility.domain.model.FreeGames
import com.example.appcodility.domain.repository.FreeGamesRepository
import com.example.appcodility.domain.usecase.FreeGameUseCase
import com.example.appcodility.presentation.free_game.state.FreeGameState
import com.example.appcodility.presentation.free_game.state.UiEffect
import com.example.appcodility.presentation.viewmodel.FreeGameViewModel
import io.mockk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class FreeGameViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FreeGameViewModel
    private lateinit var mockUseCase: FreeGameUseCase
    private lateinit var mockRepository: FreeGamesRepository
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        // Initialize Mocks
        mockUseCase = mockk()
        mockRepository = mockk()
        val expectedFreeGames = listOf(FreeGames("", 1, "", "", ""))
        // Provide a default answer for the invoke function of the use case mock
        coEvery { mockUseCase.invoke() } returns flowOf(Resource.Success(expectedFreeGames))
        // Create an instance of the view model with the mocked use case
        viewModel = FreeGameViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        // Clean up after each test
        clearAllMocks()
    }

    @Test
    fun testGetAllFreeGamesSuccess() {
        val expectedFreeGames = listOf(FreeGames("", 1, "", "", ""))
        coEvery { mockUseCase.invoke() } returns flowOf(Resource.Success(expectedFreeGames))
        viewModel.getAllFreeGames()
        assertEquals(expectedFreeGames, viewModel.freeGameState.value.freeGames)
    }

    @Test
    fun testGetAllFreeGamesError() {
        val expectedFreeGames = "Something Went Wrong"
        coEvery { mockUseCase.invoke() } returns flowOf(Resource.Error(expectedFreeGames))
        viewModel.getAllFreeGames()
        assertEquals(expectedFreeGames, viewModel.freeGameState.value.errorMsg)
    }

    @Test
    fun testIsLoadingTrue() {
        val isLoading = true
        coEvery { mockUseCase.invoke() } returns flowOf(Resource.Loading())
        viewModel.getAllFreeGames()
        assertEquals(isLoading, viewModel.freeGameState.value.isLoading)

    }
}