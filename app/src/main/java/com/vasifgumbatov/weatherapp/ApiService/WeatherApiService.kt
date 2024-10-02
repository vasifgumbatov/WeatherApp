package com.vasifgumbatov.weatherapp.ApiService

import com.vasifgumbatov.weatherapp.DataClass.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("key") apiKey: String
    ): Response<CurrentWeatherResponse>

    @GET("current.json")
    suspend fun getCurrentWeatherByCityNew(
        @Query("q") city: String,
        @Query("key") apiKey: String
    ): Response<CurrentWeatherResponse>
}