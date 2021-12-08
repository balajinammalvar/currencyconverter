package com.emperor.kotlinexample.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import online.balaji.currencyconverter.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroClient {
    private const val BASE_URL = "https://currency-converter13.p.rapidapi.com/"
    private const val BASE = BuildConfig.VERSION_CODE
    private fun getRetrofit(): Retrofit {
        /*http client setup with headers and timeout*/
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("x-rapidapi-host", "currency-converter13.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "37117bbf9dmsh36aafa2407cfb30p1841d7jsna8b118982634")
                .build()
            chain.proceed(request)
        }
        val client = builder.build()
        /*retrofit build */
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}

