package com.pauldesilva.weatherapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.pauldesilva.weatherapp.R
import com.pauldesilva.weatherapp.data.model.Interval
import com.pauldesilva.weatherapp.other.Constants
import com.pauldesilva.weatherapp.ui.main.view.WeatherItemClickListener
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class WeatherListAdapter constructor(
    private val mContext: Context,
    private val mClickListener: WeatherItemClickListener,
    private var mWeatherData: List<Interval>
) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherItemViewHolder>() {
    private val MAIN_WEATHER_VIEW = 0
    private val SUB_WEATHER_VIEW = 1

    open class WeatherItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class WeatherItemViewHolderMain(itemView: View) : WeatherItemViewHolder(itemView) {
        internal var txTemp: TextView = itemView.findViewById(R.id.txTemperature)
        internal var txDesc: TextView = itemView.findViewById(R.id.txWeatherDesc)
        internal var txDate: TextView = itemView.findViewById(R.id.txDate)
        internal var imgIcon: ImageFilterView = itemView.findViewById(R.id.imgWeatherIcon)

    }

    class WeatherItemViewHolderSub(itemView: View) : WeatherItemViewHolder(itemView) {
        internal var txTemp: TextView = itemView.findViewById(R.id.txTemperature)
        internal var txDesc: TextView = itemView.findViewById(R.id.txWeatherDesc)
        internal var txDayOfWeek: TextView = itemView.findViewById(R.id.txDayOfWeek)
        internal var imgIcon: ImageFilterView = itemView.findViewById(R.id.imgWeatherIcon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        if (viewType == MAIN_WEATHER_VIEW) {
            return WeatherItemViewHolderMain(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_fragment_weather_main,
                    parent,
                    false
                )
            )
        } else {
            return WeatherItemViewHolderSub(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_fragment_weather_sub,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        val weatherItem = mWeatherData[position].values!!
        val startTime = mWeatherData[position].startTime
        val parsedStartTime = SimpleDateFormat(Constants.API_DATE_FORMAT)
            .parse(startTime)
        val dayOfWeekSdf = SimpleDateFormat("EEE MMM dd")

        when (holder.itemViewType) {
            MAIN_WEATHER_VIEW -> {
                val vh = holder as WeatherItemViewHolderMain
                vh.apply {
                    val tempRound = weatherItem.temperature?.let { it.roundToInt() }
                    txTemp.text = "${tempRound.toString()}°C"
                    txDesc.text = Constants.WEATHER_CODE_MAP[weatherItem.weatherCode]
                    txDate.text = dayOfWeekSdf.format(parsedStartTime)

                    val drawable = Constants.WEATHER_CODE_ICONS[weatherItem.weatherCode]
                    imgIcon.setImageResource(drawable!!)
                }

            }
            SUB_WEATHER_VIEW -> {
                val vh = holder as WeatherItemViewHolderSub
                vh.apply {
                    val tempRound = weatherItem.temperature?.let { it.roundToInt() }
                    txTemp.text = "${tempRound.toString()}°C"
                    txDesc.text = Constants.WEATHER_CODE_MAP[weatherItem.weatherCode]
                    txDayOfWeek.text = dayOfWeekSdf.format(parsedStartTime)

                    val drawable = Constants.WEATHER_CODE_ICONS[weatherItem.weatherCode]
                    imgIcon.setImageResource(drawable!!)
                }

            }
        }

        holder.itemView.setOnClickListener {
            if (position in 0..3) {
                mClickListener.weatherItemClicked(startTime!!)
            } else {
                Toast.makeText(
                    mContext, mContext.getString(R.string.limitations_request),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewType = SUB_WEATHER_VIEW
        if (position == 0) {
            viewType = MAIN_WEATHER_VIEW
        }
        return viewType
    }

    override fun getItemCount(): Int {
        return mWeatherData.size
    }

    fun updateWeatherData(weatherData: List<Interval>) {
        this.mWeatherData = weatherData.subList(1, weatherData.size-1)
        notifyDataSetChanged()
    }
}