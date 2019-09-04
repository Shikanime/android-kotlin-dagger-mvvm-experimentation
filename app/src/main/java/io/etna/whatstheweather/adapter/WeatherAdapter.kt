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


class WeatherAdapter(
    private var context: Context
) : RecyclerView.Adapter<WeatherAdapter.BookmarkViewHolder>() {

    internal var weathers: List<WeatherLocationRecord.Weather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail_weather, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val location = weathers[position]

        if (location.icon.isNotBlank()) {
            Glide
                .with(context)
                .load("https://openweathermap.org/img/wn/${location.icon}@2x.png")
                .into(holder.icon)
        }

        holder.bind(location)
    }

    override fun getItemCount() =
        weathers.size

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var icon: ImageView = itemView.findViewById(R.id.weather_icon)
        internal var main: TextView = itemView.findViewById(R.id.weather_main)
        internal var description: TextView = itemView.findViewById(R.id.weather_description)

        fun bind(weather: WeatherLocationRecord.Weather) {
            if (weather.main.isNotBlank()) {
                main.text = weather.main
            }

            if (weather.description.isNotBlank()) {
                description.text = weather.description
            }
        }
    }
}
