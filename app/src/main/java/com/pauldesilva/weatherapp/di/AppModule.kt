package com.pauldesilva.weatherapp.di

import com.pauldesilva.weatherapp.data.api.WeatherApi
import com.pauldesilva.weatherapp.data.repository.DefaultWeatherDataRepository
import com.pauldesilva.weatherapp.data.repository.WeatherDataRepository
import com.pauldesilva.weatherapp.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi() : WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        api: WeatherApi
    ) = DefaultWeatherDataRepository(api) as WeatherDataRepository
}