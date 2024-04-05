package com.example.appcodility.presentation.free_game.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appcodility.domain.model.FreeGames
import com.example.appcodility.presentation.free_game.state.FreeGameState


@Composable
fun GameScreen(freeGameState: FreeGameState) {
    if (freeGameState.freeGames?.isNotEmpty()!!) {
        LazyColumn {
            items(freeGameState.freeGames) {
                FreeGameItem(it)
            }
        }
    } else if (freeGameState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).testTag("Progress indicator"))
        }
    }
}

@Composable
fun FreeGameItem(games: FreeGames) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = games.thumbnail, contentDescription = "Thumbnail", modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Text(text = games.title, fontWeight = FontWeight.Bold)
                Text(text = games.shortDescription)
            }
        }
    }
}
