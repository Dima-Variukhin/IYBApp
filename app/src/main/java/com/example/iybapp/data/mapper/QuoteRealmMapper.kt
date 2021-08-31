package com.example.iybapp.data.mapper

import com.example.iybapp.CommonDataModelMapper
import com.example.iybapp.data.cache.QuoteRealmModel

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel, String> {
    override fun map(
        key: String,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ) = QuoteRealmModel().also { quote ->
        quote.key = key
        quote.content = first
        quote.author = second
    }
}
