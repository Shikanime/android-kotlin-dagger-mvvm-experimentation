package io.etna.whatstheweather.ui.main.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var nameTextView: AppCompatTextView
    private lateinit var weatherDescriptionTextView: AppCompatTextView
    private lateinit var visibilityTextView: AppCompatTextView
    private lateinit var iconImageView: AppCompatImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView = view.findViewById(R.id.location_name)
        iconImageView = view.findViewById(R.id.location_weather_icon)
        weatherDescriptionTextView = view.findViewById(R.id.location_weather_description)
        visibilityTextView = view.findViewById(R.id.location_visibility)

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