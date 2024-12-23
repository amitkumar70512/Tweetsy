package com.amit.tweetsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amit.tweetsy.screens.CategoryScreen
import com.amit.tweetsy.screens.DetailScreen
import com.amit.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Tweetsy") },
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }

    }
}


@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen {
                navController.navigate("detail/$it")
            }

        }
        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType }
            )
        ) {
            DetailScreen()
        }
    }
}
