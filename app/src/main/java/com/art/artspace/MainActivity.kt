package com.art.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.art.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var currentPortrait by remember {
        mutableIntStateOf(1)
    }

    @DrawableRes val image = when (currentPortrait) {
        1 -> R.drawable.portrait_1
        2 -> R.drawable.portrait_2
        else -> R.drawable.ic_launcher_foreground
    }

    val title = when (currentPortrait) {
        1 -> "Answers of Freedom"
        2 -> "Miracles of Love"
        else -> ""
    }

    val artist = when (currentPortrait) {
        1 -> "Olena Bohovyk"
        2 -> "Olena Bohovyk"
        else -> ""
    }

    val year = when (currentPortrait) {
        1 -> "2019"
        2 -> "2020"
        else -> ""
    }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            Modifier.weight(4f),
            verticalArrangement = Arrangement.Center
        ) {
            Wall(portrait = image)
        }
        Column(
            Modifier
                .weight(1.5f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Description(
                title = title,
                artist = artist,
                year = year
            )
            Controller(
                onClickNext = {
                    if (currentPortrait == 2) {
                        currentPortrait = 1
                    } else
                        currentPortrait++
                },
                onClickPrevious = {
                    if (currentPortrait == 1) {
                        currentPortrait = 2
                    } else
                        currentPortrait--
                },
            )
        }
    }
}

@Composable
fun Wall(@DrawableRes portrait: Int) {
    Column(
        Modifier
            .shadow(5.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = portrait),
            contentDescription = portrait.toString(),
            Modifier
                .size(500.dp)
                .padding(48.dp),
        )
    }
}

@Composable
fun Description(title: String, artist: String, year: String) {
    Column(
        Modifier
            .background(color = colorResource(id = R.color.light_gray))
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.W300),
            maxLines = 2
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(artist)
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.W400)) {
                    append(" ($year)")
                }
            }, style = TextStyle(fontSize = 16.sp)
        )
    }
}

@Composable
fun Controller(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClickPrevious,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.purple_blue)
            ),
        ) {
            Column(
                Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_previous), fontSize = 16.sp)
            }
        }
        Button(
            onClickNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.purple_blue)
            ),
        ) {
            Column(
                Modifier.padding(start = 28.dp, end = 28.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_next), fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}