package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    var itemIndex by remember {
        mutableStateOf(0)
    }
    val normalPrevious = {itemIndex -= 1}
    val normalNext = {itemIndex += 1}
    when (itemIndex) {
        0 -> ArtSpaceLayout(
            artItem = items[0],
            onPrevious = {itemIndex = 10},
            onNext = normalNext
        )
        // all the other cases
        else -> ArtSpaceLayout(
            artItem = items[items.size - 1],
            onPrevious = normalPrevious,
            onNext = {itemIndex = 0}
        )
    }
}

@Composable
fun ArtSpaceLayout(
    artItem : ArtItem,
    onPrevious : () -> Unit,
    onNext : () -> Unit
) {
    Column() {
        Image(
            painter = painterResource(artItem.image),
            contentDescription = stringResource(artItem.imageDescription))
        Column() {
            Text(
                text = stringResource(artItem.title)
                // add styling
            )
            Row() {
                Text(
                    text = stringResource(artItem.author)
                    // add styling
                )
                Text(
                    text = stringResource(artItem.year)
                    // add styling
                )
            }
        }
        Row() {
            Button(onClick = onPrevious) {
                Text(text = stringResource(R.string.previous_button))
            }
            Button(onClick = onNext) {
                Text(text = stringResource(R.string.next_button))
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}