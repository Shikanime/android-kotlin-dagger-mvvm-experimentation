package io.etna.whatstheweather.ui.main.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.MainViewModel
import javax.inject.Inject


class DetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var addFavoriteImageButton: ImageButton
    lateinit var viewModel: MainViewModel
    lateinit var nameTextView: TextView
    lateinit var weatherDescriptionTextView: TextView
    lateinit var visibilityTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherDescriptionTextView = view.findViewById(R.id.weather_description)
        nameTextView = view.findViewById(R.id.name)
        visibilityTextView = view.findViewById(R.id.name)

        addFavoriteImageButton = view.findViewById(R.id.add_favorite)
        addFavoriteImageButton.setOnClickListener {
            viewModel.addFavoriteLocationWeather()
        }

        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        viewModel.locationWeather.removeObservers(viewLifecycleOwner)
        viewModel.locationWeather.observe(viewLifecycleOwner, Observer{
            viewModel.addFavoriteLocationWeather()
            nameTextView.text = it.name
            weatherDescriptionTextView.text = it.weather[0].description
            visibilityTextView.text = it.visibility.toString()
        })
    }
}