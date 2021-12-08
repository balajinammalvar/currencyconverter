package com.emperor.kotlinexample.api

import online.balaji.currencyconverter.view.model.ConverterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /*to Country code list*/
    @GET("list")
    suspend fun getCountryCode(): List<String>

    /*to convert currency */
    @GET("convert")
    suspend fun getConvertedCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): ConverterResponse
}