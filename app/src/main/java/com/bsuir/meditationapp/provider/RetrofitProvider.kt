package com.bsuir.meditationapp.provider

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private const val HOROSCOPE_BASE = "https://ohmanda.com"

    fun getHoroscopeRetrofit(httpClient: OkHttpClient = OkHttpClient()) =
        httpClient.defaultRetrofit().baseUrl(HOROSCOPE_BASE).build()

    private fun OkHttpClient.defaultRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(this)

}