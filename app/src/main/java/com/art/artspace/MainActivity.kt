package com.art.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            PhoneLayout(
                image = image,
                title = title,
                artist = artist,
                year = year,
                onClickNext = { currentPortrait = currentPortrait.onClickNext() },
                onClickPrevious = { currentPortrait = currentPortrait.onclickPrevious() },
            )
        }
        if (maxWidth > 600.dp) {
            TabletLayout(
                image = image,
                title = title,
                artist = artist,
                year = year,
                onClickPrevious = { currentPortrait = currentPortrait.onclickPrevious() },
                onClickNext = { currentPortrait = currentPortrait.onClickNext() })
        }
    }
}

private fun Int.onClickNext(): Int =
    if (this == 2) {
        1
    } else
        this + 1

private fun Int.onclickPrevious(): Int =
    if (this == 1) {
        2
    } else
        this - 1

@Composable
fun TabletLayout(
    @DrawableRes image: Int,
    title: String,
    artist: String,
    year: String,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConstraintLayout {
            val (portrait, description, controller) = createRefs()
            Wall(
                portrait = image,
                Modifier
                    .shadow(5.dp)
                    .constrainAs(portrait) {
                        top.linkTo(parent.top, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Description(
                title = title,
                artist = artist,
                year = year,
                Modifier
                    .background(color = colorResource(id = R.color.light_gray))
                    .padding(16.dp)
                    .constrainAs(description) {
                        top.linkTo(portrait.bottom, margin = 16.dp)
                        start.linkTo(portrait.start)
                        end.linkTo(portrait.end)
                        width = Dimension.fillToConstraints
                    }
            )
            Controller(
                onClickPrevious = onClickPrevious,
                onClickNext = onClickNext,
                Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
                    .constrainAs(controller) {
                        top.linkTo(description.bottom, margin = 24.dp)
//                        start.linkTo(parent.start, margin = 24.dp)
//                        end.linkTo(parent.end, margin = 24.dp)
                    },
            )
        }

    }
}

@Composable
fun PhoneLayout(
    @DrawableRes image: Int,
    title: String,
    artist: String,
    year: String,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            Modifier.weight(4f),
            verticalArrangement = Arrangement.Center
        ) {
            Wall(
                portrait = image,
                Modifier
                    .shadow(5.dp)
                    .fillMaxWidth()
            )
        }
        Column(
            Modifier
                .weight(1.5f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Description(
                title = title,
                artist = artist,
                year = year,
                Modifier
                    .background(color = colorResource(id = R.color.light_gray))
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Controller(
                onClickPrevious = onClickPrevious,
                onClickNext = onClickNext,
                Modifier
                    .fillMaxWidth(),
            )
        }
    }

}

@Composable
fun Wall(@DrawableRes portrait: Int, modifier: Modifier = Modifier) {
    Column(
        modifier,
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
fun Description(title: String, artist: String, year: String, modifier: Modifier = Modifier) {
    Column(
        modifier,
        verticalArrangement = Arrangement.SpaceAround,
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
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
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

@Preview(showBackground = true, name = "tablet", device = Devices.TABLET)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}