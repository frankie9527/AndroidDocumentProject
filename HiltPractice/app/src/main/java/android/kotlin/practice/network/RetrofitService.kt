package android.kotlin.practice.network

import retrofit2.http.GET


interface RetrofitService {
    @GET("V9LG4B3A0/n/0-10.html")
    suspend fun getMovies(
    ): Map<String,List<VideoBean>>
}