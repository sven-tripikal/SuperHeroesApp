package com.sventripikal.superheroesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 *  @param name: resource ID for the name of hero
 *  @param description: resource ID containing hero attributes
 *  @param image: resource ID providing hero photo
 */
data class Hero(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)