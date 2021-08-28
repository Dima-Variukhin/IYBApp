package com.example.iybapp

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IYBApp : Application() {
    lateinit var baseViewModel: BaseViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cachedAction = BaseCachedAction()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider())
        val resourceManager = BaseResourceManager(this)
        baseViewModel = BaseViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(
                    cachedAction,
                    cacheDataSource,
                    NoCachedActions(resourceManager)
                ),
                CloudResultHandler(
                    cachedAction,
                    BaseCloudDataSource(retrofit.create(ActionService::class.java)),
                    NoConnection(resourceManager),
                    ServiceUnavailable(resourceManager)
                ),
                cachedAction
            ),
            BaseCommunication()
        )
    }
}