package com.pauldesilva.weatherapp.repository

import com.pauldesilva.weatherapp.data.model.Interval
import com.pauldesilva.weatherapp.data.model.Values
import com.pauldesilva.weatherapp.data.repository.WeatherDataRepository
import kotlin.math.min

class FakeWeatherDataRepository : WeatherDataRepository {
    private var mIntervals : List<Interval> = ArrayList()

    override suspend fun observeWeatherLiveData(
        startTime: String,
        endTime: String
    ): List<Interval> {
        mIntervals = ArrayList()
        for(i in 0..10){
            (mIntervals as ArrayList<Interval>).add(Interval("2021-10-01T00:00:00Z", Values(23.33, 40001)))
        }
        return mIntervals
    }

    override suspend fun observeWeatherDetailLiveData(
        startTime: String,
        endTime: String
    ): List<Interval> {
        mIntervals = ArrayList()
        for(i in 0..23){
            (mIntervals as ArrayList<Interval>).add(Interval("2021-10-01T00:00:00Z", Values(23.33, 40001)))
        }
        return mIntervals
    }
}