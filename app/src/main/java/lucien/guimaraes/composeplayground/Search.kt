package lucien.guimaraes.composeplayground

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Search(
    onBackClicked: () -> Unit,
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    focused: Boolean,
    onFocusChange: (Boolean) -> Unit,
    onClearQueryClicked: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
            BasicTextField(
                modifier = Modifier
                    .onFocusChanged { focusState -> onFocusChange(focusState.isFocused) }
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(20.dp),
                    ),
                value = query,
                onValueChange = onQueryChange,
                textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (focused) {
                            IconButton(
                                modifier = Modifier.weight(2f),
                                onClick = { onBackClicked.invoke() },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onSurface,
                                )
                            }
                        } else {
                            Icon(
                                modifier = Modifier.weight(2f),
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                            )
                        }
                        Box(modifier = Modifier
                            .weight(8f)
                            .padding(start = 12.dp)) {
                            if (query.text.isEmpty() && focused.not()) {
                                Text(
                                    text = "What are you looking for?",
                                    style = MaterialTheme.typography.body1,
                                    color = Color.Gray,
                                )
                            }
                            innerTextField()
                        }
                        IconButton(
                            modifier = Modifier.weight(2f),
                            onClick = { onClearQueryClicked.invoke() },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onBackClicked.invoke() }),
            )
        }
    }
}

@Composable
fun rememberSearchFieldState(
    query: TextFieldValue = TextFieldValue(),
    focused: Boolean = false,
    previousSearch: String = "",
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            previousSearch = previousSearch,
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    previousSearch: String,
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var previousSearch by mutableStateOf(previousSearch)
}
