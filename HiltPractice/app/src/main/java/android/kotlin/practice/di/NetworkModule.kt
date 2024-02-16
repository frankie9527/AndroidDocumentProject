package android.kotlin.practice.di

import android.kotlin.practice.network.NetworkInterceptor
import android.kotlin.practice.network.RetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    val BASE_URL = "http://c.3g.163.com/nc/video/list/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val networkInterceptor = NetworkInterceptor()
            addInterceptor(networkInterceptor)
            addNetworkInterceptor(networkInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideMovieService(okHttpClient: OkHttpClient): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)
    }
}