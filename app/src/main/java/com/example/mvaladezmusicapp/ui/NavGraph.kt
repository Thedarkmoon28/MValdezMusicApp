package com.example.mvaladezmusicapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mvaladezmusicapp.ui.screens.HomeScreen
import com.example.mvaladezmusicapp.ui.screens.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val albumId: String)

@Composable
fun MusicNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onAlbumClick = { id ->
                    navController.navigate(Detail(id))
                }
            )
        }
        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(albumId = detail.albumId)
        }
    }
}
