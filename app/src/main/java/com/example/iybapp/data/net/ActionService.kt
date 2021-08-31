package com.example.iybapp.data.net

import com.example.iybapp.data.NewActionServerModel
import retrofit2.Call
import retrofit2.http.GET

interface BaseActionService {
    @GET("https://www.boredapi.com/api/activity")
    fun getAction(): Call<ActionServerModel>
}

interface NewActionService {
    @GET("https://www.boredapi.com/api/activity?participants=4")
    fun getAction(): Call<NewActionServerModel>
}