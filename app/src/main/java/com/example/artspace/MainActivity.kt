package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.data.Datasource
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
    val items = Datasource.loadArtItems()
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Alignment.Bottom)
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp,
                top = 100.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (itemIndex) {
            0 -> {
                ArtSpaceLayout(
                    artItem = items[0],
                    modifier = Modifier.weight(1.7f)
                )
                Spacer(Modifier
                    //.height(180.dp)
                    .weight(1f))
                ButtonRow(
                    onPrevious = {itemIndex = 10},
                    onNext = normalNext,
                    modifier = Modifier.weight(0.3f)
                )
            }

            1 -> {
                ArtSpaceLayout(
                    artItem = items[1],
                    modifier = Modifier.weight(1.7f)
                )
                Spacer(Modifier.weight(1f))
                ButtonRow(
                    onPrevious = normalPrevious,
                    onNext = normalNext,
                    modifier = Modifier.weight(0.3f)
                )
            }
            2 -> {
                ArtSpaceLayout(
                    artItem = items[2],
                    modifier = Modifier.weight(1.7f)
                )
                Spacer(Modifier.weight(1f))
                ButtonRow(
                    onPrevious = normalPrevious,
                    onNext = normalNext,
                    modifier = Modifier.weight(0.3f)
                )
            }
            // all the other cases
            else -> {
                ArtSpaceLayout(
                    artItem = items.last(),
                    modifier = Modifier.weight(1.7f)
                )
                Spacer(Modifier.weight(1f))
                ButtonRow(
                    onPrevious = normalPrevious,
                    onNext = {itemIndex = 0},
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
    }

}

@Composable
fun ArtSpaceLayout(
    artItem : ArtItem,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .border(2.dp, Color.DarkGray)
                .padding(24.dp)
                .shadow(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(artItem.image),
                contentDescription = stringResource(artItem.imageDescription),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            )
        }
        Spacer(Modifier.height(48.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(color = Color.White)
                .shadow(2.dp)
                .wrapContentWidth(Alignment.Start)
        ) {
            Row {
                Text(
                    text = stringResource(artItem.title),
                    color = Color.Black,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 12.dp,
                            top = 8.dp)
                )
            }
            Row {
                Text(
                    text = stringResource(artItem.author),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            bottom = 8.dp
                        )
                )
                Spacer(Modifier.width(1.dp))
                Text(
                    text = "(${stringResource(artItem.year)})",
                    color = Color.DarkGray,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }
        }
    }
}

@Composable
fun ButtonRow(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.spacedBy(48.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.previous_button))
        }
        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(R.string.next_button))
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