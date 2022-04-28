package com.bsuir.meditationapp.provider

import com.bsuir.meditationapp.api.HoroscopeApi
import retrofit2.Retrofit

object ApiProvider {

    fun provideHoroscopeApi(retrofit: Retrofit): HoroscopeApi =
        retrofit.create(HoroscopeApi::class.java)

}