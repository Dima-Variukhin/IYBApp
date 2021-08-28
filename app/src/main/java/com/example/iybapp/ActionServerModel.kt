package com.example.iybapp

import com.google.gson.annotations.SerializedName

class ActionServerModel(
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
) {
    fun toAction() = Action(key, participants, accessibility, activity, type)
}