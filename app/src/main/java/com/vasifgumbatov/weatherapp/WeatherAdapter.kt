package com.vasifgumbatov.weatherapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasifgumbatov.weatherapp.DataClass.CurrentWeather
import com.vasifgumbatov.weatherapp.databinding.ItemsBinding

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var weatherList: List<CurrentWeather> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<CurrentWeather>) {
        weatherList = newList.toMutableList()
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(private val binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: CurrentWeather){

//            binding.highLowTemp.text = "H: ${weather.temperature} `C"
            binding.tempText.text = weather.temperature.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }


}