package com.example.artspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtItem (
    @DrawableRes val image : Int,
    @StringRes val title : Int,
    @StringRes val author : Int,
    @StringRes val year : Int
)