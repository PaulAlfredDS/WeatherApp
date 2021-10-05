package com.pauldesilva.weatherapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.pauldesilva.weatherapp.R
import com.pauldesilva.weatherapp.data.model.Interval
import com.pauldesilva.weatherapp.other.Constants
import java.text.SimpleDateFormat

class WeatherDetailAdapter constructor(var mWeatherDetailList: List<Interval>) :
    RecyclerView.Adapter<WeatherDetailAdapter.WeatherDetailViewHolder>() {
    class WeatherDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txTemp: TextView = itemView.findViewById(R.id.txTemperature)
        internal var txDesc: TextView = itemView.findViewById(R.id.txWeatherDesc)
        internal var txDate: TextView = itemView.findViewById(R.id.txDate)
        internal var imgIcon: ImageFilterView = itemView.findViewById(R.id.imgWeatherIcon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDetailViewHolder {
        return WeatherDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fragment_weather_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherDetailViewHolder, position: Int) {
        val weatherDetail = mWeatherDetailList[position].values
        holder.apply {
            val tempRound = weatherDetail?.temperature?.let { Math.round(it) }
            txTemp.text = "${tempRound.toString()}°C"
            txDesc.text = Constants.WEATHER_CODE_MAP[weatherDetail?.weatherCode]

            val parsedStartTime = SimpleDateFormat(Constants.API_DATE_FORMAT)
                .parse(mWeatherDetailList[position].startTime)
            val dayOfWeekSdf = SimpleDateFormat("EEE MMM dd HH:mm")
            txDate.text = dayOfWeekSdf.format(parsedStartTime)

            val drawable = Constants.WEATHER_CODE_ICONS[weatherDetail!!.weatherCode]
            imgIcon.setImageResource(drawable!!)
        }
    }

    override fun getItemCount(): Int {
        return mWeatherDetailList.size
    }

    fun updateWeatherDetails(weatherDetails: List<Interval>) {
        mWeatherDetailList = ArrayList()
        mWeatherDetailList = weatherDetails
        notifyDataSetChanged()
    }

}