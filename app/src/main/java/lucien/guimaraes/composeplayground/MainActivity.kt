package lucien.guimaraes.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import lucien.guimaraes.composeplayground.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            MainScreen()
        }
    }
}
