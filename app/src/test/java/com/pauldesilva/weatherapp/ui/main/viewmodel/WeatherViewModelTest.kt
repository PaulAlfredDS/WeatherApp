package com.pauldesilva.weatherapp.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.pauldesilva.weatherapp.MainCoroutineRule
import com.pauldesilva.weatherapp.repository.FakeWeatherDataRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        viewModel = WeatherViewModel(FakeWeatherDataRepository())
    }

    @Test
    fun `get weather data for whole day correct parameters, returns 24 intervals`() {
        viewModel.getWeatherDetailData("2021-10-01T00:00:00Z", "2021-10-01T23:00:00Z")

        val value = viewModel.mWeatherDetailDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(24)
    }

    @Test
    fun `get weather data for whole day start date missing, returns 0 intervals`() {
        viewModel.getWeatherDetailData("", "2021-10-01T23:00:00Z")

        val value = viewModel.mWeatherDetailDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

    @Test
    fun `get weather data for whole day end date missing, returns 0 intervals`() {
        viewModel.getWeatherDetailData("2021-10-01T23:00:00Z", "")

        val value = viewModel.mWeatherDetailDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

    @Test
    fun `get weather data for whole day both missing parameters, returns 0 Intervals`() {
        viewModel.getWeatherDetailData("", "")

        val value = viewModel.mWeatherDetailDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

    @Test
    fun `get weather data for 10 days correct parameters,returns 11 intervals`() {
        viewModel.getWeatherData("2021-10-01T00:00:00Z", "2021-10-10T00:00:00Z")

        val value = viewModel.mWeatherDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(11)
    }

    @Test
    fun `get weather data for 10 days start date missing, returns 0 intervals`() {
        viewModel.getWeatherData("", "2021-10-01T23:00:00Z")

        val value = viewModel.mWeatherDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

    @Test
    fun `get weather data for 10 days end date missing, returns 0 intervals`() {
        viewModel.getWeatherData("2021-10-01T23:00:00Z", "")

        val value = viewModel.mWeatherDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

    @Test
    fun `get weather data for 10 days both missing parameters, returns 0 Intervals`() {
        viewModel.getWeatherData("", "")

        val value = viewModel.mWeatherDataList.getOrAwaitValueTest()
        assertThat(value.size).isEqualTo(0)
    }

}