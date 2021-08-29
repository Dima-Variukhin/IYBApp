package com.example.iybapp

import com.example.iybapp.core.Mapper
import com.example.iybapp.data.*
import com.example.iybapp.domain.NoConnectionException
import com.example.iybapp.domain.ServiceUnavailableException
import retrofit2.Call
import java.lang.Exception
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T : Mapper<ActionDataModel>> :
    CloudDataSource {
    protected abstract fun getActionServerModel(): Call<T>

    override suspend fun getAction(): ActionDataModel {
        try {
            return getActionServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}