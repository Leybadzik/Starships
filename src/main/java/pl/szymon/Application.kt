package pl.szymon

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.szymon.starships.ui.StarshipDetailsScreen
import pl.szymon.starships.ui.StarshipsScreen

@Composable
fun Application() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable(route = "list") {
            StarshipsScreen(navController)
        }
        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            StarshipDetailsScreen(
                navBackStackEntry.arguments!!.getInt("id"), navController
            )
        }
    }
}