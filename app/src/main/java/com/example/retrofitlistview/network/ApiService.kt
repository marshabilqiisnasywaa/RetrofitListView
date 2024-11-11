package com.example.retrofitlistview.network


import com.example.retrofitlistview.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    fun getAllUsers(): Call<Users>
}