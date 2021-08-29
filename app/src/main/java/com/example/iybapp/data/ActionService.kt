package com.example.iybapp.data

import com.example.iybapp.ActionDataModel
import com.example.iybapp.core.Mapper
import retrofit2.Call
import retrofit2.http.GET

interface ActionService<T : Mapper<ActionDataModel>> {
    fun getAction(): Call<T>
}

interface BaseActionService {
    @GET("https://www.boredapi.com/api/activity")
    fun getAction(): Call<ActionServerModel>
}

interface NewActionService {
    @GET("https://www.boredapi.com/api/activity?participants=4")
    fun getAction(): Call<NewActionServerModel>
}