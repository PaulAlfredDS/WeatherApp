package com.pauldesilva.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Root(
    @SerializedName("data") val data: Data
)
