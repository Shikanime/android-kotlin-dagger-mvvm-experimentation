package io.etna.whatstheweather.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.etna.whatstheweather.R
import io.etna.whatstheweather.ui.main.BookmarksFragment.BookmarksFragment
import io.etna.whatstheweather.ui.main.DetailFragment.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainViewModel by viewModel()

    private lateinit var cityInputText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityInputText = findViewById(R.id.city_input)

        supportFragmentManager.beginTransaction()
            .replace(R.id.bookmark_container, BookmarksFragment())
            .commit()

        cityInputText.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val text = v.text.toString()

                    if (!TextUtils.isEmpty(text)) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.detail_container, DetailFragment())
                            .commit()

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
