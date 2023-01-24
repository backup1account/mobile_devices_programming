package com.example.list4

import retrofit2.Call
import retrofit2.http.GET

interface PlaceholderApi {
    @GET("v2/all?fields=name,capital,flag")
    fun countries(): Call<List<Country>>
}