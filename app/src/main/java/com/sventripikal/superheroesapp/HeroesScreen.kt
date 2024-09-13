package com.sventripikal.superheroesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sventripikal.superheroesapp.data.HeroesData
import com.sventripikal.superheroesapp.model.Hero
import com.sventripikal.superheroesapp.ui.theme.SuperHeroesAppTheme

/**
 *  @param hero: hero item containing hero information
 *  @param modifier: modifiers applied to composable
 */
@Composable
fun HeroListItem(   // composable displaying hero info on a card
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.large, // 16dp rounded corner shape
        elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.two_dp)),  // 2dp elevation
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() // fill card width
                .padding(dimensionResource(R.dimen.sixteen_dp))     // 16dp spacing from card edges
                .height(dimensionResource(R.dimen.seventy_two_dp))  // 72dp row height
        ) {
            HeroInfoColumn(hero, Modifier.weight(1f))   // hero info space takes 3/4 card size

            Spacer(Modifier.width(dimensionResource(R.dimen.sixteen_dp)))   // 16dp horizontal spacer

            Image(
                painter = painterResource(hero.image),  // hero image
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.seventy_two_dp))    // 72dp image size
                    .clip(MaterialTheme.shapes.small)   // 8dp rounded corners
            )
        }
    }
}


/**
 *  @param hero: hero item containing hero information
 *  @param modifier: modifiers applied to composable
 */
@Composable
fun HeroInfoColumn(     // composable containing hero name & description
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,   // center text vertically
        modifier = modifier.fillMaxSize()   // fill entire empty space
    ) {
        Text(
            text = stringResource(hero.name),
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = stringResource(hero.description),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


/**
 *  @param listOfHeroes: list of Hero items
 *  @param modifier: modifiers applied to composable
 */
@Composable
fun HeroLazyColumn(             // composable displaying heroes using LazyColumn
    listOfHeroes: List<Hero>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.sixteen_dp)),  // 16dp spacing around content
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.eight_dp)), // 8dp spacing between items
        modifier = modifier
    ) {
        items(listOfHeroes) { hero ->
            HeroListItem(hero)  // create Hero card for each item
        }
    }
}


// Hero card preview
@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    SuperHeroesAppTheme(darkTheme = true) {
        HeroListItem(HeroesData.heroesList[0])
    }
}


// Hero list displayed using LazyColumn preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeroLazyColumnPreview() {
    SuperHeroesAppTheme(darkTheme = false) {
        HeroLazyColumn(HeroesData.heroesList)
    }
}
