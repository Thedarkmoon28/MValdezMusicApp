package com.example.mvaladezmusicapp.network

import com.example.mvaladezmusicapp.data.Album
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://musicapi.pjasoft.com/"

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .header("User-Agent", "MValadezMusicApp/1.0")
            .build()
        chain.proceed(request)
    }
    .build()

interface MusicApiService {
    @GET("api/albums")
    suspend fun getAlbums(): List<Album>

    @GET("api/albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String): Album
}

object MusicApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: MusicApiService by lazy {
        retrofit.create(MusicApiService::class.java)
    }
}
