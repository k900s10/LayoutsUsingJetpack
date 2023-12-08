package com.jetpack.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.main.model.Topic

class ItemTopic {

    @Composable
    fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(5.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = topic.image),
                    contentDescription = stringResource(id = topic.title)
                )
                Column(
                    Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = topic.title))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_grain),
                            contentDescription = painterResource(
                                id = R.drawable.ic_grain
                            ).toString()
                        )
                        Text(text = topic.number.toString(), Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TopicPreview() {
        TopicCard(
            Topic(
                title = R.string.business,
                number = 54,
                image = R.drawable.business
            )
        )
    }
}