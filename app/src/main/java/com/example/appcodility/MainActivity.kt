package com.example.appcodility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appcodility.ui.theme.AppCodilityTheme
import com.example.appcodility.ui.theme.view.UserApp
import com.example.appcodility.viewModel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCodilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()
                    .background(Color.White)) {
                    UserScreen()
                }
            }
        }
    }
}

@Composable
fun UserScreen() {
    val viewModel: DogViewModel = hiltViewModel()
    UserApp(viewModel)
}