package com.jetpack.main.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val number: Int,
    @DrawableRes val image: Int,
)
