package com.example.iybapp

import com.example.iybapp.data.NewActionServerModel
import com.example.iybapp.data.net.BaseCloudDataSource
import com.example.iybapp.data.net.NewActionService

class NewActionCloudDataSource(private val service: NewActionService) :
    BaseCloudDataSource<NewActionServerModel>() {

    override fun getServerModel() = service.getAction()

}