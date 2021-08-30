package com.example.iybapp.data.net

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.Mapper
import com.google.gson.annotations.SerializedName

class QuoteServerModel(
    @SerializedName(value = "_id")
    private val id: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String
) : Mapper<CommonDataModel> {
    override fun to() = CommonDataModel(id, 0, 0.0, content, author)

}