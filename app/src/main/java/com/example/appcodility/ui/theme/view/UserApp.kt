package com.example.appcodility.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.appcodility.model.DogImageData
import com.example.appcodility.viewModel.DogViewModel
import com.example.appcodility.R

@Composable
fun UserApp(
    viewModel: DogViewModel = hiltViewModel()
) {
    val dogImages by viewModel.dogImages.observeAsState()
    //val dogImage by viewModel.dogImage.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.welcome_to_dogs_world))
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.size(80.dp),
                shape = CircleShape,
                elevation = 8.dp
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_img),
                contentDescription =  stringResource(id = R.string.dog_image),
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.fetchRandomDogImages(6) },
            modifier = Modifier
                .width(250.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.click_more_dogs))
        }
        if (!dogImages.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                itemsIndexed(dogImages!!) { _, dogImage ->
                    DogImageItem(dogImage)
                }
            }
        }
    }
}

@Composable
fun DogImageItem(dogImage: DogImageData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(dogImage.message),
                contentDescription = stringResource(id = R.string.dog_image),
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Breed: ${dogImage.message}",
                modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
