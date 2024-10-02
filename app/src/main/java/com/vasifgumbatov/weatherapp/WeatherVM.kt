package com.vasifgumbatov.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.vasifgumbatov.weatherapp.ApiService.WeatherApiService
import com.vasifgumbatov.weatherapp.DataClass.CurrentWeather
import com.vasifgumbatov.weatherapp.DataClass.CurrentWeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherVM @Inject constructor(
    private val weatherApiService: WeatherApiService
) : ViewModel() {
    fun getWeatherData(): LiveData<CurrentWeatherResponse> = weatherData
    private val weatherData = MutableLiveData<CurrentWeatherResponse>()

    fun getWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            val result = apiCall { weatherApiService.getCurrentWeatherByCityNew(city, apiKey) }
            when (result) {
                is ApiResult.Success<*> -> {
                    val data = result.data as? CurrentWeatherResponse
                    data?.let {
                        weatherData.postValue(it)
                    }
                }
                is ApiResult.Error -> {
                }
            }
        }
    }
}

private suspend fun <T> apiCall(call: suspend () -> Response<T>): ApiResult<T> {
    val result = call.invoke()
    return try {
        if (result.isSuccessful && result.body() != null) {
            ApiResult.Success(result.body()!!)
        } else {
            val gson = Gson()
            val jsonObject = JSONObject(result.errorBody()?.charStream()?.readText() ?: "{}")
            val error = gson.fromJson(jsonObject.toString(), ErrorModel::class.java)
            ApiResult.Error(error)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ApiResult.Error(
            ErrorModel(errorCode = 505, errorTitle = "Xeta", errorDescription = "Sistem xetasi")
        )
    }
}