package io.etna.whatstheweather.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.etna.whatstheweather.R
import io.etna.whatstheweather.repository.WeatherRepository
import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    single {
        Glide.with(get<Application>())
            .setDefaultRequestOptions(get())
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(OpenWeatherConstants.API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OpenWeatherApiService> {
        get<Retrofit>()
            .create(OpenWeatherApiService::class.java)
    }

    single {
        WeatherRepository(get())
    }
}
