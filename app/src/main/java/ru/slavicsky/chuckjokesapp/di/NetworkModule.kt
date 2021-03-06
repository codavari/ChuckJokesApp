package ru.slavicsky.chuckjokesapp.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ru.slavicsky.chuckjokesapp.BuildConfig
import ru.slavicsky.chuckjokesapp.data.api.services.JokesService
import ru.slavicsky.chuckjokesapp.data.api.services.JokesService.Companion.ENDPOINT
import ru.slavicsky.chuckjokesapp.data.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(@API okhttpClient: OkHttpClient): JokesService {
        return Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(JokesService::class.java)
    }

    @Singleton
    @Provides
    fun provideDataRepository(service: JokesService) =
        DataRepository(service)

    @API
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
}
