package com.pauldesilva.weatherapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pauldesilva.weatherapp.data.model.Timeline

data class Data (
    @SerializedName("timelines")
    @Expose
    var timelines: List<Timeline>? = null
)