package lucien.guimaraes.composeplayground

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lucien.guimaraes.composeplayground.HomeDestinations.EXPLORE_ROUTE
import lucien.guimaraes.composeplayground.HomeDestinations.DRAWER_ROUTE

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
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Explore.route,
        ) {
            composable(Screen.Explore.route) {
                BackHandler(onBack = {
                    (context as ComponentActivity).finish()
                } )
                MainScreen()
            }
            composable(Screen.Maps.route) {
                BackHandler(onBack = { navController.popBackStack() })
                DrawerScreen()
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
    object Maps : Screen(DRAWER_ROUTE, "Drawer", R.drawable.ic_map)
}


/**
 * Destinations used in the ([HomeBottomBar)]).
 */
private object HomeDestinations {
    const val EXPLORE_ROUTE = "explore"
    const val DRAWER_ROUTE = "drawer"
}
