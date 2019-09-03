package io.etna.whatstheweather.ui.main

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


class FavoriteRecycleAdapter constructor(
    private var context: Context
) : RecyclerView.Adapter<FavoriteRecycleAdapter.BookmarkViewHolder>() {

    internal var weatherLocations: List<WeatherLocationRecord> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val location = weatherLocations[position]

        if (location.weather.isNotEmpty()) {
            Glide
                .with(context)
                .load("https://openweathermap.org/img/wn/${location.weather[0].icon}.png")
                .into(holder.icon)
        }

        holder.bind(location)
    }

    override fun getItemCount(): Int {
        return weatherLocations.size
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

            if (weatherLocationRecord.weather.isNotEmpty()) {
                weatherDescription.text = weatherLocationRecord.weather[0].description
            }
        }
    }
}
