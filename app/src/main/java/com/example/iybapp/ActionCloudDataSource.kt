package com.example.iybapp

import com.example.iybapp.data.ActionServerModel
import com.example.iybapp.data.BaseActionService
import retrofit2.Call

class ActionCloudDataSource(private val service: BaseActionService) :
    BaseCloudDataSource<ActionServerModel>() {
    override fun getActionServerModel() = service.getAction()
}