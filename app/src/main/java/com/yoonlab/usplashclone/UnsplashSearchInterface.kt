package com.yoonlab.usplashclone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashSearchInterface {
    @GET ("search/photos?client_id=4rvJU82BkuKftXvBJpOFNnS7yvxh4L-OC9CLAghWzOM")
    fun getImage(@Query("query") query: String,
    @Query("page") page: Int,
    @Query("per_page") perPage: Int,
    @Query("lang") lang : String): Call<SearchResult> }