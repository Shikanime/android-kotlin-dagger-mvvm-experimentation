package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import io.etna.whatstheweather.R
import io.etna.whatstheweather.model.Location
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var awesomeTextView: TextView
    private lateinit var cityInputText: EditText

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        awesomeTextView = findViewById(R.id.logText)
        cityInputText = findViewById(R.id.cityInputText)

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mViewModel.locationWeather.observe(this, Observer<Location>{
            awesomeTextView.text = it.weather[0].description
        })

        cityInputText.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val text = v.text.toString()

                    if (!TextUtils.isEmpty(text)) {
                        mViewModel.getLocationWeather(text)
                        false
                    } else {
                        true
                    }
                }
                else ->
                    false
            }
        }
    }
}
