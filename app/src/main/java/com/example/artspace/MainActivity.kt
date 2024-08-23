package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

val images = listOf(
    R.drawable.francesco_ungaro_1_unsplash,
    R.drawable.francesco_ungaro_2_unsplash,
    R.drawable.francesco_ungaro_3_unsplash,
    R.drawable.francesco_ungaro_4_unsplash,
    R.drawable.francesco_ungaro_5_unsplash,
    R.drawable.francesco_ungaro_6_unsplash,
    R.drawable.francesco_ungaro_7_unsplash
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface {
                    ArtSpaceMainScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceMainScreen() {

    var currentImageNumber by remember {
         mutableIntStateOf(0)
    }

    val currentImage = images[currentImageNumber]

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Header(artistName = R.string.francesco_ungaro)
        Spacer(modifier = Modifier.height(4.dp))
        ImageViewer(image = currentImage)
        Spacer(modifier = Modifier.height(16.dp))
        Footer(
            artistName = R.string.francesco_ungaro,
            publishYear = R.string.year,
            artistUnsplashURL = R.string.francesco_ungaro_unsplash_link
        )
        NavigationButtons(
            currentImageNumber = currentImageNumber,
            onPreviousClicked = {
                if(currentImageNumber > 0) {
                    currentImageNumber--
                } else {
                    currentImageNumber = images.size - 1
                }
            },
            onNextClicked = {
                if(currentImageNumber < images.size - 1) {
                    currentImageNumber++
                } else {
                    currentImageNumber = 0
                }
            }
        )
    }
}


@Composable
fun Header(
    @StringRes artistName: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "${stringResource(artistName)}'s \nArt Space",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    )
}

@Composable
fun ImageViewer(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(
                    listOf(
                        Color.White,
                        Color.Black
                    ),
                    center = Offset.Infinite
                ),
                RectangleShape
            )
            .background(
                Color.Black.copy(alpha = .1f), RectangleShape
            )
    )
}

@Composable
fun Footer(
    @StringRes artistName: Int,
    @StringRes publishYear: Int,
    @StringRes artistUnsplashURL: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = artistName),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = publishYear),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            buildAnnotatedString {
                append(stringResource(id = artistUnsplashURL))

            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun NavigationButtons(
    currentImageNumber: Int,
    onPreviousClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ButtonElement("Previous", onClick = onPreviousClicked)
        ButtonElement("Next", onClick = onNextClicked)
    }
}

@Composable
fun ButtonElement(
    text: String,
    onClick: () -> Unit
    ) {
    ElevatedButton(
        onClick = onClick,
        contentPadding = ButtonDefaults.ContentPadding,
        colors = ButtonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Red
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 8.dp,
            focusedElevation = 16.dp,
            hoveredElevation = 16.dp,
        ),
        modifier = Modifier
            .width(120.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceMainScreen()
    }
}