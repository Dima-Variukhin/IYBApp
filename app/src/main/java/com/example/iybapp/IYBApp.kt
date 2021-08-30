package com.example.iybapp

import android.app.Application
import com.example.iybapp.data.BaseRepository
import com.example.iybapp.data.cache.*
import com.example.iybapp.data.mapper.ActionRealmMapper
import com.example.iybapp.data.mapper.QuoteRealmMapper
import com.example.iybapp.data.net.ActionCloudDataSource
import com.example.iybapp.data.net.BaseActionService
import com.example.iybapp.data.net.QuoteCloudDataSource
import com.example.iybapp.data.net.QuoteService
import com.example.iybapp.domain.BaseInteractor
import com.example.iybapp.domain.FailureFactory
import com.example.iybapp.presentation.BaseCommunication
import com.example.iybapp.presentation.BaseResourceManager
import com.example.iybapp.presentation.BaseViewModel
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IYBApp : Application() {
    lateinit var baseViewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel
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

        val realmProvider = BaseRealmProvider()
        val cacheDataSource =
            ActionCachedDataSource(realmProvider, ActionRealmMapper(), ActionRealmToCommonMapper())
        val cloudDataSource = ActionCloudDataSource(retrofit.create(BaseActionService::class.java))
        val jokeRepository = BaseRepository(cacheDataSource, cloudDataSource, BaseCachedData())
        val failureHandler = FailureFactory(BaseResourceManager(this))
        val mapper = CommonSuccessMapper<Int>()
        val interactor =
            BaseInteractor(jokeRepository, failureHandler, mapper)
        baseViewModel = BaseViewModel(interactor, BaseCommunication())

        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedData()
        )
        quoteViewModel = BaseViewModel(
            BaseInteractor(quoteRepository, failureHandler, mapper),
            BaseCommunication()
        )
    }
}