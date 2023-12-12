package com.jetpack.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jetpack.main.data.HeroesRepository
import com.jetpack.main.model.Hero
import com.jetpack.main.ui.theme.SuperHeroesTheme

class HeroesScreen {

    @Composable
    fun HeroesList(heroes: List<Hero>, modifier: Modifier = Modifier) {
        LazyColumn(modifier) {
            items(heroes) { hero ->
                ItemHeroes(hero = hero)
            }
        }
    }

    @Composable
    fun ItemHeroes(
        hero: Hero,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
        ) {
            ConstraintLayout(
                modifier
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp)
                    .fillMaxWidth(),
//                    .background(Color.Red),
            ) {
                val (name, description, image) = createRefs()

                Text(
                    text = stringResource(id = hero.nameRes),
                    Modifier.constrainAs(name) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(image.start, margin = 16.dp)
                        width = Dimension.fillToConstraints

                    },
                    style = MaterialTheme.typography.displaySmall,
                    maxLines = 2
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    Modifier.constrainAs(description) {
                        top.linkTo(name.bottom)
                        start.linkTo(name.start)
                        end.linkTo(image.start, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
                SuperHeroesImage(image = hero.imageRes, Modifier.constrainAs(image) {
                    top.linkTo(name.top)
                    bottom.linkTo(description.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(name.end)
                })
            }
        }
    }

    @Composable
    fun SuperHeroesImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .size(72.dp)
                .clip(MaterialTheme.shapes.small)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.FillWidth
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HeroesPreview() {
        SuperHeroesTheme {
//            ItemHeroes(
//                hero = Hero(
//                    R.string.hero1,
//                    R.string.description1,
//                    R.drawable.android_superhero1
//                )
//            )
            HeroesList(HeroesRepository.heroes)
        }
    }
}