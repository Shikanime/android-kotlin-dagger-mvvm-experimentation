package io.etna.whatstheweather.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.etna.whatstheweather.R
import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        Glide.with(get<Application>())
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(OpenWeatherConstants.HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OpenWeatherApiService> {
        get<Retrofit>()
            .create(OpenWeatherApiService::class.java)
    }
}
