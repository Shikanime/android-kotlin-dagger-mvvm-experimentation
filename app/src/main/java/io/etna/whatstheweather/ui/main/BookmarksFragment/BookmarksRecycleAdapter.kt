package io.etna.whatstheweather.ui.main.BookmarksFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.etna.whatstheweather.R
import io.etna.whatstheweather.model.Location


class BookmarksRecycleAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var locations: List<Location> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_bookmark, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookmarkViewHolder).bind(locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var name: TextView = itemView.findViewById(R.id.title)

        fun bind(location: Location) {
            name.text = location.name
        }
    }
}




