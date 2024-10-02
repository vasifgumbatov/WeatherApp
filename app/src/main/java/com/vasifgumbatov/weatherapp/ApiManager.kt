package com.vasifgumbatov.weatherapp

import com.vasifgumbatov.weatherapp.ApiService.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private var okhttpClient = OkHttpClient.Builder()
        .addInterceptor(WeatherInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    fun getApiService(): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}
