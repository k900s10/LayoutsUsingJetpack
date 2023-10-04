package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcompose.ui.theme.TestComposeTheme

class ComposeQuadrant : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quadrant()
                }
            }
        }
    }
}

@Composable
fun Quadrant(modifier: Modifier = Modifier) {
    Column{
        Row(
            modifier = modifier
                .weight(1f)
        ) {
            CardView(
                title = stringResource(
                    id = R.string.compose_quadrant_content_text_title
                ),
                description = stringResource(
                    id = R.string.compose_quadrant_content_text_content
                ),
                color = colorResource(
                    id = R.color.compose_quadrant_content_text
                ),
                modifier = Modifier
                    .weight(1f)
            )
            CardView(
                title = stringResource(
                    id = R.string.compose_quadrant_content_image_title
                ),
                description = stringResource(
                    id = R.string.compose_quadrant_content_image_content
                ),
                color = colorResource(
                    id = R.color.compose_quadrant_content_image
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = modifier
                .weight(1f),
        ) {
            CardView(
                title = stringResource(
                    id = R.string.compose_quadrant_content_row_title
                ),
                description = stringResource(
                    id = R.string.compose_quadrant_content_row_content
                ),
                color = colorResource(
                    id = R.color.compose_quadrant_content_row
                ),
                modifier = Modifier
                    .weight(1f)
            )
            CardView(
                title = stringResource(
                    id = R.string.compose_quadrant_content_column_title
                ),
                description = stringResource(
                    id = R.string.compose_quadrant_content_column_content
                ),
                color = colorResource(
                    id = R.color.compose_quadrant_content_column
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun CardView(
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    TestComposeTheme {
        Quadrant()
    }
}