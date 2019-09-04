package io.etna.whatstheweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.etna.whatstheweather.R
import io.etna.whatstheweather.data.WeatherLocationRecord


class WeatherLocationAdapter(
    private var context: Context
) : RecyclerView.Adapter<WeatherLocationAdapter.WeatherLocationViewHolder>() {

    internal var weatherLocations: List<WeatherLocationRecord> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherLocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return WeatherLocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherLocationViewHolder, position: Int) {
        val location = weatherLocations[position]

        if (location.weather.isNotEmpty() and location.weather.first().icon.isNotBlank()) {
            Glide
                .with(context)
                .load("https://openweathermap.org/img/wn/${location.weather.first().icon}@2x.png")
                .into(holder.icon)
        }

        holder.bind(location)
    }

    override fun getItemCount() =
        weatherLocations.size

    inner class WeatherLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var icon: ImageView = itemView.findViewById(R.id.location_weather_icon)
        internal var name: TextView = itemView.findViewById(R.id.location_name)
        internal var visibility: TextView = itemView.findViewById(R.id.location_visibility)
        internal var weatherDescription: TextView =
            itemView.findViewById(R.id.location_weather_description)

        fun bind(weatherLocationRecord: WeatherLocationRecord) {
            if (weatherLocationRecord.name.isNotBlank()) {
                name.text = weatherLocationRecord.name
            }

            if (weatherLocationRecord.visibility > 0) {
                visibility.text = weatherLocationRecord.visibility.toString()
            }

            if (weatherLocationRecord.weather.isNotEmpty() and
                weatherLocationRecord.weather.first().icon.isNotBlank()) {
                weatherDescription.text = weatherLocationRecord.weather.first().description
            }
        }
    }
}
