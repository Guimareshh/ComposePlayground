package lucien.guimaraes.composeplayground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerScreen() {

    BoxWithConstraints {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
        val defaultPeekHeight by remember { derivedStateOf { (maxHeight.value * 0.35).toInt().dp } }

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = defaultPeekHeight,
            sheetContent = {
                BoxWithConstraints {
                    val sheetNavController = rememberNavController()
                    val maxHeightNavHost by remember {
                        derivedStateOf { maxHeight.value.dp - 16.dp }
                    }
                    NavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = sheetNavController,
                        startDestination = "ROUTE_A",
                    ) {

                        composable("ROUTE_A") {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Blue)
                            )
                        }
                        composable("ROUTE_B") {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Cyan)
                            )
                        }
                    }

                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            },
        )
    }
}