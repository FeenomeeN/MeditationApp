package com.bsuir.meditationapp.util

import com.bsuir.meditationapp.provider.ApiProvider
import com.bsuir.meditationapp.provider.RetrofitProvider


fun provideHoroscopesApi() = ApiProvider.provideHoroscopeApi(RetrofitProvider.getHoroscopeRetrofit())