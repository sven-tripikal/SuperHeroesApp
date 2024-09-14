package com.sventripikal.superheroesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.sventripikal.superheroesapp.data.HeroesData
import com.sventripikal.superheroesapp.model.Hero
import com.sventripikal.superheroesapp.ui.theme.SuperHeroesAppTheme

/**
 *  @param modifier: modifiers applied to composable
 */
@Composable
fun SuperHeroesApp(             // composable displaying heroes using LazyColumn
    modifier: Modifier = Modifier
) {
    Surface(    // apply app background surface
        modifier = modifier.fillMaxSize()
    ) {
        // get heroes list
        val listOfHeroes = HeroesData.heroesList

        //  get LocalLayoutDirection
        val layoutDirection = LocalLayoutDirection.current

        LazyColumn(     // LazyColumn to display hero list
            contentPadding = PaddingValues(dimensionResource(R.dimen.sixteen_dp)),  // 16dp spacing around content
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.eight_dp)), // 8dp spacing between items
            modifier = Modifier
                .statusBarsPadding()        // top statusBar padding
                .navigationBarsPadding()    // bottom navigationBar padding
                .padding(
                    start = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection),  // left screen edge padding
                    end = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateEndPadding(layoutDirection)     // right screen edge padding
                )
        ) {
            items(listOfHeroes) { hero ->
                HeroListItem(hero)  // create Hero card for each item in list
            }
        }
    }
}


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

            Image(  // hero image
                painter = painterResource(hero.image),
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
        modifier = modifier.fillMaxSize()   // fill 3/4 card space & entire column space
    ) {
        Text(   // hero name
            text = stringResource(hero.name),
            style = MaterialTheme.typography.displaySmall,
            overflow = TextOverflow.Visible     // avoid text clipping
        )

        Text(   // hero description
            text = stringResource(hero.description),
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Visible     // avoid text clipping
        )
    }
}


// app Light preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeroAppLightPreview() {
    SuperHeroesAppTheme(darkTheme = false) {
        SuperHeroesApp()
    }
}


// app Dark preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeroAppDarkPreview() {
    SuperHeroesAppTheme(darkTheme = true) {
        SuperHeroesApp()
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
