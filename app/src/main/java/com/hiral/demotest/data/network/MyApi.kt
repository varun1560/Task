package com.hiral.demotest.data.network

import com.hiral.demotest.data.network.responses.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MyApi {
    @GET("posts")
    suspend fun getResponse(): Response<List<ApiResponse>>

    companion object {

        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        operator fun invoke(): MyApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(MyApi::class.java)
        }
    }
}