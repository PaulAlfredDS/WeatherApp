package com.pauldesilva.weatherapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("timestep")
    @Expose
    var timestep: String? = null,

    @SerializedName("startTime")
    @Expose
    var startTime: String? = null,

    @SerializedName("endTime")
    @Expose
    var endTime: String? = null,

    @SerializedName("intervals")
    @Expose
    var intervals: List<Interval>? = null
)