package com.pauldesilva.weatherapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pauldesilva.weatherapp.R
import com.pauldesilva.weatherapp.data.model.Interval
import com.pauldesilva.weatherapp.other.Constants
import com.pauldesilva.weatherapp.ui.main.adapter.WeatherListAdapter
import com.pauldesilva.weatherapp.ui.main.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WeatherListFragment : Fragment(R.layout.fragment_weather_list), WeatherItemClickListener {
    private lateinit var mWeatherRecyclerView: RecyclerView
    private lateinit var mWeatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //compute start date and end date for total of 10 days for the api call
        val sdf = SimpleDateFormat(Constants.API_DATE_FORMAT)
        val calendar = Calendar.getInstance()
        val startTime = sdf.format(calendar.time)
        //add 10 days to current date
        calendar.add(Calendar.DAY_OF_MONTH, 10)
        val endTime = sdf.format(calendar.time)

        val adapter = WeatherListAdapter(requireContext(),this, ArrayList())

        mWeatherViewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        mWeatherRecyclerView = view.findViewById(R.id.rvWeatherList)
        val linearLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        mWeatherRecyclerView.layoutManager = linearLayoutManager
        mWeatherRecyclerView.adapter = adapter

        mWeatherViewModel.getWeatherData(startTime, endTime)
        mWeatherViewModel.mWeatherDataList.observe(requireActivity(), Observer {
            adapter.updateWeatherData(it)
            mWeatherRecyclerView.scheduleLayoutAnimation()
        })
    }

    override fun weatherItemClicked(startTime: String) {
        val fragment = WeatherDetailsFragment()
        val bundle = Bundle()
        bundle.putString(WeatherDetailsFragment.KEY_START_TIME, startTime)
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.fragment_container_view,
            fragment
        ).addToBackStack(WeatherListFragment::class.java.simpleName)
            .commit()
    }
}