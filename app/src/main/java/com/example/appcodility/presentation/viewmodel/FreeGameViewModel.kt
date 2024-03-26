package com.example.appcodility.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcodility.core.common.Resource
import com.example.appcodility.domain.usecase.FreeGameUseCase
import com.example.appcodility.presentation.free_game.state.FreeGameState
import com.example.appcodility.presentation.free_game.state.UiEffect
import com.example.appcodility.presentation.free_game.state.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FreeGameViewModel @Inject constructor(private  val useCase: FreeGameUseCase) : ViewModel() {
    private val _freeGameState = MutableStateFlow(FreeGameState())
    val freeGameState: StateFlow<FreeGameState>
    get() = _freeGameState

    private val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect: SharedFlow<UiEffect>
    get() = _uiEffect.asSharedFlow()

    init {
        getAllFreeGames()
    }
    private fun getAllFreeGames() = useCase().onEach {
        when(it) {
            is Resource.Error -> {
                _freeGameState.value = FreeGameState().copy(errorMsg = it?.msg)
                _uiEffect.emit(UiEffect.ShowSnackBar(it.msg.toString()))
            }
            is Resource.Loading -> {
                _freeGameState.value = FreeGameState().copy(isLoading = true)
            }
            is Resource.Success -> {
                _freeGameState.value = FreeGameState().copy(freeGames = it.data)
            }
        }
    }.launchIn(viewModelScope)
}