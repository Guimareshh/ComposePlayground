package lucien.guimaraes.composeplayground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        val searchFieldState = rememberSearchFieldState()
        val focusManager = LocalFocusManager.current
        Search(
            onBackClicked = {
                focusManager.clearFocus()
                searchFieldState.query = TextFieldValue(searchFieldState.previousSearch)
            },
            query = searchFieldState.query,
            onQueryChange = {
                searchFieldState.query = it
            },
            focused = searchFieldState.focused,
            onFocusChange = {
                if (it) searchFieldState.query = TextFieldValue()
                searchFieldState.focused = it
            },
            onClearQueryClicked = {
                searchFieldState.query = TextFieldValue()
            },
        )
        AnimatedVisibility(
            visible = searchFieldState.focused,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            val searchQuery = "My shiny Pokemon"
            TextButton(onClick = {
                focusManager.clearFocus()
                searchFieldState.query = TextFieldValue(searchQuery)
                searchFieldState.previousSearch = searchQuery
            }) {
                Text(searchQuery)
            }

        }
    }
}
