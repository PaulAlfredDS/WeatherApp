package com.pauldesilva.weatherapp.other

import com.pauldesilva.weatherapp.R

object Constants {

    const val BASE_URL = "https://api.tomorrow.io/v4/timelines/"

    val WEATHER_CODE_ICONS = mapOf(
        0 to R.drawable.ic_clear_day,
        1000 to R.drawable.ic_clear_day,
        1001 to R.drawable.ic_cloudy,
        1100 to R.drawable.ic_mostly_clear_day,
        1101 to R.drawable.ic_partly_cloudy_day,
        1102 to R.drawable.ic_mostly_cloudy,
        2000 to R.drawable.ic_fog,
        2100 to R.drawable.ic_fog_light,
        3000 to R.drawable.ic_mostly_cloudy,
        3001 to R.drawable.ic_fog_light,
        3002 to R.drawable.ic_fog,
        4000 to R.drawable.ic_drizzle,
        4001 to R.drawable.ic_rain,
        4200 to R.drawable.ic_rain_light,
        4201 to R.drawable.ic_rain_heavy,
        5000 to R.drawable.ic_snow,
        5001 to R.drawable.ic_flurries,
        5100 to R.drawable.ic_snow_light,
        5101 to R.drawable.ic_snow_heavy,
        6000 to R.drawable.ic_freezing_drizzle,
        6001 to R.drawable.ic_freezing_rain,
        6200 to R.drawable.ic_freezing_rain_light,
        6201 to R.drawable.ic_freezing_rain_heavy,
        7000 to R.drawable.ic_ice_pellets,
        7101 to R.drawable.ic_ice_pellets_heavy,
        7102 to R.drawable.ic_ice_pellets_light,
        8000 to R.drawable.ic_tstorm
    )

    val WEATHER_CODE_MAP = mapOf(
        0 to "Unknown",
        1000 to "Clear",
        1001 to "Cloudy",
        1100 to "Mostly Clear",
        1101 to "Partly Cloudy",
        1102 to "Mostly Cloudy",
        2000 to "Fog",
        2100 to "Light Fog",
        3000 to "Light Wind",
        3001 to "Wind",
        3002 to "Strong Wind",
        4000 to "Drizzle",
        4001 to "Rain",
        4200 to "Light Rain",
        4201 to "Heavy Rain",
        5000 to "Snow",
        5001 to "Flurries",
        5100 to "Light Snow",
        5101 to "Heavy Snow",
        6000 to "Freezing Drizzle",
        6001 to "Freezing Rain",
        6200 to "Light Freezing Rain",
        6201 to "Heavy Freezing Rain",
        7000 to "Ice Pellets",
        7101 to "Heavy Ice Pellets",
        7102 to "Light Ice Pellets",
        8000 to "Thunderstorm"
    )

    const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
}