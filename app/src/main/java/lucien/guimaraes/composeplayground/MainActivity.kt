package lucien.guimaraes.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mapbox.mapboxsdk.Mapbox

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(
            applicationContext,
            getString(R.string.mapbox_access_token)
        )

        setContent {
            HomeScreen()
        }
    }
}
