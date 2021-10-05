package com.pauldesilva.weatherapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Interval (
    @SerializedName("startTime")
    @Expose
    var startTime: String? = null,

    @SerializedName("values")
    @Expose
    var values: Values? = null
)