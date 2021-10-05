package com.pauldesilva.weatherapp.data.repository

import com.pauldesilva.weatherapp.data.model.Interval

interface WeatherDataRepository {

    suspend fun observeWeatherLiveData(startTime : String, endTime: String) : List<Interval>
    suspend fun observeWeatherDetailLiveData(startTime : String, endTime: String) : List<Interval>
}