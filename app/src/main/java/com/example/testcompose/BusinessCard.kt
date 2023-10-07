package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcompose.ui.theme.TestComposeTheme

class BusinessCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Card()
                }
            }
        }
    }
}

@Composable
fun Card() {
    BasicInformation(
        name = stringResource(id = R.string.text_bc_name),
        title = stringResource(id = R.string.text_bc_title)
    )
    Contacts()
}

@Composable
fun BasicInformation(name: String, title: String) {
    val photo = painterResource(id = R.drawable.android_logo)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 8.dp)
    ) {
        Image(
            painter = photo,
            contentDescription = null,
            Modifier
                .background(
                    color = colorResource(
                        id = R.color.android_background_color
                    )
                )
                .width(150.dp)
                .height(150.dp)
        )
        Text(
            text = name,
            fontSize = 44.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = title,
            color = colorResource(id = R.color.dark_green),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Contacts() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(bottom = 32.dp)
    ) {
        Contact(
            iconId = R.drawable.ic_phone,
            contact = stringResource(id = R.string.text_bc_phone_number),
        )
        Contact(
            iconId = R.drawable.ic_share,
            contact = stringResource(id = R.string.text_bc_social_media)
        )
        Contact(
            iconId = R.drawable.ic_email,
            contact = stringResource(id = R.string.text_bc_email)
        )
    }
}

@Composable
fun Contact(iconId: Int, contact: String) {
    val icon = painterResource(id = iconId)
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
            )
        Text(
            text = contact,
            modifier = Modifier
                .weight(3f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    TestComposeTheme {
        Card()
    }
}