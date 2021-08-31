package com.example.iybapp.data.net

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel, String>() {
    override fun getServerModel() = service.getQuote()
}