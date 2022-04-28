package com.bsuir.meditationapp.api

import com.bsuir.meditationapp.model.Horoscope
import retrofit2.http.GET

interface HoroscopeApi {

    @GET("/api/horoscope/aquarius")
    suspend fun getAquarius(): Horoscope

    @GET("/api/horoscope/pisces")
    suspend fun getPisces(): Horoscope

    @GET("/api/horoscope/aries")
    suspend fun getAries(): Horoscope

    @GET("/api/horoscope/taurus")
    suspend fun getTaurus(): Horoscope

    @GET("/api/horoscope/gemini")
    suspend fun getGemini(): Horoscope

    @GET("/api/horoscope/cancer")
    suspend fun getCancer(): Horoscope

    @GET("/api/horoscope/leo")
    suspend fun getLeo(): Horoscope

    @GET("/api/horoscope/virgo")
    suspend fun getVirgo(): Horoscope

    @GET("/api/horoscope/libra")
    suspend fun getLibra(): Horoscope

    @GET("/api/horoscope/scorpio")
    suspend fun getScorpio(): Horoscope

    @GET("/api/horoscope/sagittarius")
    suspend fun getSagittarius(): Horoscope

    @GET("/api/horoscope/capricorn")
    suspend fun getCapricorn(): Horoscope

}