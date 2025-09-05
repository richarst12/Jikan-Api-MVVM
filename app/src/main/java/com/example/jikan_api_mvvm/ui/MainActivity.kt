package com.example.jikan_api_mvvm.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jikan_api_mvvm.ui.dashboard.DashBoardScreen
import com.example.jikan_api_mvvm.ui.detail.DetailScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jikan_api_mvvm.ui.theme.JikanApiMVVMTheme
import com.example.jikan_api_mvvm.ui.viewmodel.MainViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JikanApiMVVMTheme {
                Surface {
                    val nav = rememberNavController()
                    val mainViewModel: MainViewModel = hiltViewModel()

                    NavHost(
                        navController = nav,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            DashBoardScreen(
                                viewModel = mainViewModel,
                                onAnimeClick = { id -> nav.navigate("detail/$id") },
                                onRetry = { mainViewModel.loadTopAnime() }
                            )
                        }
                        composable(
                            "detail/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments!!.getInt("id")
                            DetailScreen(
                                id = id,
                                viewModel = mainViewModel,
                                onBack = { nav.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
