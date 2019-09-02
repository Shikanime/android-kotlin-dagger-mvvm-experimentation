package io.etna.whatstheweather.ui.main.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.MainViewModel
import javax.inject.Inject


class DetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel: MainViewModel
    private lateinit var nameTextView: TextView
    private lateinit var weatherDescriptionTextView: TextView
    private lateinit var visibilityTextView: TextView
    private lateinit var iconImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView = view.findViewById(R.id.location_name)
        iconImageView = view.findViewById(R.id.location_weather_icon)
        weatherDescriptionTextView = view.findViewById(R.id.location_weather_description)
        visibilityTextView = view.findViewById(R.id.location_visibility)

        mViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        mViewModel.locationWeather.removeObservers(viewLifecycleOwner)
        mViewModel.locationWeather.observe(viewLifecycleOwner, Observer{
            if (it.weather.isNotEmpty()) {
                Glide
                    .with(this)
                    .load("https://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                    .into(iconImageView)

                iconImageView.setOnClickListener {
                    mViewModel.addFavoriteLocationWeather()
                }
            }

            if (it.name == "") {
                nameTextView.text = "No name"
            } else {
                nameTextView.text = it.name
            }
            if (it.visibility < 0) {
                visibilityTextView.text = "No visibility"
            } else {
                visibilityTextView.text = it.visibility.toString()
            }
            if (it.weather.isEmpty()) {
                weatherDescriptionTextView.text = "No weather description"
            } else {
                weatherDescriptionTextView.text = it.weather[0].description
            }
        })
    }
}