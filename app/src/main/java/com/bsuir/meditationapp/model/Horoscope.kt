package com.bsuir.meditationapp.model

import com.google.gson.annotations.SerializedName

data class Horoscope(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String
)