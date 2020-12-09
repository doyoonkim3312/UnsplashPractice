package com.yoonlab.usplashclone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder().baseUrl("https://api.unsplash.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var usrEditText = findViewById<EditText>(R.id.searchTextField)
        val resultRecyclerView = findViewById<RecyclerView>(R.id.itemRecylerView)

        findViewById<Button>(R.id.searchBtn).setOnClickListener {
            val searchKey = usrEditText.text.toString()
            startRetrofitConnection(retrofit, this, searchKey, resultRecyclerView)
        }



    }

    fun callRetrofitService(retrofit: Retrofit) : UnsplashSearchInterface {
        return retrofit.create(UnsplashSearchInterface::class.java)
    }

    fun startRetrofitConnection(retrofit: Retrofit, context: Context, searchKeyword: String, recyclerView : RecyclerView){
        val retrofitCall = callRetrofitService(retrofit).getImage(
                searchKeyword, 1, 50, "en")

        retrofitCall.enqueue(object: retrofit2.Callback<SearchResult>{
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                if(response.isSuccessful) {
                    //findViewById<TextView>(R.id.testText).text = "SUCCESS"
                    Log.d("api success", response.body().toString())

                    if (response.body()?.results?.isEmpty() != true) {
                        val resultList = mutableListOf<SearchResult.Result?>()

                        response.body()?.results?.forEach { it
                            resultList.add(it)
                        }
                        val adapter = MainAdapter(resultList, context)
                        recyclerView.adapter = adapter

                    } else {
                        Toast.makeText(this@MainActivity, "There're no results for $searchKeyword", Toast.LENGTH_LONG)
                    }


                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                //findViewById<TextView>(R.id.testText).text = "FAILD"
                Log.d("api faild", t.message.toString())
            }

        })
    }
}