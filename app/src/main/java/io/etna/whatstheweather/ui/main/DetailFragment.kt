package io.etna.whatstheweather.ui.main

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
import io.etna.whattheweather.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment() {

    private val mViewModel: MainViewModel by sharedViewModel()

    private lateinit var mNameTextView: AppCompatTextView
    private lateinit var mWeatherDescriptionTextView: AppCompatTextView
    private lateinit var mVisibilityTextView: AppCompatTextView
    private lateinit var mIconImageView: AppCompatImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNameTextView = view.findViewById(R.id.location_name)
        mIconImageView = view.findViewById(R.id.location_weather_icon)
        mWeatherDescriptionTextView = view.findViewById(R.id.location_weather_description)
        mVisibilityTextView = view.findViewById(R.id.location_visibility)

        mViewModel.currentWeatherLocation.removeObservers(viewLifecycleOwner)
        mViewModel.currentWeatherLocation.observe(viewLifecycleOwner, Observer {
            if (it.weather.isNotEmpty()) {
                Glide
                    .with(this)
                    .load("https://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                    .into(mIconImageView)

                mIconImageView.setOnClickListener { _ ->
                    mViewModel.addFavoriteLocationWeather(it)
                }
            }

            if (it.name.isNotBlank()) {
                mNameTextView.text = it.name
            }

            if (it.visibility > 0) {
                mVisibilityTextView.text = it.visibility.toString()
            }

            if (it.weather.isNotEmpty()) {
                mWeatherDescriptionTextView.text = it.weather[0].description
            }
        })
    }
}