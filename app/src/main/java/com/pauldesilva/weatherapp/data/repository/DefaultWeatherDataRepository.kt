package com.pauldesilva.weatherapp.data.repository

import com.pauldesilva.weatherapp.data.api.WeatherApi
import com.pauldesilva.weatherapp.data.model.Interval
import javax.inject.Inject

class DefaultWeatherDataRepository @Inject constructor(
    private val mWeatherApi: WeatherApi
) : WeatherDataRepository {

    override suspend fun observeWeatherLiveData(
        startTime: String,
        endTime: String
    ): List<Interval> {
        if(startTime.isEmpty() || endTime.isEmpty()) {
            return ArrayList()
        }

        val response = mWeatherApi.getWeather(startTime = startTime, endTime = endTime)
        val weatherDataValues: ArrayList<Interval> = ArrayList()
        if (response.isSuccessful) {
            for (item in response.body()!!.data.timelines?.get(0)!!.intervals!!) {
                weatherDataValues.add(item)
            }
        }
        return weatherDataValues
    }

    override suspend fun observeWeatherDetailLiveData(
        startTime: String,
        endTime: String
    ): List<Interval> {
        if(startTime.isEmpty() || endTime.isEmpty()) {
            return ArrayList()
        }

        val response =
            mWeatherApi.getWeather(timeSteps = "1h", startTime = startTime, endTime = endTime)
        val weatherDataValues: ArrayList<Interval> = ArrayList()
        if (response.isSuccessful) {
            for (item in response.body()!!.data.timelines?.get(0)!!.intervals!!) {
                weatherDataValues.add(item)
            }
        }
        return weatherDataValues
    }
}