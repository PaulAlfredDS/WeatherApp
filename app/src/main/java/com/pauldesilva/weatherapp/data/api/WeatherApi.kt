package com.pauldesilva.weatherapp.data.api

import com.pauldesilva.weatherapp.BuildConfig
import com.pauldesilva.weatherapp.data.model.Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v4/{timelines}")
    suspend fun getWeather(
        @Path(value = "timelines", encoded = true) timelines: String = "timelines",
        @Query("location", encoded = true) location: String = "14.589119745720266,121.06040320054605",
        @Query("fields", encoded = true) fields: String = "temperature,weatherCode",
        @Query("timesteps") timeSteps : String = "1d",
        @Query("units") units : String = "metric",
        @Query("startTime") startTime : String = "2021-10-01T22:00:00Z",
        @Query("endTime") endTime : String = "2021-10-12T22:00:00Z",

        @Query("apikey") apiKey : String = BuildConfig.API_KEY
    ) : Response<Root>
}