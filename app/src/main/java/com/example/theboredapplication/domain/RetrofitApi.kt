package com.example.theboredapplication.domain

import com.example.theboredapplication.model.Quote
import com.example.theboredapplication.util.BASE_URL
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
interface RetrofitApi {
    @GET("activity")
    fun GetRandomQuote():Call<Quote>


    companion object {

        fun create() : RetrofitApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitApi::class.java)

        }
    }
}