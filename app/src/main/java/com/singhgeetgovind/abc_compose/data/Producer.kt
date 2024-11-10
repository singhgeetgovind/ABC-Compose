package com.singhgeetgovind.abc_compose.data

import com.singhgeetgovind.abc_compose.R

object Producer {
    private var count = 1
        get() = field++
    val item1 = listOf<Item>(
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
        Item(R.drawable.dog1,"Dog$count"),
    )
    val item2 = listOf<Item>(
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
        Item(R.drawable.dog2,"Dog$count"),
    )
}