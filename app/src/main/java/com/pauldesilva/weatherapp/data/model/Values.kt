package com.pauldesilva.weatherapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Values (
    @SerializedName("temperature")
    @Expose
    var temperature: Double? = null,

    @SerializedName("weatherCode")
    @Expose
    var weatherCode: Int? = null
)