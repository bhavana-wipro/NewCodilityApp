package com.example.appcodility

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appcodility.presentation.free_game.components.GameScreen
import com.example.appcodility.presentation.free_game.state.UiEffect
import com.example.appcodility.presentation.viewmodel.FreeGameViewModel
import com.example.appcodility.ui.theme.AppCodilityTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCodilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                ) {
                    FreeGameLaunch()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FreeGameLaunch() {
    val snackBarState = remember {
        SnackbarHostState()
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarState)}) {
        val freeGameViewModel = hiltViewModel<FreeGameViewModel>()
        val state = freeGameViewModel.freeGameState.collectAsState()
        LaunchedEffect(key1 = true) {
            freeGameViewModel.uiEffect.collectLatest {
                when(it) {
                    UiEffect.NavigateToDetailScreen -> TODO()
                    is UiEffect.ShowSnackBar -> {
                        launch {
                            snackBarState.showSnackbar(it.msg, duration = SnackbarDuration.Long)
                        }
                    }
                }
            }
        }
        GameScreen(freeGameState = state.value)
    }
}