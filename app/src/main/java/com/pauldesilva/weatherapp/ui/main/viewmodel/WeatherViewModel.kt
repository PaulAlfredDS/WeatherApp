package com.pauldesilva.weatherapp.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauldesilva.weatherapp.data.model.Interval
import com.pauldesilva.weatherapp.data.repository.WeatherDataRepository
import kotlinx.coroutines.launch


class WeatherViewModel @ViewModelInject constructor(
    private val repository: WeatherDataRepository
) : ViewModel() {
    val mWeatherDataList = MutableLiveData<List<Interval>>()

    val mWeatherDetailDataList = MutableLiveData<List<Interval>>()

    fun getWeatherData(startTime: String, endTime: String) {
        if (startTime.isEmpty() || endTime.isEmpty()) {
            mWeatherDataList.postValue(ArrayList())
            return
        }
        viewModelScope.launch {
            mWeatherDataList.postValue(repository.observeWeatherLiveData(startTime, endTime))
        }
    }

    fun getWeatherDetailData(startTime: String, endTime: String) {
        if (startTime.isEmpty() || endTime.isEmpty()) {
            mWeatherDetailDataList.postValue(ArrayList())
            return
        }
        viewModelScope.launch {
            mWeatherDetailDataList.postValue(
                repository.observeWeatherDetailLiveData(
                    startTime,
                    endTime
                )
            )
        }

    }
}