package lucien.guimaraes.composeplayground

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lucien.guimaraes.composeplayground.HomeDestinations.EXPLORE_ROUTE
import lucien.guimaraes.composeplayground.HomeDestinations.MAPS_ROUTE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            HomeBottomBar(
                navController = navController,
                tabItemList = items,
            )
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Explore.route) {
            composable(Screen.Explore.route) {
                BackHandler(onBack = {
                    (context as ComponentActivity).finish()
                } )
                PagerScreen()
            }
            composable(Screen.Maps.route) {
                BackHandler(onBack = { navController.popBackStack() })
                MapPreview(innerPadding)
            }
        }
    }
}

private val items = listOf(
    Screen.Explore,
    Screen.Maps,
)

sealed class Screen(val route: String,val title: String, @DrawableRes val icon: Int) {
    object Explore : Screen(EXPLORE_ROUTE, "Explore", R.drawable.ic_search)
    object Maps : Screen(MAPS_ROUTE, "Map", R.drawable.ic_map)
}


/**
 * Destinations used in the ([HomeBottomBar)]).
 */
private object HomeDestinations {
    const val EXPLORE_ROUTE = "explore"
    const val MAPS_ROUTE = "maps"
}
