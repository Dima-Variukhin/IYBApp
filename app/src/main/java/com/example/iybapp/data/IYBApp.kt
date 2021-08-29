package com.example.iybapp.data

import android.app.Application
import com.example.iybapp.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IYBApp : Application() {
    lateinit var baseViewModel: BaseViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), ActionRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource =
            NewActionCloudDataSource(retrofit.create(NewActionService::class.java))
        val repository = BaseActionRepository(cacheDataSource, cloudDataSource, BaseCachedAction())
        val interactor = BaseActionInteractor(
            repository,
            ActionFailureFactory(resourceManager),
            ActionSuccessMapper()
        )
        baseViewModel = BaseViewModel(interactor, BaseCommunication())
    }
}