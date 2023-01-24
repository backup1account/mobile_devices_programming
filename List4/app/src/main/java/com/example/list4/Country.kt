package com.example.list4

import com.google.gson.annotations.SerializedName

data class Country(
    val name: String,
    val capital: String,

    @SerializedName("flag")
    val imgPath: String
)