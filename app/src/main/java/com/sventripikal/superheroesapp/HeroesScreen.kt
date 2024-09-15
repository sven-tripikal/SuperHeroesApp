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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesApp(             // main app composable
    modifier: Modifier = Modifier
) {
//  hides TitleBar while scrolling MainScreen content
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = { TitleBar(scrollBehavior) }, // top screen section
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection) // tracks scroll events
    ) { contentPadding ->
        MainScreen(     // bottom screen section
            modifier = Modifier.padding(contentPadding)
        )
    }
}


/**
 *  @param modifier: modifiers applied to composable
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(       // top screen header composable
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {   // center-aligned text composable for title
            Text(
                text = stringResource(R.string.superheroes_text),
                style = MaterialTheme.typography.displayLarge,
                overflow = TextOverflow.Visible
            )
        },
        scrollBehavior = scrollBehavior,    // hides header while scrolling MainScreen content
        modifier = modifier
    )
}


/**
 *  @param modifier: modifiers applied to composable
 */
@Composable
private fun MainScreen(     // main composable displaying heroes using LazyColumn
    modifier: Modifier = Modifier
) {
    // get heroes list
    val listOfHeroes = HeroesData.heroesList

    LazyColumn(     // LazyColumn to display hero list
        contentPadding = PaddingValues(dimensionResource(R.dimen.sixteen_dp)),  // 16dp spacing around entire content
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.eight_dp)), // 8dp spacing between items
        modifier = modifier
    ) {
        items(listOfHeroes) { hero ->
            HeroListItem(hero)  // create Hero card for each item in list
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
