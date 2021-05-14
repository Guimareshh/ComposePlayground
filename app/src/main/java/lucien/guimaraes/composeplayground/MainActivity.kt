package lucien.guimaraes.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mapbox.mapboxsdk.Mapbox
import lucien.guimaraes.composeplayground.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Mapbox.getInstance(
            applicationContext,
            getString(R.string.mapbox_access_token)
        )

        binding.composeView.setContent {
            MainScreen()
        }
    }
}
