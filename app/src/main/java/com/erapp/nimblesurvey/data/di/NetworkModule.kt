package com.erapp.nimblesurvey.data.di

import android.app.Application
import com.erapp.nimblesurvey.BuildConfig
import com.erapp.nimblesurvey.data.api.NetworkResponseAdapterFactory
import com.erapp.nimblesurvey.data.api.NimbleSurveyApiService
import com.erapp.nimblesurvey.data.datastore.DataStorePreferencesRepository
import com.erapp.nimblesurvey.utils.NotRequiredAuthorization
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (25 * 1024 * 1024).toLong() // 25 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: Interceptor,
        cache: Cache,
        preferences: DataStorePreferencesRepository,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.apply {
            cache(cache)
            addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val method = originalRequest.tag(Invocation::class.java)!!.method()

                if (method.isAnnotationPresent(NotRequiredAuthorization::class.java)) {
                    chain.proceed(originalRequest)
                } else {
                    val token = runBlocking(Dispatchers.IO) {
                        // todo: get token from preferences
                    }
                    val request = originalRequest.newBuilder()
                        .addHeader(AUTHORIZATION_HEADER, "Bearer $token")
                        .build()
                    chain.proceed(request)
                }
            })
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): NimbleSurveyApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NIMBLE_BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NimbleSurveyApiService::class.java)
    }

    private const val AUTHORIZATION_HEADER = "Authorization"
}