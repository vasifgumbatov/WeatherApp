package com.vasifgumbatov.weatherapp

import okhttp3.Interceptor
import okhttp3.Response

class WeatherInterceptor : Interceptor {
    private val key = "cd2d46eaef6f4c9db83105148241009"
    private val lang = "en"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val newUrl = "${chain.request().url}&key=$key&$lang=$lang"
        return chain.proceed(request.build())
    }
}
