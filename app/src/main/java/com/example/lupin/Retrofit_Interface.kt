package com.example.lupin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface Retrofit_Interface {
    @GET("posts")
    fun getJson(): Call<List<Output>>
}