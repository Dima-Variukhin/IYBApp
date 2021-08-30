package com.example.iybapp.data.net

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.Mapper
import com.example.iybapp.core.data.net.CloudDataSource
import com.example.iybapp.core.domain.NoConnectionException
import com.example.iybapp.core.domain.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T : Mapper<CommonDataModel>> :
    CloudDataSource {
    protected abstract fun getServerModel(): Call<T>

    override suspend fun getData(): CommonDataModel {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}