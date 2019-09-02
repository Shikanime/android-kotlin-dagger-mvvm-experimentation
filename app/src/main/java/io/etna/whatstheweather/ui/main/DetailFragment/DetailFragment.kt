package io.etna.whatstheweather.ui.main.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import dagger.android.support.DaggerFragment
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.MainViewModel
import kotlinx.android.synthetic.main.layout_list_bookmark.*
import javax.inject.Inject


class DetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel: MainViewModel
    private lateinit var nameTextView: TextView
    private lateinit var weatherDescriptionTextView: TextView
    private lateinit var visibilityTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherDescriptionTextView = view.findViewById(R.id.weather_description)
        nameTextView = view.findViewById(R.id.name)
        visibilityTextView = view.findViewById(R.id.name)
        imageView = view.findViewById(R.id.image_view)

        mViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        mViewModel.locationWeather.removeObservers(viewLifecycleOwner)
        mViewModel.locationWeather.observe(viewLifecycleOwner, Observer{
            Glide
                .with(this)
                .load("https://openweathermap.org/img/wn/${it.weather[0].icon}.png")
                .into(imageView)

            nameTextView.text = it.name
            weatherDescriptionTextView.text = it.weather[0].description
            visibilityTextView.text = it.visibility.toString()
        })

        imageView.setOnClickListener {
            mViewModel.addFavoriteLocationWeather()
        }
    }
}