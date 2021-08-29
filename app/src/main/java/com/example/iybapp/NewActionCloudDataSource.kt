package com.example.iybapp

import com.example.iybapp.data.NewActionServerModel
import com.example.iybapp.data.NewActionService

class NewActionCloudDataSource(private val service: NewActionService) :
    BaseCloudDataSource<NewActionServerModel>() {

    override fun getActionServerModel() = service.getAction()

}