package com.singhgeetgovind.abc_compose.data

import androidx.annotation.DrawableRes
import java.util.UUID

data class Item(
    @DrawableRes val drawRes : Int,
    val title : String,
    val unique:String = UUID.randomUUID().toString(),
)
