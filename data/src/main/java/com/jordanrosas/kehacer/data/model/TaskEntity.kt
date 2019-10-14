package com.jordanrosas.kehacer.data.model

import com.google.gson.annotations.SerializedName

data class TaskEntity(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("task") var task: String,
    @SerializedName("category") var category: String,
    @SerializedName("date") var date: String,
    @SerializedName("time") var time: String
)