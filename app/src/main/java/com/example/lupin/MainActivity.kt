package com.example.lupin

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_genre.view.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    var lastItemVisibleFlag = false;
    var page = 0;
    var mLockListView = false
    var genreList:ArrayList<Genre> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_recyclerView.adapter = MainRecyclerViewAdapter()
        var linearLayoutManager = LinearLayoutManager(this);
        main_recyclerView.layoutManager = linearLayoutManager
        (main_recyclerView.adapter as MainRecyclerViewAdapter).notifyDataSetChanged()

        main_recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var lastVisibleItemPosition = ( recyclerView.getLayoutManager() as LinearLayoutManager).findLastCompletelyVisibleItemPosition();
                var itemTotalCount = recyclerView.getAdapter()!!.getItemCount() - 1;
                 if (lastVisibleItemPosition == itemTotalCount) {
                    Log.d("TAG", "last Position...");
                     main_progressBar.setVisibility(View.VISIBLE)
                     // 다음 데이터를 불러온다.
                     getItem()
                }

            }

            fun getItem(){
                mLockListView = true
                Handler().postDelayed(Runnable() {
                    run() {
                        genreList.add(Genre("a","car"))
                        genreList.add(Genre("b","audio"))
                        genreList.add(Genre("c","fishing"))
                        genreList.add(Genre("d","ghostStory"))
                        genreList.add(Genre("e","car"))
                        genreList.add(Genre("f","audio"))
                        //page++;
                        (main_recyclerView.adapter as MainRecyclerViewAdapter).notifyDataSetChanged();
                        main_progressBar.setVisibility(View.GONE);
                        mLockListView = false;
                    }
                },1000);

            }

        })

        //getJson()
    }

    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        init {
            genreList.add(Genre("자동차","car"))
            genreList.add(Genre("오디오","audio"))
            genreList.add(Genre("낚시","fishing"))
            genreList.add(Genre("괴담","ghostStory"))
            genreList.add(Genre("자동차","car"))
            genreList.add(Genre("오디오","audio"))
            genreList.add(Genre("낚시","fishing"))
            genreList.add(Genre("괴담","ghostStory"))
            genreList.add(Genre("자동차","car"))
            genreList.add(Genre("오디오","audio"))
            genreList.add(Genre("낚시","fishing"))
            genreList.add(Genre("괴담","ghostStory"))
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.card_genre, parent, false)
            return CustomViewHoler(view)
        }

        override fun getItemCount(): Int {
            Log.d("aa",genreList.size.toString())
            return genreList.size
        }
        inner class CustomViewHoler(view: View) : RecyclerView.ViewHolder(view)

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var mainViewholder = (holder as CustomViewHoler).itemView

            mainViewholder.cardGenre_txtvGenre.text = genreList[position].name
           /* var chatUser = User()


            chatViewholder.cardChat_txtvTitle.text = chatUsers[position].userNickname
            chatViewholder.cardChat_txtvMessage.text = chatUsers[position].lastMessage

            holder.itemView.setOnClickListener {
                val intent = Intent(activity, MessageActivity::class.java)
                intent.putExtra("destinationUid", destinationUsers[position])
                startActivity(intent)
            }*/


        }


    }

   /* fun getJson(){

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

    }*/

}
