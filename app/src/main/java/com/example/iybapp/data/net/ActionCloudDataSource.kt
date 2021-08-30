package com.example.iybapp.data.net

class ActionCloudDataSource(private val service: BaseActionService) :
    BaseCloudDataSource<ActionServerModel>() {
    override fun getServerModel() = service.getAction()
}