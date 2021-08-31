package com.example.iybapp.data.net

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.Mapper
import com.google.gson.annotations.SerializedName

class ActionServerModel(
    @SerializedName("key")
    private val key: String,
    @SerializedName("participants")
    private val participants: Int,
    @SerializedName("accessibility")
    private val accessibility: Double,
    @SerializedName("activity")
    private val activity: String,
    @SerializedName("type")
    private val type: String
) : Mapper<CommonDataModel<String>> {
    override fun to() = CommonDataModel(key, participants, accessibility, activity, type)

}