package com.vasifgumbatov.weatherapp

import com.vasifgumbatov.weatherapp.DataClass.CurrentWeatherResponse

sealed class ApiResult<out T> {
    data class Success<T>(val data: T?): ApiResult<T>()
    data class Error(val error: ErrorModel?): ApiResult<Nothing>()
}