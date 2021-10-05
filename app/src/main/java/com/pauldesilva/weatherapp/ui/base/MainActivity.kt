package com.pauldesilva.weatherapp.ui.base

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.pauldesilva.weatherapp.R
import com.pauldesilva.weatherapp.ui.main.view.WeatherListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_main)

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container_view,
            WeatherListFragment()
        ).commit()
    }
}