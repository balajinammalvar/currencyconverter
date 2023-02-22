package com.emperor.kotlinexample.api

import online.balaji.currencyconverter.model.ConverterResponse
import online.balaji.currencyconverter.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /*to Country code list*/
    @GET("list")
    suspend fun getCountryCode(): List<String>


    @GET("movielist.json")
    suspend fun getMovies() :List<Movies>

    /*to convert currency */
    @GET("convert")
    suspend fun getConvertedCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String
    ): ConverterResponse
}