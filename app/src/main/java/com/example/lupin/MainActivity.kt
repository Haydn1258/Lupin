package com.example.lupin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getJson()
    }


    fun getJson(){

        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().addHeader("Content-Type", "application/json; charset=utf-8").build()
            return@addInterceptor chain.proceed(request)
        }.build()


        var retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(
            GsonConverterFactory.create()).client(httpClient).
            addConverterFactory(ScalarsConverterFactory.create()).build()
        var server = retrofit.create(Retrofit_Interface::class.java)

        server.getJson().enqueue(object :Callback<List<Output>>{
            override fun onFailure(call: Call<List<Output>>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail"+t.message.toString(), Toast.LENGTH_SHORT).show()
                Log.d("결과", ""+t.message.toString())
            }

            override fun onResponse(call: Call<List<Output>>, response: Response<List<Output>>) {
                if (response.isSuccessful()) {
                    val body = response.body()
                    body?.let {
                        txtv_json.setText(body[4].id.toString())
                    }
                }
                else {
                }
                Log.d("결과",""+response.code())

            }

        })

    }

}
