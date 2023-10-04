package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcompose.ui.theme.TestComposeTheme

class ComposeArticle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Article(
                        title = stringResource(id = R.string.article_title),
                        topContent = stringResource(id = R.string.article_top_content),
                        bottomContent = stringResource(id = R.string.article_bottom_content)
                    )
                }
            }
        }
    }
}

@Composable
fun Article(
    title: String,
    topContent: String,
    bottomContent: String,
    modifier: Modifier = Modifier
) {
    Column {
        ArticleBackground()
        Text(
            text = title,
            modifier = modifier
                .padding(16.dp),
            fontSize = 24.sp,
        )
        Text(
            text = topContent,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify
        )
        Text(
            text = bottomContent,
            modifier = modifier
                .padding(16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ArticleBackground() {
    val imageBackground = painterResource(id = R.drawable.bg_compose_background__1_)
    Image(painter = imageBackground, contentDescription = "Image background")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TestComposeTheme {
        Article(
            title = stringResource(id = R.string.article_title),
            topContent = stringResource(id = R.string.article_top_content),
            bottomContent = stringResource(id = R.string.article_bottom_content)
        )
    }
}