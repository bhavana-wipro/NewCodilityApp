package com.example.appcodility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appcodility.domain.model.FreeGames
import com.example.appcodility.presentation.free_game.components.FreeGameItem
import com.example.appcodility.presentation.free_game.components.GameScreen
import com.example.appcodility.presentation.free_game.state.FreeGameState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameScreenTest {

   @get:Rule
   val composeTestRule = createComposeRule()

    @Test
    fun testFreeGameItem() {
        // Start the app
        val gameItems = FreeGames("Sample Game Url", 1, "Sample Description", "sample_thumbnail_url", "Sample Title")

        composeTestRule.setContent {
            FreeGameItem(games = gameItems)
        }
        composeTestRule.onNodeWithContentDescription("Thumbnail").assertIsDisplayed()
    }

    @Test
    fun testGameScreenIsLoadingFalse() {
        val sampleGameState = FreeGameState (
            freeGames = listOf(
                FreeGames("Sample Game Url", 1, "Sample Description 1", "sample_thumbnail_url_1", "Sample Title 1"),
                FreeGames("Sample Game Url", 1, "Sample Description 1", "sample_thumbnail_url_1", "Sample Title 2")
            ),
            isLoading = false
        )

        composeTestRule.setContent {
            GameScreen(freeGameState = sampleGameState)
        }

        composeTestRule.onNodeWithText("Sample Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sample Title 2").assertIsDisplayed()
    }

    @Test
    fun testGameScreenIsLoadingTrue() {
        val sampleGameState = FreeGameState (
            freeGames = listOf(
                FreeGames("Sample Game Url", 1, "Sample Description 1", "sample_thumbnail_url_1", "Sample Title 1"),
                FreeGames("Sample Game Url", 1, "Sample Description 1", "sample_thumbnail_url_1", "Sample Title 2")
            ),
            isLoading = true
        )

        composeTestRule.setContent {
            GameScreen(freeGameState = sampleGameState)
        }

        composeTestRule.onNodeWithText("Sample Title 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sample Title 2").assertIsDisplayed()
    }

    @Test
    fun testProgressBarNotDisplayed() {
        composeTestRule.setContent {
            Box(modifier = Modifier.fillMaxSize()) {}
        }
        composeTestRule.onNodeWithTag("Progress indicator").assertDoesNotExist()
    }

    @Test
    fun testProgressBarDisplayed() {
        composeTestRule.setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                  CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).testTag("Progress indicator"))
            }
        }
        composeTestRule.onNodeWithTag("Progress indicator").assertIsDisplayed()
    }
}