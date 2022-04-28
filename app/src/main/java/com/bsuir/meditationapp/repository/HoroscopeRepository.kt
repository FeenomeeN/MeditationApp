package com.bsuir.meditationapp.repository

import com.bsuir.meditationapp.api.HoroscopeApi
import com.bsuir.meditationapp.model.Horoscope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class HoroscopeRepository(private val api: HoroscopeApi) : IHoroscopeRepository {

    override suspend fun getHoroscopes(): List<Horoscope> {
        return withContext(Dispatchers.IO) {
            listOf(
                async { api.getAquarius() },
                async { api.getPisces() },
                async { api.getAries() },
                async { api.getTaurus() },
                async { api.getGemini() },
                async { api.getCancer() },
                async { api.getLeo() },
                async { api.getLibra() },
                async { api.getVirgo() },
                async { api.getScorpio() },
                async { api.getSagittarius() },
                async { api.getCapricorn() }
            ).awaitAll()
        }
    }

}

interface IHoroscopeRepository {

    suspend fun getHoroscopes(): List<Horoscope>

}