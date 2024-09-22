package com.vasifgumbatov.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasifgumbatov.weatherapp.DataClass.CurrentWeatherResponse
import com.vasifgumbatov.weatherapp.databinding.FragmentWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel by viewModels<WeatherVM>()
    private var adapterWeather = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterWeather = WeatherAdapter()
        val apiKey = "cd2d46eaef6f4c9db83105148241009"
        val city = "Baku"
        viewModel.getVM(city, apiKey)
        viewModel.getWeatherData().observe(viewLifecycleOwner, Observer { weatherResponse ->
            weatherResponse?.let {
                val currentWeather = listOf(it.current)
                adapterWeather.updateData(currentWeather)
                binding.cityText.text = it.location.name
                binding.tempText.text = "${it.current.temperature} °C"
                binding.weatherText.text = it.current.condition.text
            }
        })
    }
}