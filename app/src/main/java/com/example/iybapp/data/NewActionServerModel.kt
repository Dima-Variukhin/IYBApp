package com.example.iybapp.data

import com.example.iybapp.ActionDataModel
import com.example.iybapp.core.Mapper
import com.google.gson.annotations.SerializedName

class NewActionServerModel(
    @SerializedName("key")
    private val key: String,
    @SerializedName("participants")
    private val participants: Int,
    @SerializedName("accessibility")
    private val accessibility: Int,
    @SerializedName("activity")
    private val activity: String,
    @SerializedName("type")
    private val type: String

) : Mapper<ActionDataModel> {
    override fun to() = ActionDataModel(key, participants, accessibility, activity, type)
}