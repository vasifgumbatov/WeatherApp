package com.vasifgumbatov.weatherapp.DataClass

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    var location: WeatherLocation,
    var current: CurrentWeather,
)

data class WeatherLocation(
    var name: String,
    var region: String,
    var country: String
)

data class CurrentWeather(
    @SerializedName("temp_c")
    var temperature: Double,
    @SerializedName("condition")
    var condition: Condition
)

data class Condition(
    var text: String,
    var icon: String,
)
