package io.etna.whatstheweather.di

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import io.etna.whatstheweather.R
import io.etna.whatstheweather.repository.WeatherRepository
import io.etna.whatstheweather.service.openweatherapi.OpenWeatherApiService
import io.etna.whatstheweather.util.OpenWeatherConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }


    @Singleton
    @Provides
    internal fun provideGlideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    internal fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OpenWeatherConstants.API_HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideOpenWeatherApiService(retrofit: Retrofit): OpenWeatherApiService {
        return retrofit
            .create(OpenWeatherApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideWeatherRepository(weatherApiService: OpenWeatherApiService): WeatherRepository {
        return WeatherRepository(
            weatherApiService
        )
    }
}
