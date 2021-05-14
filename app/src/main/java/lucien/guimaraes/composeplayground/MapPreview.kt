package lucien.guimaraes.composeplayground

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.Style

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {

    val pagerState = rememberPagerState(pageCount = 10)
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        MapPreview()
        HorizontalPager(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            state = pagerState,
            itemSpacing = 8.dp,
        ) { page ->
            Card(
                modifier = Modifier.size(width = 300.dp, height = 100.dp).padding(bottom = 8.dp),
                elevation = 4.dp,
                backgroundColor = Color.DarkGray,
            ) {
                Text(
                    text = "Page: $page",
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun MapPreview(mapView: MapView = rememberMapViewWithLifecycle()) {
    var mapInitialized by remember(mapView) { mutableStateOf(false) }
    LaunchedEffect(mapInitialized) {
        val mapboxMap = mapView.awaitMap()
        mapboxMap.setStyle(Style.OUTDOORS)
        mapInitialized = true
    }
    AndroidView(factory = { mapView })
}
