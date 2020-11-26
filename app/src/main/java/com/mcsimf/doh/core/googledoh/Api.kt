package com.mcsimf.doh.core.googledoh

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Maksym Fedyay on 11/26/20 (mcsimf@gmail.com).
 */
class Api {

    private val API_URL = "https://dns.google/"

    var dohApi: DoH

    init {
        val okHttpClient = OkHttpClient.Builder().let {
            it.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            it.build()
        }

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        dohApi = retrofit.create(DoH::class.java)
    }


}