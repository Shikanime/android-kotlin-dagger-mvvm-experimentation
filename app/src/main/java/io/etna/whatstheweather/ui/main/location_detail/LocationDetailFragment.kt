package io.etna.whatstheweather.ui.main.location_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import io.etna.whatstheweather.R

class LocationDetailFragment: DaggerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(activity, "Location Detail", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.location_detail_fragment, container, false)
    }
}