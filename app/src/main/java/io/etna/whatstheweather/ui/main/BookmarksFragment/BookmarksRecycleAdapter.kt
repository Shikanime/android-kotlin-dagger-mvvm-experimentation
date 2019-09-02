package io.etna.whatstheweather.ui.main.BookmarksFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.etna.whatstheweather.R
import io.etna.whatstheweather.model.Location


class BookmarksRecycleAdapter constructor(
    private var context: Context
) : RecyclerView.Adapter<BookmarksRecycleAdapter.BookmarkViewHolder>() {

    internal var locations: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val location = locations[position]

        if (location.weather.isNotEmpty()) {
            Glide
                .with(context)
                .load("https://openweathermap.org/img/wn/${location.weather[0].icon}.png")
                .into(holder.icon)
        }

        holder.bind(location)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var icon: ImageView = itemView.findViewById(R.id.location_weather_icon)
        internal var name: TextView = itemView.findViewById(R.id.location_name)
        internal var visibility: TextView = itemView.findViewById(R.id.location_visibility)
        internal var weatherDescription: TextView = itemView.findViewById(R.id.location_weather_description)

        fun bind(location: Location) {
            if (location.name == "") {
                name.text = "No name"
            } else {
                name.text = location.name
            }
            if (location.visibility < 0) {
                visibility.text = "No visibility"
            } else {
                visibility.text = location.visibility.toString()
            }
            if (location.weather.isEmpty()) {
                weatherDescription.text = "No weather description"
            } else {
                weatherDescription.text = location.weather[0].description
            }
        }
    }
}
