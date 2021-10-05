package com.pauldesilva.weatherapp.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pauldesilva.weatherapp.R
import com.pauldesilva.weatherapp.other.Constants
import com.pauldesilva.weatherapp.ui.main.adapter.WeatherDetailAdapter
import com.pauldesilva.weatherapp.ui.main.viewmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherDetailsFragment : Fragment() {
    companion object {
        const val KEY_START_TIME = "START_TIME"
    }

    private lateinit var mWeatherDetailsRecyclerView: RecyclerView
    private lateinit var mWeatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WeatherDetailAdapter(ArrayList())

        mWeatherViewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        mWeatherDetailsRecyclerView = view.findViewById(R.id.rvWeatherDetailList)
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        mWeatherDetailsRecyclerView.layoutManager = linearLayoutManager
        mWeatherDetailsRecyclerView.adapter = adapter

        /*
            this values is to show weathers from 12AM to 11PM of the selected day
            just incase today is selected no need to display weather of several hours ago.
         */
        val startTime = arguments?.getString(KEY_START_TIME)?.split("T")?.get(0)
        val dateNow = SimpleDateFormat(Constants.API_DATE_FORMAT).format(Calendar.getInstance().time)
        val dateNowSplit = dateNow?.split("T")?.get(0)
        var startTimeMidnight = "${startTime}T00:00:00Z"
        if(startTime == dateNowSplit) {
            startTimeMidnight = dateNow
        }
        val endTimeBeforeNextDay = "${startTime}T23:00:00Z"

        mWeatherViewModel.getWeatherDetailData(startTimeMidnight, endTimeBeforeNextDay)
        mWeatherViewModel.mWeatherDetailDataList.observe(requireActivity(), Observer {
            adapter.updateWeatherDetails(it)
            mWeatherDetailsRecyclerView.scheduleLayoutAnimation()
        })

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (requireActivity().supportFragmentManager.backStackEntryCount == 0) {
                    requireActivity().finish()
                } else {
                    mWeatherViewModel.mWeatherDetailDataList.postValue(ArrayList())
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}